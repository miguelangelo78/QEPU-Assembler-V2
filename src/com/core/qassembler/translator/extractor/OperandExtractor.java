package com.core.qassembler.translator.extractor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.core.qassembler.constants.Global_Constants;
import com.utils.Misc;

public class OperandExtractor implements Global_Constants{

	public OperandExtractor() { }
	
	public String[] extract(String instruction){
		List<String> fetched_ops=new ArrayList<String>();
		
		if(!instruction.contains(",") && instruction.contains(" ")){ // THERE'S 1 OPERAND
			String [] tempVar=instruction.split(" ");
			fetched_ops.addAll(Arrays.asList(tempVar[FUNC],tempVar[tempVar.length-1])); // GET THE OPERAND 1
		}
		else if(instruction.contains(",")){ // THERE'S MORE THAN 1 OPERANDS
			List<String> tempVar=Arrays.asList(instruction.split(","));
			String[] tempVar2=tempVar.get(FUNC).split(" ");
			fetched_ops.addAll(Arrays.asList(tempVar2[FUNC],tempVar2[tempVar2.length-1])); // TO PREVENT MULTIPLE SPACES
			fetched_ops.addAll(tempVar.subList(1, tempVar.size()));
		}else fetched_ops.add(instruction); // GET FUNCTION (THERE'S ONLY THE FUNCTION ITSELF)
		fetched_ops.set(FUNC, fetched_ops.get(FUNC).toUpperCase());
		return Misc.trimAllList(fetched_ops).toArray(new String[fetched_ops.size()]);
	}
}