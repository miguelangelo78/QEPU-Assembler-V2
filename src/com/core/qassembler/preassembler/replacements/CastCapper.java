package com.core.qassembler.preassembler.replacements;

import java.util.List;

import com.core.qassembler.constants.Global_Constants;
import com.utils.Misc;
import com.utils.regex.RegexHandler;

/**THIS CLASS TRANSFORMS A STRING INTO A LONG NUMBER AND IF IT'S USING CAST, IT CAPS THE NUMBER SIZE**/
public class CastCapper implements Global_Constants{
	
	public CastCapper(){}
	
	public static int getCastSize(String op){
		// RETURN THE SIZE OF THE CAST USING THE CAST LIST AND ITS CORRESPONDENT SIZES:
		// TRY TO MATCH THE CONSTANT TYPE SIZES (BIT,NIBBLE,BYTE,WORD,DWORD)
		for(String dimensionPatt:CASTING_LIST.keySet())
			if(RegexHandler.match(dimensionPatt, op, 0,null).size()>0) return CASTING_LIST.get(dimensionPatt);
		
		// TRY TO MATCH SIZEX
		List<Object> sizex_match=RegexHandler.match(CAST_CUSTOM, op, 0, new int[]{1});
		if(sizex_match.size()>0) return Integer.parseInt(((String[])sizex_match.get(0))[0]);
		
		// TRY TO MATCH AUTO
		List<Object> autox_match=RegexHandler.match(CAST_AUTO, op, 0,null);
		if(autox_match.size()>0){
			// TODO: DO LOTS OF COMPLICATED STUFF HERE
		}
		return -1;
	}
	
	public static boolean usingCast(String op){
		// USE THE CAST LIST, SIZEX AND AUTO TYPE SIZES TO DETERMINE WHETHER OR NOT THERE'S A CAST GOING ON THE OPERAND
		for(String dimensionPatt:CASTING_LIST.keySet()) if(RegexHandler.match(dimensionPatt, op, 0,null).size()>0) return true;
		if(RegexHandler.match(CAST_CUSTOM, op, 0,null).size()>0) return true;
		if(RegexHandler.match(CAST_AUTO, op, 0,null).size()>0) return true;	
		return false;
	}
	
	public static String removeCast(String operand){
		for(String dimensionPatt:CASTING_LIST.keySet())
			operand=operand.replaceAll(dimensionPatt,"");
		operand=operand.replaceAll(CAST_CUSTOM, "");
		operand=operand.replaceAll(CAST_AUTO, "");
		return operand.trim();
	}
	
	public static String cap(String operand){
		// SEE IF IT'S USING A CAST, IF IT IS, EXTRACT THE NUMBER, CAP IT, AND RETURN IT
		if(!usingCast(operand)) return operand;
		int bit_count=getCastSize(operand);
		operand=removeCast(operand);
		String operand_number=String.format("%"+SYS_STD_SIZE+"s",Long.toBinaryString(Misc.extractNumber(operand))).replace(' ', '0');
		for(int i=bit_count;i<operand_number.length();i++)
			operand_number=Misc.setCharAt(operand_number, (operand_number.length()-1)-i,'0'); // CAP THE NUMBER:
		return operand.replaceAll(PATT_NUMBER, ""+Integer.parseInt(operand_number,2)); // REMOVE THE CAST,CONVERT BINARY TO INT, INT TO STR AND REPLACE THE OLD NUMBER BY THE NEW ONE
	}
}
