package com.core.qassembler.preassembler.replacements;

import java.util.List;
import java.util.regex.Pattern;

import com.core.qassembler.constants.Global_Constants;
import com.core.qassembler.file.MainProgramFile;
import com.utils.regex.RegexHandler;

public class StringInterval implements Global_Constants{
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
					String content=ConstantReplacements.fixEscapes(ops[1].replace("\"", ""));
					int containerAddress=Integer.parseInt(((String[])function_containerMatch.get(0))[1].trim());
					String buildStringReplacement="";
					int buffLength=0;
					
					if(ops.length>2) buffLength=Integer.parseInt(ops[2])-1;
					else buffLength=content.length();
					
					//MAKE STRING REPLACEMENTS:
					int j;
					for(j=0;j<buffLength;j++)
						if(j>=content.length()) buildStringReplacement+=function+" ["+(containerAddress+j)+"],0\n";
						else buildStringReplacement+=function+" ["+(containerAddress+j)+"],"+((int)content.charAt(j))+(j<buffLength-1?"\n":"");
					//buildStringReplacement+=function+" ["+(containerAddress+j)+"],0"; // THIS GIVES A FIXED STRING TERMINATOR TO THE STRING
					assembly=assembly.replace((String)stringMatch.get(i), buildStringReplacement);
				}
			}
		}
		//SUBSTITUTE CHARS INTO INTS:
		List<Object> charMatchList=RegexHandler.match(PATT_CHARCONSTANT, assembly, Pattern.MULTILINE, new int[]{0,1});
		for(int i=0;i<charMatchList.size();i++){
			String wholeMatch=((String[])charMatchList.get(i))[0];
			String charMatch=((String[])charMatchList.get(i))[1];
			char character=charMatch.charAt(0);
			if(character=='\\') //USING A ESCAPED SEQUENCE
				character=ConstantReplacements.fixEscapes(charMatch).charAt(0);
			assembly=assembly.replace(wholeMatch, ""+(int)character);
		}
		
		mainFile.getFile().setAssemblyCode(assembly);
		return mainFile;
	}
}
