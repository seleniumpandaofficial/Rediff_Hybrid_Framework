package com.rediff.qa.utils;

import java.util.Date;

public class Utilities {
	
	public static void main(String[] args) {
		generateNameforEmailWithTimeStamp();
	}

	public static String generateEmailWithTimeStamp() {
		Date date = new Date();
		String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "seleniumpanda" + timeStamp + "@rediffmail.com";
	}
	
	public static String generateNameforEmailWithTimeStamp() {
		Date date = new Date();
		String timeStamp = date.toString().replace(" ", "_").replace(":", "_").substring(8, 19).replace("_", "");
		return "seleniumpanda" + timeStamp;
	
	}

	public static final int implitWaitTime = 10;
	public static final int pageLoadTime = 10;
	public static final int scriptTime = 1000;

}
