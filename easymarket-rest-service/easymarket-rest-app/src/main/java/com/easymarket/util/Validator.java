package com.easymarket.util;

import org.apache.commons.lang3.StringUtils;

public class Validator {

	public static boolean isEmpty(String message) {
		String value = StringUtils.trimToEmpty(message);
		
		boolean result = StringUtils.isEmpty(value);
		return result;
	}
	
	public static void throwExcetionValueNotEspecifiedIfMessageIsEmpty(String label,String message) throws Exception{
		if(isEmpty(message)){
			throwExceptionValueNotSpecified(label);
		}
	}
	
	public static boolean throwExceptionValueNotSpecified(String label) throws Exception{
	    Exception cause = new IllegalArgumentException(label+" was not specified");
        throw cause;
	}
}
