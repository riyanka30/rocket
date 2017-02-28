package com.rocket.minutes.utils;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rocket.minutes.RequestBeans.MinutesRequestBean;
import com.rocket.minutes.RequestBeans.TaskRequestBean;

@Service
public class MailerService {
	
	@Autowired
	PropertyReader propertyReader;
	
	public void sendMail(MinutesRequestBean mrb){
		final String username = propertyReader.username;
		final String password = propertyReader.password;
		
		System.out.println("username is "+username);
		System.out.println("password is "+password);
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(propertyReader.username));
			message.addRecipients(Message.RecipientType.TO,
					InternetAddress.parse(propertyReader.defaultTo));
			String[] splitted = mrb.getAttendees().split(",");
			for(int i=0;i<splitted.length;i++){
				message.addRecipients(Message.RecipientType.TO,
						InternetAddress.parse(splitted[i]));				
			}
			message.setSubject("Minutes of meeting : "+mrb.getTitle());
//			message.setContent("<h1>Dear Mail Crawler,</h1>"
//				+ "\n\n No spam to my email, please!","text/html");
			message.setContent(getMailBody(mrb),"text/html");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	private String getMailBody(MinutesRequestBean mrb){
		StringBuilder sb = new StringBuilder();
		sb.append(getCssForTable());
		sb.append("<h3 style=\"color: #428bca\">Minutes of meeting : "+mrb.getTitle()+"</h3><hr>");
		sb.append("<p><b>Client : </b>"+mrb.getClient()+"</p>");
		sb.append("<p><b>Project : </b>"+mrb.getProject()+"</p>");
		sb.append("<p><b>Time : </b>"+mrb.getTime()+"</p>");
		sb.append("<p><b>Location : </b>"+mrb.getLocation()+"</p>");
		sb.append("<p><b>Chairperson : </b>"+mrb.getChairP()+"</p>");
		String table ="<table style=\"border:1px solid black\">"+
	"<thead>"+
		"<tr style=\"background-color: #428bca;color: white\">"+
			"<th>Topic</th>"+
			"<th>Description</th>"+
			"<th>Owners</th>"+
			"<th>Target date</th>"+
			"<th>Status</th>"+
			"<th>Minutes</th>"+
		"</tr>"+
	"</thead>"+
	"<tbody>";
		List<TaskRequestBean> listtrb = mrb.getTopics();
		StringBuilder sb1 = new StringBuilder();
		for(int i=0;i<listtrb.size();i++){
			TaskRequestBean trb = listtrb.get(i);
			sb1.append("<tr>");
			sb1.append("<td>"+trb.getTitle()+"</td>");
			sb1.append("<td>"+trb.getDescription()+"</td>");
			sb1.append("<td>"+trb.getOwners()+"</td>");
			sb1.append("<td>"+trb.getTarget()+"</td>");
			sb1.append("<td style=\"border:1px solid black;color:white;background:"+getStatusColor(trb.getStatus())+"\">"+trb.getStatus()+"</td>");
			sb1.append("<td>"+trb.getMinutes()+"</td>");
			sb1.append("</tr>");
		}
		table+=sb1.toString()+"</tbody></table>";
		sb.append(table);
		//System.out.println(sb.toString());
		return sb.toString();
		
	}
	private String getStatusColor(String status){
		String color = "black";
		if(status.equalsIgnoreCase("pending"))
			color="crimson";
		else if(status.equalsIgnoreCase("complete"))
			color="#089111";
		else if(status.equalsIgnoreCase("in progress"))
			color="#E39B1E";
		return color;
	}
	
	private String getCssForTable(){
		StringBuilder sb = new StringBuilder();
		sb.append("<style type=\"text/css\">");
		sb.append("body h1, h2, h3, h4, h5, p {font-family: Verdana, Geneva, sans-serif;}");
		sb.append("p {font-size: 14px;}");
		sb.append("table {font-family: Verdana, Geneva, sans-serif;/*width:75%;*/font-size: 12px;}");
		sb.append("table, th, td {border: 1px solid black;border-collapse: collapse;}");
		sb.append("th, td {padding: 5px;text-align: left;}");
		sb.append("tr:nth-child(odd) {background-color: #dddddd;}");
		sb.append("th {background-color: #144178;color: white;}");
		sb.append("</style>");
		return sb.toString();
	}
}
