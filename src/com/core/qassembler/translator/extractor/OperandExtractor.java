package com.core.qassembler.translator.extractor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.core.qassembler.constants.Global_Constants;
import com.utils.Misc;

public class OperandExtractor implements Global_Constants{

	public OperandExtractor() { }
	
	public Object[] extract(String instruction){
		List<String> fetched_ops=new ArrayList<String>();
		int fetched_ops_length=0;
		
		if(!instruction.contains(",") && instruction.contains(" ")){ // THERE'S 1 OPERAND
			String [] tempVar=instruction.split(" ");
			fetched_ops.addAll(Arrays.asList(tempVar[FUNC],tempVar[tempVar.length-1])); // GET THE OPERAND 1
			fetched_ops_length=fetched_ops.size();
		}
		else if(instruction.contains(",")){ // THERE'S MORE THAN 1 OPERANDS
			// TODO: PROPERLY EXTRACT OPERAND 1 WHICH MAY CONTAIN EXTRA SPACES:
			
			List<String> tempVar=Arrays.asList(instruction.split(","));
			fetched_ops_length=tempVar.size();
			String[] tempVar2=tempVar.get(FUNC).split(" ");
			if(tempVar2[1]!=null && CASTING_LIST.containsKey("(?i)\\b"+tempVar2[1].toUpperCase()+"\\b")){
				fetched_ops.addAll(Arrays.asList(tempVar2[FUNC],tempVar2[FUNC+1]+" "+tempVar2[tempVar2.length-1])); // TO PREVENT MULTIPLE SPACES
			}else
				fetched_ops.addAll(Arrays.asList(tempVar2[FUNC],tempVar2[tempVar2.length-1])); // TO PREVENT MULTIPLE SPACES
			fetched_ops.addAll(tempVar.subList(1, tempVar.size()));
		}else fetched_ops.add(instruction); // GET FUNCTION (THERE'S ONLY THE FUNCTION ITSELF)
		fetched_ops.set(FUNC, fetched_ops.get(FUNC).toUpperCase());
		return new Object[]{Misc.trimAllList(fetched_ops).toArray(new String[fetched_ops.size()]),(Object)fetched_ops_length};
	}
}