package com.rocket.minutes.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertyReader {
	
	@Value("${username}")
	public String username;
	
	@Value("${password}")
	public String password;
	
	@Value("${defaultTo}")
	public String defaultTo;

	public PropertyReader() {
	//	setProperty();
	}
	
	/*private static void setProperty(){
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
	}*/
}
