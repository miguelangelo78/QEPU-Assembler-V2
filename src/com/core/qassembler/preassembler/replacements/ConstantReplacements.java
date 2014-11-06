package com.core.qassembler.preassembler.replacements;

import java.util.List;
import java.util.regex.Pattern;
import com.core.qassembler.constants.Global_Constants;
import com.core.qassembler.file.MainProgramFile;
import com.core.qassembler.memory.Memory;
import com.utils.regex.RegexHandler;

public class ConstantReplacements implements Global_Constants{
	public ConstantReplacements(){ }
	
	public static String fixEscapes(String src){
		// Escapes: \a \b \f \n \r \t \v \\ \' \" \? 
		String [] escapeSequence=new String[]{"\\a","\\b","\\f","\\n","\\r","\\t","\\v","\\","\\'","\\","\\?"};
		int [] escapeInts=new int[]{0x07,0x08,0x0C,0x0A,0x0D,0x09,0x0B,0x5C,0x27,0x22,0x3F};
		
		src=src.replace("\\0","\0"); // REPLACE ESCAPED STRING TERMINATOR WITH AN UNESCAPED STRING TERMINADOR
		for(int i=0;i<escapeSequence.length;i++){
			StringBuilder strBldr=new StringBuilder(src);
			while(src.contains(escapeSequence[i])){
				int replacement_index=src.indexOf(escapeSequence[i]);
	            strBldr.setCharAt(replacement_index, (char)escapeInts[i]);
	            strBldr.deleteCharAt(replacement_index+1);
	            src=strBldr.toString();
	        }
		}
        return src;
	}
	
	public static MainProgramFile replaceAll(MainProgramFile mainFile,Memory assemblerMemory){
		String assembly=mainFile.getFile().getAssemblyCode();
		// FIX CHARACTERS '_' BEING NEXT TO CONSTANTS (BOUNDARY BUG):
		assembly=assembly.replace("_"," _ ");
				
		// replace binary, hexadecimal and octal numbers (in strings) into decimal numbers (in strings)
		List<Object> basesMatch=RegexHandler.match(PATT_ALLBASES, assembly, Pattern.MULTILINE|Pattern.CASE_INSENSITIVE, null);
		for(int i=0;i<basesMatch.size();i++){
			String number=((String)basesMatch.get(i));
			switch(number.substring(0,2).toUpperCase()){ // BASE SWITCH
				case "0X": assembly=assembly.replace(number, Long.toString(Long.parseLong(number.replaceAll("0[xX]", ""),16))); break;
				case "0O": assembly=assembly.replace(number, Long.toString(Long.parseLong(number.replaceAll("0[oO]", ""),8)));break;
				case "0B": assembly=assembly.replace(number, Long.toString(Long.parseLong(number.replaceAll("0[bB]", ""),2))); break;
			}
		}
		
		// replace $ into the line
		String [] assembly_Splitted=assembly.split("\\n");
		assembly="";
		for(int i=0;i<assembly_Splitted.length;i++){
			if(RegexHandler.match(PATT_CURRLINE, assembly_Splitted[i], 0, null).size()>0) assembly_Splitted[i]=assembly_Splitted[i].replaceAll(PATT_CURRLINE,""+i);
			assembly+="\n"+assembly_Splitted[i];
		}
		
		// replace the constant registers into their address
		for (String key : CREGISTER_CONTAINER.keySet())   assembly=assembly.replaceAll("(?i)\\b"+key+"\\b"+PATT_TEMPLATE_OUTSIDEQUOTES,  CREGISTER_CONTAINER.get(key));
		for (String key : CREGISTER_NOCONTAINER.keySet()) assembly=assembly.replaceAll("(?i)\\b"+key+"\\b"+PATT_TEMPLATE_OUTSIDEQUOTES,  CREGISTER_NOCONTAINER.get(key));
		for (String key : QREGISTER_CONTAINER.keySet())   assembly=assembly.replaceAll("(?i)\\b"+key+"\\b"+PATT_TEMPLATE_OUTSIDEQUOTES,  QREGISTER_CONTAINER.get(key));
		for (String key : QREGISTER_NOCONTAINER.keySet()) assembly=assembly.replaceAll("(?i)\\b"+key+"\\b"+PATT_TEMPLATE_OUTSIDEQUOTES,  QREGISTER_NOCONTAINER.get(key));
		for (String key : CREGISTER_OFF.keySet())   	  assembly=assembly.replaceAll("(?i)\\b"+key+"\\b"+PATT_TEMPLATE_OUTSIDEQUOTES,  CREGISTER_OFF.get(key));
		for (String key : QREGISTER_OFF.keySet()) 		  assembly=assembly.replaceAll("(?i)\\b"+key+"\\b"+PATT_TEMPLATE_OUTSIDEQUOTES,  QREGISTER_OFF.get(key));
		for (String key : QBIT_ALL.keySet()) 			  assembly=assembly.replaceAll("(?i)\\b"+key+"\\b"+PATT_TEMPLATE_OUTSIDEQUOTES,  QBIT_ALL.get(key));
		for (String key : QBIT_THETA.keySet()) 			  assembly=assembly.replaceAll("(?i)\\b"+key+"\\b"+PATT_TEMPLATE_OUTSIDEQUOTES,  QBIT_THETA.get(key));
		for (String key : QBIT_PHI.keySet()) 			  assembly=assembly.replaceAll("(?i)\\b"+key+"\\b"+PATT_TEMPLATE_OUTSIDEQUOTES,  QBIT_PHI.get(key));
		
		assembly=assembly.replaceAll(" _ ", "_"); // FIX _CONSTANT_ BUG BECAUSE OF BOUNDARIES
		mainFile.getFile().setAssemblyCode(assembly);
		return mainFile;
	}
}
