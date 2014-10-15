package com.utils;

import java.nio.ByteBuffer;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;

import com.core.qassembler.constants.QConstants;
import com.utils.regex.RegexHandler;

public class Misc implements QConstants{
	
	public static byte getByteAt(long dword,int index){
		return ByteBuffer.allocate(8).putLong(dword).array()[7-index];
	}
	
	public static byte [] getByteSubFromTo(long dword,int from,int to){
		return Arrays.copyOfRange(ByteBuffer.allocate(8).putLong(dword).array(),8-from,8-to);
	}
	
	public static boolean arrayContains(int[] haystack,int needle){
		for(int i=0;i<haystack.length;i++) if(haystack[i]==needle) return true;
		return false;
	}
	
	public static List<Long> asList(final long [] l){
		return new AbstractList<Long>() {
	        public Long get(int i) {return l[i];}
	        // throws NPE if val == null
	        public Long set(int i, Long val) {
	            Long oldVal = l[i];
	            l[i] = val;
	            return oldVal;
	        }
	        public int size() { return l.length;}
	    };
	}
	
	public static long extractNumber(String str){
		String numberStr=(String)(RegexHandler.match(PATT_NUMBER,str, 0, null).get(0));
		if(numberStr.contains(".")) return Float.floatToIntBits(Float.parseFloat(numberStr)); // DEALING WITH FLOATS
		else return Long.parseLong(numberStr);
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