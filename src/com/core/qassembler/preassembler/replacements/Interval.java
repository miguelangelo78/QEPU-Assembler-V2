package com.core.qassembler.preassembler.replacements;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.core.qassembler.constants.Global_Constants;
import com.core.qassembler.file.MainProgramFile;
import com.utils.Misc;
import com.utils.regex.RegexHandler;

public class Interval implements Global_Constants{
	public Interval(){
		
	}
	
	public MainProgramFile handleIntervals(MainProgramFile mainFile){
		String assembly=mainFile.getFile().getAssemblyCode();
		//SUBSTITUE '&' INTO NEW LINES
		assembly=assembly.replaceAll(PATT_INSTR_NEWLINE,"\n");
		
		Pattern intervals_patt=Pattern.compile(PATT_INTERVAL,Pattern.CASE_INSENSITIVE|Pattern.MULTILINE);
    	for(String line:assembly.split("\\n")){
    		Matcher intervals_match=intervals_patt.matcher(line.trim());
    		String interval_substitute="";
    		while(intervals_match.find()){
    			float deincrement_amount=1;
    			float start_interval=Float.parseFloat(intervals_match.group(1).trim());
        		float end_interval=Float.parseFloat(intervals_match.group(2).trim());
        		if(intervals_match.group(3)!=null){ // HOW MUCH THE INTERVAL WILL INCREMENT/DECREMENT BY
        			deincrement_amount=Math.abs(Float.parseFloat(intervals_match.group(3)));
        			if(deincrement_amount==0) deincrement_amount=1;
        		}
        		//IF THERE'S 1 INTERVAL:
        		if(start_interval<=end_interval)
            		for(float i=start_interval;i<=end_interval;i+=deincrement_amount) interval_substitute+=line.replaceFirst(RegexHandler.sanitizeString(intervals_match.group(0)),Misc.formatFloat(i))+"\n";
        		else
        			for(float i=start_interval;i>=end_interval;i-=deincrement_amount) interval_substitute+=line.replaceFirst(RegexHandler.sanitizeString(intervals_match.group(0)),Misc.formatFloat(i))+"\n";
        		
        		//IF THERE'S MORE THAN 1 INTERVAL:
        		while(intervals_match.find()){
        			start_interval=Float.parseFloat(intervals_match.group(1).trim());
        			end_interval=Float.parseFloat(intervals_match.group(2).trim());
        			deincrement_amount=1; // GET THE DEINCREMENT OF THE OTHER INTERVALS
        			if(intervals_match.group(3)!=null){ // HOW MUCH THE INTERVAL WILL INCREMENT/DECREMENT BY
            			deincrement_amount=Math.abs(Float.parseFloat(intervals_match.group(3)));
            			if(deincrement_amount==0) deincrement_amount=1;
            		}
        			
        			int max_interval_length=(int)(Math.abs((end_interval-start_interval))/deincrement_amount+1); // MAX COUNT NUMBER THAT 'FITS' THE INTERVAL
        			int loop_ctr=0;
        			String new_interval_substitute="";
    				
        			if(start_interval<=end_interval){ // THERE MAY BE REPETITIONS ON INTERVALS, SO THE ALGORITHM GETS MORE COMPLICATED:
        				for(String interval_line:interval_substitute.split("\\n"))
        					if(loop_ctr++<max_interval_length){ new_interval_substitute+=interval_line.replaceAll(RegexHandler.sanitizeString(intervals_match.group(0)), Misc.formatFloat(start_interval)+"")+"\n"; start_interval+=deincrement_amount;	}
        					else break;
        				interval_substitute=new_interval_substitute;
        			}else{ // THERE MAY BE REPETITIONS ON INTERVALS, SO THE ALGORITHM GETS MORE COMPLICATED:
        				for(String interval_line:interval_substitute.split("\\n"))
        					if(loop_ctr++<max_interval_length){ new_interval_substitute+=interval_line.replaceAll(RegexHandler.sanitizeString(intervals_match.group(0)), Misc.formatFloat(start_interval)+"")+"\n"; start_interval-=deincrement_amount;	}
        					else break;
        				interval_substitute=new_interval_substitute;
        			}
        		}
        		
        		//CHECK FOR LEFTOVER INTERVALS (DELETE THE ONES THAT DON'T NEED TO BE THERE):
        		intervals_match=intervals_patt.matcher(interval_substitute);
        		while(intervals_match.find()) interval_substitute=interval_substitute.replaceAll("(?m).+?"+RegexHandler.sanitizeString(intervals_match.group(0))+"(?:.+|$)", "");
        		
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