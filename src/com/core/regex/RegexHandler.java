package com.core.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexHandler {
	
	public static List<Object> match(String pattern,String text,int patternFlags,int[]groups){
		List<Object> matchlist=new ArrayList<Object>();
		Matcher matcher=Pattern.compile(pattern,patternFlags).matcher(text);
		while(matcher.find())
			if(groups==null || groups.length==0) matchlist.add(matcher.group(0));
			else{
				//CREATE MULTIDIMENSIONAL ARRAYS
			}
		return matchlist;
	}
}
