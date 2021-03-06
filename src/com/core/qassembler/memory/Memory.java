package com.core.qassembler.memory;

import com.core.qassembler.constants.Global_Constants;
import com.core.qassembler.file.MainProgramFile;
import com.core.qassembler.memory.properties.Defines;
import com.core.qassembler.memory.properties.Includer;
import com.core.qassembler.memory.properties.Label;
import com.core.qassembler.memory.properties.Offset;
import com.core.qassembler.memory.properties.Variable;
import com.utils.regex.RegexHandler;

public class Memory implements Global_Constants{
	private Includer includer; // INCLUDES FILES
	private Variable variable_handler; // VARIABLES TO BE DECLARED
	private Defines defines_handler; // PRECOMPILER CONSTANTS TO BE DECLARED
	private Offset offset_handler; // OFFSETS TO BE USED
	private Label label_handler; // LABEL DECLARATOR
	private String basePath;
	
	public Memory(String mainFilePath){
		basePath=(String) RegexHandler.match(PATT_FILEPATH, mainFilePath, 0, null).get(0);
		includer=new Includer(mainFilePath,basePath);
		variable_handler=new Variable();
		defines_handler=new Defines();
		offset_handler=new Offset();
		label_handler=new Label();
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
	
	public Defines getDefinesHandler(){
		return defines_handler;
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
	
	public MainProgramFile handleVariables(MainProgramFile mainFile) throws Exception{
		return variable_handler.handleVariables(mainFile);
	}
	
	public MainProgramFile handleDefines(MainProgramFile mainFile) throws Exception{
		return defines_handler.handleDefines(mainFile);
	}
	
	public MainProgramFile handleLabels(MainProgramFile mainFile) throws Exception{
		return label_handler.handleLabels(mainFile);
	}
	
	public void handleOffsets(MainProgramFile mainFile){
		offset_handler.handleOffsets(mainFile);
	}
}