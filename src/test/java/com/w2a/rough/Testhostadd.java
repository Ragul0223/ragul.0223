package com.w2a.rough;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.w2a.utilities.MonitoringMail;
import com.w2a.utilities.TestConfig;

public class Testhostadd {

	public static void main(String[] args) throws IOException, AddressException, MessagingException {
		// TODO Auto-generated method stub
		
		MonitoringMail mail = new MonitoringMail();
		String messegebody="http://"+InetAddress.getLocalHost().getHostAddress()+ ":8080/job/Liveproject/Extent_20Reports/";
		System.out.println(messegebody);
		mail.sendMail(TestConfig.server,TestConfig.from,TestConfig.to,TestConfig.subject,messegebody);

	}

}
