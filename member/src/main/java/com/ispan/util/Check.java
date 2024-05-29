package com.ispan.util;

public class Check {

	public static boolean containsColume(String[] columes,String colume) {
		for(String col : columes) {
			if (col.equalsIgnoreCase(colume)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isNumber(String string) {
		for(char c : string.toCharArray()) {
			if(!Character.isDigit(c) && c != '.') {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isDate(String string) {
		for(char c : string.toCharArray()) {
			if(!Character.isDigit(c) && c != '-' && c != '/') {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isCsv(String filePath) {
		boolean b = false;
		for(char c : filePath.toCharArray()) {
			switch (c) {
			case '.':
				b = true;
				break;
			case 'c':
				b = b? true:false;
				break;
			case 's':
				b = b? true:false;
				break;
			case 'v':
				b = b? true:false;
				break;

			default:
				b = false;
				break;
			}
		}
		return b;
	}
}
