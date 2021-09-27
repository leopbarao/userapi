package com.leopbarao.userapi.utils;

public class StringUtil {

	public static boolean isNumeric(String s) {
		try {
			Long.parseLong(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
}
