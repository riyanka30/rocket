package com.rocket.minutes.utils;

import java.io.InputStream;
import java.util.Properties;
import org.springframework.stereotype.Component;

@Component
public class PropertyReader {
	
	private static Properties props;
	
	public static String username;
	public static String password;
	public static String defaultTo;

	public static void setProperty(){
		//System.out.println("set property called");
		props = new Properties();
		try {
			// Load the server properties file
			InputStream is = PropertyReader.class.getClassLoader().getResourceAsStream(
					"account.properties");
			if (is == null) {
				System.err.println("Input stream for account.properties is null");
			}
			props.load(is);
			username=props.getProperty("username");
			password=props.getProperty("password");
			defaultTo=props.getProperty("defaultTo");
			
		} catch (Exception e) {
			System.err.println("Exception while reading property file account.properties");
			e.printStackTrace();
		}
	}
}
