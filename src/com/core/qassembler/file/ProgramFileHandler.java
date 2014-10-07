package com.core.qassembler.file;

import java.io.BufferedReader;
import java.io.FileReader;

import com.core.qassembler.constants.QConstants;
import com.utils.regex.RegexHandler;

public class ProgramFileHandler implements QConstants{
	
	private String program_filepath;
	private String program_name,program_name_format_preassembled,program_name_format_assembled;
	private String assembly_code;
	private String[] assembly_code_splitted;
	private int assembly_code_linecount;
	
	public ProgramFileHandler(String program_filepath) throws Exception{
		//CHECK IF THE FORMAT IS .QASM (OR EQUAL TO VAR FILESOURCE_FORMAT):
		String format=((String) (RegexHandler.match(PATT_FILEFORMAT,program_filepath , 0, null).get(0))).trim();
		if(!format.equals(FILESOURCE_FORMAT)) throw new Exception("The file format given was '."+format+"'. You need to use '."+FILESOURCE_FORMAT+"'");
		
		//THE FILE HAS A VALID FORMAT:
		this.program_filepath=program_filepath;
		
		//SET FILE FORMATS AND NAME:
		program_name_format_preassembled=((String)(RegexHandler.match(PATT_FILENAME,program_filepath,0,null).get(0))).trim();
		program_name_format_assembled=program_name_format_preassembled.replace("."+FILESOURCE_FORMAT,"."+FILEBINARY_FORMAT);
		program_name=program_name_format_preassembled.replace("."+FILESOURCE_FORMAT, "");
		this.program_filepath=this.program_filepath.replaceAll(program_name_format_preassembled,"");
		
		//SET ASSEMBLY CODE FOR THIS FILE:
		assembly_code=readFile(program_filepath);
		assembly_code_splitted=assembly_code.split("\\n");
		assembly_code_linecount=assembly_code_splitted.length;
	}
	
	public void updateSplittedLines(){
		assembly_code_splitted=this.assembly_code.split("\\n");
		assembly_code_linecount=assembly_code_splitted.length;
	}
	
	private String readFile(String filepath){
		String text="";
		try {
			BufferedReader br=new BufferedReader(new FileReader(filepath));
			String line="";
			while ((line= br.readLine()) != null) text+=line+"\n";
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}
	
	public String getLine(int index){
		return assembly_code_splitted[index].trim();
	}
	
	public String[] getLines(){
		return assembly_code_splitted;
	}
	
	public int getCodeLineCount(){
		return assembly_code_linecount;
	}
	
	public String getPreAssembledName(){
		return program_name_format_preassembled; // GET NAME WITH FORMAT BEFORE ASSEMBLED
	}
	
	public String getAssembledName(){
		return program_name_format_assembled; // GET NAME WITH FORMAT AFTER ASSEMBELD
	}
	
	public String getName(){
		return program_name;
	}
	
	public String getPath(){
		return program_filepath;
	}
	
	public String getFullPath(){
		return program_filepath+program_name_format_preassembled;
	}
	
	public String getFullBinPath(){
		return program_filepath+program_name_format_assembled;
	}
	
	public void setAssemblyCode(String assembly_code){
		this.assembly_code=assembly_code;
	}
	
	public String getAssemblyCode(){
		return assembly_code;
	}
}