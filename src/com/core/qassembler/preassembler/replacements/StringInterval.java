package com.core.qassembler.preassembler.replacements;

import java.util.List;
import java.util.regex.Pattern;

import com.core.qassembler.constants.QConstants;
import com.core.qassembler.file.MainProgramFile;
import com.utils.regex.RegexHandler;

public class StringInterval implements QConstants{
	public StringInterval(){}
	
	public MainProgramFile replaceAll(MainProgramFile mainFile){
		//SUBSTITUTE STRINGS INTO MULTIPLE MOVS OF INTS
		String assembly=mainFile.getFile().getAssemblyCode();
		String [] assemblySplitted=assembly.split("\\n");
		for(int line=0;line<assemblySplitted.length;line++){
			List<Object> stringMatch=RegexHandler.match(PATT_STRINGCONSTANT, assemblySplitted[line], Pattern.MULTILINE, null);
			if(stringMatch.size()>0){
				for(int i=0;i<stringMatch.size();i++) {
					//IMPORTANT VARS:
					String [] ops=((String)stringMatch.get(i)).split(",");
					List<Object> function_containerMatch=RegexHandler.match(PATT_FUNCIONCONTAINER, ops[0],0, new int[]{1,2});
					String function=((String[])function_containerMatch.get(0))[0].trim();
					String content=ops[1].replace("\"", "").trim();
					int containerAddress=Integer.parseInt(((String[])function_containerMatch.get(0))[1].trim());
					String buildStringReplacement="";
					int buffLength=0;
					
					if(ops.length>2) buffLength=Integer.parseInt(ops[2])-1;
					else buffLength=content.length();
					
					//MAKE STRING REPLACEMENTS:
					int j;
					for(j=0;j<buffLength;j++)
						if(j>=content.length()) buildStringReplacement+=function+" ["+(containerAddress+j)+"],0\n";
						else buildStringReplacement+=function+" ["+(containerAddress+j)+"],"+((int)content.charAt(j))+"\n";
					buildStringReplacement+=function+" ["+(containerAddress+j)+"],0";
					assembly=assembly.replace((String)stringMatch.get(i), buildStringReplacement);
				}
			}
		}
		//SUBSTITUTE CHARS INTO INTS:
		List<Object> charMatch=RegexHandler.match(PATT_CHARCONSTANT, assembly, Pattern.MULTILINE, new int[]{1});
		for(int i=0;i<charMatch.size();i++){
			char character=((String[])charMatch.get(i))[0].charAt(0);
			assembly=assembly.replace("'"+character+"'", ""+(int)character);
		}
		mainFile.getFile().setAssemblyCode(assembly);
		return mainFile;
	}
}