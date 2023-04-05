package com.rediff.qa.utils;

import java.util.Date;

public class TimeStampExplanation {

	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(date.toString().replace(" ", "_").replace(":", "_").substring(8, 19).replace("_", ""));

	}

}
