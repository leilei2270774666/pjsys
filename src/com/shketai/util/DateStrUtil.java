package com.shketai.util;

public class DateStrUtil {

	/**
	 * 截取时间段中的分号
	 * @param s
	 * @return
	 */
	public static String sub(String s){
		String s1 = s.substring(0,2);
		String s2 = s.substring(3);
		return s1 + s2;
	}
	
	
	/**
	 * 比较两个时间的大小
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int compare(String s1,String s2){

		int i1 = Integer.parseInt(sub(s1));
		int i2 = Integer.parseInt(sub(s2));
		if(i1 > i2){
			return 1;
		}else if(i1 == i2){
			return 0;
		}else{
			return -1;
		}
	}
	
	
}
