package com.kh.portfolio.common.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class PropertiesEncryptor {

	public static void main(String[] args) {
		StandardPBEStringEncryptor enc = new StandardPBEStringEncryptor();
		//암호키 root-context.xml에 
		//<bean id="configurationEncryptor" class="org.jasypt.encryption.pbe.StandardPBEStringEncryptor">
		//안의 password와 같은 값이 있어야 인코딩이 가능해짐
		enc.setPassword("gkrToaWkd");
		
		//db암호화
		System.out.println(enc.encrypt("oracle.jdbc.driver.OracleDriver"));
		System.out.println(enc.encrypt("jdbc:oracle:thin:@127.0.0.1:1521:xe"));
		System.out.println(enc.encrypt("portfolio"));
		System.out.println(enc.encrypt(""));
		
		//mail암호화
		System.out.println(enc.encrypt("dkrlrhdrur@gmail.com"));
		System.out.println(enc.encrypt(""));
		
		//복호화
		System.out.println(enc.decrypt("+4J2oANlBNioZ683EmH10yBIoywZn/cPksqISgFBLTI="));
	}

}
