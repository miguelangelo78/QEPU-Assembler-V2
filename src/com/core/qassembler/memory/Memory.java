package com.core.qassembler.memory;

import com.core.qassembler.file.ProgramFileHandler;
import com.core.qassembler.includer.Includer;
import com.core.qassembler.memory.properties.Label;
import com.core.qassembler.memory.properties.Offset;
import com.core.qassembler.memory.properties.Variable;

public class Memory {
	private Includer includer; // INCLUDES FILES
	private Variable variable_handler; // VARIABLES TO BE DECLARED
	private Offset offset_handler; // OFFSETS TO BE USED
	private Label label_handler; // LABEL DECLARATOR
	
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
	
	public void addLibrary(ProgramFileHandler libfile){
		includer.include(libfile);
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
	
	public Memory(String mainFilePath){
		includer=new Includer(mainFilePath);
	}
}