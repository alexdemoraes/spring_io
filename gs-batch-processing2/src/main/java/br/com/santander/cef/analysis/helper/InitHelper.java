package br.com.santander.cef.analysis.helper;

import java.math.BigDecimal;
import java.util.Date;

public class InitHelper {
	
	public static Integer intType(){
		return 0;
	}
	
	public static String stringType(){
		return "";
	}
	
	public static Long longType(){
		return Long.valueOf(0);
	}
	
	public static Date dateType(){
		return new Date();
	}
	
	public static Short shortType(){
		return 0;
	}

	public static Byte byteType() {
		return 0;
	}

	public static BigDecimal bigDecimalType() {
		return BigDecimal.ZERO;
	}

	public static Boolean booleanType() {
		return Boolean.FALSE;
	}

	public static Float floatType() {
		return Float.valueOf(0);
	}

}
