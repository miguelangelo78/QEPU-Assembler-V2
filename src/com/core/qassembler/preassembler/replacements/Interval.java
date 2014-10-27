package com.core.qassembler.preassembler.replacements;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.core.qassembler.constants.QConstants;
import com.core.qassembler.file.MainProgramFile;
import com.utils.regex.RegexHandler;

public class Interval implements QConstants{
	public Interval(){
		
	}
	
	public MainProgramFile handleIntervals(MainProgramFile mainFile){
		String assembly=mainFile.getFile().getAssemblyCode();
		//SUBSTITUE '&' INTO NEW LINES
		assembly=assembly.replaceAll(PATT_INSTR_NEWLINE,"\n");
		//SUBSTITUTE THE INTERVAL BY MULTIPLE MOVS DETERMINED BY THE INTERVAL
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
    	
		// replace 'times x  ... endtimes' into its respective replacement
		List<Object> times_list=RegexHandler.match(PATT_TIMES,assembly, Pattern.CASE_INSENSITIVE|Pattern.DOTALL|Pattern.MULTILINE,new int[]{0,1,2});
		for(int i=0;i<times_list.size();i++){
			String fullmatch=((String[])times_list.get(i))[0];
			String to_replace=((String[])times_list.get(i))[2];
			String replacement="";
			int times_num=Integer.parseInt(((String[])times_list.get(i))[1]);
			if(times_num>0)
				for(int j=0;j<times_num;j++) replacement+=to_replace+(j<times_num-1?"\n":"");
			assembly=assembly.replace(fullmatch,replacement);
		}
		
		mainFile.getFile().setAssemblyCode(assembly);
		return mainFile;
	}
}