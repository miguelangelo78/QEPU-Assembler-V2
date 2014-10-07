package com.utils;

import java.util.List;

public class Misc {
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