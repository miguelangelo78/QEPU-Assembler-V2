package com.utils;

import java.util.List;

import com.utils.regex.RegexHandler;

public class Misc {
	
	public static boolean arrayContains(int[] haystack,int needle){
		for(int i=0;i<haystack.length;i++) if(haystack[i]==needle) return true;
		return false;
	}
	
	public static int extractNumber(String str){
		return Integer.parseInt((String)(RegexHandler.match("[0-9]+",str, 0, null).get(0)));
	}
	
	public static List<String> trimAllList(List<String> ops){
		for(int i=0;i<ops.size();i++) ops.set(i, ops.get(i).trim());
		return ops;
	}
	
	public static String arrToString(String[]arr){
		String buff="(";
		int arr_length=arr.length;
		for(int i=0;i<arr_length;i++) buff+=arr[i]+((i<arr_length-1)?",":"");
		return buff+")";
	}
}