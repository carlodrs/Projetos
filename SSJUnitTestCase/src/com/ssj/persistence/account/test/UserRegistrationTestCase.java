package com.ssj.persistence.account.test;

import java.util.Calendar;

import com.ssj.persistence.account.EClient;
import com.ssj.persistence.account.EClientSys;
import com.ssj.persistence.account.UserEmail;
import com.ssj.persistence.manager.SSJManagerEntity;

public class UserRegistrationTestCase {

	public static void main(String[] args) {
		
		EClient client = new EClient();
		client.setName("Carlos Renato Domingos da Silva");
		client.setCpf("29293578867");
		client.setGender("M");
		client.setOptIn('y');
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 1982);
		calendar.set(Calendar.MONTH, 7);
		calendar.set(Calendar.DATE, 4);
		
		client.setBirthday(calendar);
		
		UserEmail userEmail = new UserEmail();
		userEmail.setUser(client);
		userEmail.setEmail("carlosrenato.s@gmail.com");
		userEmail.setPassword("test");	
		
		
		EClientSys sysClient = new EClientSys();
		sysClient.setName("Isabel");
		sysClient.setName("Isabelzinha");
		
		sysClient.setCnpj("5555555555");
		
		UserEmail userEmail2 = new UserEmail();
		userEmail2.setEmail("belzinha@lojadabel.com");
		userEmail2.setUser(sysClient);
		
		SSJManagerEntity<UserEmail> managerEntity = new SSJManagerEntity<UserEmail>();
		try{
			
			managerEntity.create(userEmail);	
			managerEntity.create(userEmail2);
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Falha nao opera��o!!!");
		}finally{
			managerEntity.end();
		}
		
		
		
	}
	
}
