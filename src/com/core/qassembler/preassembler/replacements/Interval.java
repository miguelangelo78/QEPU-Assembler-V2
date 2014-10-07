package com.core.qassembler.preassembler.replacements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.core.qassembler.constants.QConstants;
import com.core.qassembler.file.MainProgramFile;

public class Interval implements QConstants{
	public Interval(){
		
	}
	
	public MainProgramFile handleIntervals(MainProgramFile mainFile){
		//SUBSTITUTE THE INTERVAL BY MULTIPLE MOVS DETERMINED BY THE INTERVAL
		String assembly=mainFile.getFile().getAssemblyCode();
		Pattern intervals_patt=Pattern.compile(PATT_INTERVAL,Pattern.CASE_INSENSITIVE|Pattern.MULTILINE);
    	for(String line:assembly.split("\\n")){
    		Matcher intervals_match=intervals_patt.matcher(line.trim());
    		String interval_substitute="";
    		
    		while(intervals_match.find()){
    			int start_interval=Integer.parseInt(intervals_match.group(1).trim());
        		int end_interval=Integer.parseInt(intervals_match.group(2).trim());
    			
        		//IF THERE'S 1 INTERVAL:
        		if(start_interval<=end_interval)
            		for(int i=start_interval;i<=end_interval;i++) interval_substitute+=line.replaceFirst(intervals_match.group(0),Integer.toString(i))+"\n";
        		else
        			for(int i=start_interval;i>=end_interval;i--) interval_substitute+=line.replaceFirst(intervals_match.group(0),Integer.toString(i))+"\n";
        		
        		//IF THERE'S MORE THAN 1 INTERVAL:
        		while(intervals_match.find()){
        			start_interval=Integer.parseInt(intervals_match.group(1).trim());
        			end_interval=Integer.parseInt(intervals_match.group(2).trim());
        			
        			if(start_interval<=end_interval)
            			for(int i=start_interval;i<=end_interval;i++) interval_substitute=interval_substitute.replaceFirst(intervals_match.group(0), Integer.toString(i)+" ");
            		else 
            			for(int i=start_interval;i>=end_interval;i--) interval_substitute=interval_substitute.replaceFirst(intervals_match.group(0), Integer.toString(i)+" ");
            	}
        		
        		//CHECK FOR LEFTOVER INTERVALS:
        		intervals_match=intervals_patt.matcher(interval_substitute);
        		while(intervals_match.find()) interval_substitute=interval_substitute.replaceAll(".+?"+intervals_match.group(0)+"\n", "");
        		
        		//LINE IS PREPARED TO BE REPLACED BY THE PROPER INTERVAL:
        		assembly=assembly.replace(line+"\n",interval_substitute);
        		break;
        	}
    	}
		mainFile.getFile().setAssemblyCode(assembly);
		return mainFile;
	}
}