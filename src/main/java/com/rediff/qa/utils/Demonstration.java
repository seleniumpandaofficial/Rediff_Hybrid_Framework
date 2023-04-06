package com.rediff.qa.utils;

public class Demonstration {

	public static void main(String[] args) {
	
		System.getProperties().list(System.out);
		
		System.out.println("---------------------------------------------------");
		
		System.out.println(System.getProperty("os.version"));
		System.out.println(System.getProperty("user.name"));
		System.out.println(System.getProperty("java.version"));

	}

}
