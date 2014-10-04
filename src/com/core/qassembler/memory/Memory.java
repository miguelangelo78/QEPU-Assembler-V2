package com.core.qassembler.memory;

import com.core.qassembler.constants.QConstants;
import com.core.qassembler.file.MainProgramFile;
import com.core.qassembler.file.ProgramFileHandler;
import com.core.qassembler.includer.Includer;
import com.core.qassembler.memory.properties.Label;
import com.core.qassembler.memory.properties.Offset;
import com.core.qassembler.memory.properties.Variable;
import com.core.regex.RegexHandler;

public class Memory implements QConstants{
	private Includer includer; // INCLUDES FILES
	private Variable variable_handler; // VARIABLES TO BE DECLARED
	private Offset offset_handler; // OFFSETS TO BE USED
	private Label label_handler; // LABEL DECLARATOR
	private String basePath;
	
	public Memory(String mainFilePath){
		basePath=(String) RegexHandler.match(PATT_FILEPATH, mainFilePath, 0, null).get(0);
		includer=new Includer(mainFilePath,basePath);
	}
	
	public String getBasePath() {
		return basePath;
	}

	public Includer getIncluder() {
		return includer;
	}

	public Variable getVariableHandler() {
		return variable_handler;
	}

	public Offset getOffsetHandler() {
		return offset_handler;
	}

	public Label getLabelHandler() {
		return label_handler;
	}
	
	public MainProgramFile addLibraries(MainProgramFile mainFile) throws Exception{
		return includer.include(mainFile);
	}
	
	public void addVariable(String variable_name,int variable_bytelength){
		variable_handler.declare(variable_name, variable_bytelength);
	}
	
	public void addOffset(int start,int length){
		offset_handler.addOffset(start, length);
	}
	
	public void addLabel(String labelName,int address){
		label_handler.declare(labelName, address);
	}
}