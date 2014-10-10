package com.core.qassembler.memory.properties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.core.qassembler.constants.QConstants;
import com.core.qassembler.file.MainProgramFile;
import com.core.qassembler.preassembler.replacements.ConstantReplacements;
import com.utils.regex.RegexHandler;

public class Offset implements QConstants{
	private Map<Integer,Integer> offset_list;

	public Offset(){
		offset_list=new HashMap<Integer,Integer>();
	}
	
	public int getJumpOffset(int jump_address){
		int new_jump_address=jump_address;
		for(Integer key:offset_list.keySet()) if(jump_address>key) new_jump_address+=offset_list.get(key)-1;
	    return new_jump_address-1;
	}
	
	public Map<Integer, Integer> getOffsetList() {
		return offset_list;
	}
	
	private void addOffset(int start,int length){
		offset_list.put(start,length);
	}
	
	public void handleOffsets(MainProgramFile mainFile){
		String []assemblySplitted=mainFile.getFile().getAssemblyCode().split("\\n");
		for(int line=0;line<assemblySplitted.length;line++){
			//SET VARIABLES' OFFSET
			List<Object> variableMatches=RegexHandler.match(PATT_VARIABLEDECL, assemblySplitted[line], Pattern.MULTILINE, null);
			if(variableMatches.size()>0){
				String [] ops=((String)variableMatches.get(0)).split(",");
				if(ops.length>2) addOffset(line+1, Integer.parseInt(ops[2].trim()));
				else if(ops.length==2) addOffset(line+1, ops[1].length()-1);
			}
			
			//SET STRINGS' OFFSET
			List<Object> stringMatches=RegexHandler.match(PATT_STRINGCONSTANT, assemblySplitted[line], 0, new int[]{1});
			if(stringMatches.size()>0) addOffset(line+1,ConstantReplacements.fixEscapes(((String[])stringMatches.get(0))[0]).length()+1);
			
			//SET INTERVALS' OFFSET
			List<Object> intervalMatches=RegexHandler.match(PATT_INTERVAL, assemblySplitted[line], 0, new int[]{1,2});
			if(intervalMatches.size()>0){
				int intervalStart=Integer.parseInt(((String[])intervalMatches.get(0))[0]);
				int intervalEnd=Integer.parseInt(((String[])intervalMatches.get(0))[1]);
				addOffset(line+1,Math.abs(intervalStart-intervalEnd)+1);
			}
		}
	}
}