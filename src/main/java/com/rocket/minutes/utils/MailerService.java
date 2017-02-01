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
import org.springframework.stereotype.Service;
import com.rocket.minutes.RequestBeans.MinutesRequestBean;
import com.rocket.minutes.RequestBeans.TaskRequestBean;

@Service
public class MailerService {
	
	public void sendMail(MinutesRequestBean mrb){
		final String username = PropertyReader.username;
		final String password = PropertyReader.password;

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
			message.setFrom(new InternetAddress(PropertyReader.username));
			message.addRecipients(Message.RecipientType.TO,
					InternetAddress.parse(PropertyReader.defaultTo));
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
		sb.append("<h2 style=\"color: #428bca\">Minutes of meeting : "+mrb.getTitle()+"</h2><hr>\n\n");
		sb.append("<div><b>Client : </b>"+mrb.getClient()+"</div>");
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
			sb1.append("<td style=\"border:1px solid black\">"+trb.getTitle()+"</td>");
			sb1.append("<td style=\"border:1px solid black\">"+trb.getDescription()+"</td>");
			sb1.append("<td style=\"border:1px solid black\">"+trb.getOwners()+"</td>");
			sb1.append("<td style=\"border:1px solid black\">"+trb.getTarget()+"</td>");
			sb1.append("<td style=\"color:"+getStatusColor(trb.getStatus())+"\">"+trb.getStatus()+"</td>");
			sb1.append("<td style=\"border:1px solid black\">"+trb.getMinutes()+"</td>");
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
}
