package com.utils.regex;

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
				String [] grouplist=new String[groups.length];
				for(int i=0;i<groups.length;i++)grouplist[i]=matcher.group(groups[i]);
				matchlist.add(grouplist);
			}
		return matchlist;
	}
}