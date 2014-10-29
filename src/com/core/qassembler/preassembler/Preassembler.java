package com.core.qassembler.preassembler;

import java.util.List;
import java.util.regex.Pattern;
import com.core.qassembler.constants.Global_Constants;
import com.core.qassembler.file.MainProgramFile;
import com.core.qassembler.memory.Memory;
import com.core.qassembler.memory.properties.Includer;
import com.core.qassembler.preassembler.expression.ExpressionResolver;
import com.core.qassembler.preassembler.replacements.ConstantReplacements;
import com.core.qassembler.preassembler.replacements.Interval;
import com.core.qassembler.preassembler.replacements.StringInterval;
import com.utils.regex.RegexHandler;

public class Preassembler implements Global_Constants{
	private Memory assemblerMemory;
	private ExpressionResolver expResolver;
	private Interval intervals;
	private StringInterval stringIntervals;

	public Preassembler(String mainFilePath){
		assemblerMemory=new Memory(mainFilePath);
		expResolver=new ExpressionResolver();
		intervals=new Interval();
		stringIntervals=new StringInterval();
		for(char c='A';c<='A'+CREGISTER_COUNT;c++) CREGISTER_CONTAINER.put(c+"X", "{"+(c-'A')+"}"); // INICIALIZE REGISTER CONTAINERS
		for(char c='A';c<='A'+CREGISTER_COUNT;c++) CREGISTER_NOCONTAINER.put(c+"R", ""+(c-'A')); // INICIALIZE REGISTER CONTAINERS
		for(char c='A';c<='A'+CREGISTER_COUNT;c++) QREGISTER_CONTAINER.put("Q"+c+"X", "%"+(c-'A')+"%"); // INICIALIZE REGISTER CONTAINERS
		for(char c='A';c<='A'+CREGISTER_COUNT;c++) QREGISTER_NOCONTAINER.put("Q"+c+"R", ""+(c-'A')); // INICIALIZE REGISTER CONTAINERS
		for(char c='A';c<='A'+CREGISTER_COUNT;c++) CREGISTER_OFF.put(c+"O",""+(c-'A')*CREGISTER_SIZE); // INICIALIZE REGISTER OFFSETS
		for(char c='A';c<='A'+QREGISTER_COUNT;c++) QREGISTER_OFF.put("Q"+c+"O",""+(c-'A')*QREGISTER_SIZE); // INICIALIZE REGISTER OFFSETS
			
		for(int i=0;i<QREGISTER_COUNT*QREGISTER_SIZE;i++) QBIT_ALL.  put("Q"+i,  "<"+i+">");
		for(int i=0;i<QREGISTER_COUNT*QREGISTER_SIZE;i++) QBIT_THETA.put("QT"+i, "|"+i+"|");
		for(int i=0;i<QREGISTER_COUNT*QREGISTER_SIZE;i++) QBIT_PHI.  put("QP"+i, "!"+i+"!");
	}
	
	public Includer getIncluder() {
		return assemblerMemory.getIncluder();
	}
	
	public Memory getAssemblerMemory() {
		return assemblerMemory;
	}
	
	private MainProgramFile handleIncluding(MainProgramFile mainFile) throws Exception{
		return assemblerMemory.addLibraries(mainFile);
	}
	
	private MainProgramFile handleExpressions(MainProgramFile mainFile){
		return expResolver.resolve(mainFile, assemblerMemory);
	}
	
	private MainProgramFile handleConstantReplacements(MainProgramFile mainFile){
		return ConstantReplacements.replaceAll(mainFile, assemblerMemory);
	}
	
	private MainProgramFile handleLabels(MainProgramFile mainFile) throws Exception{
		return assemblerMemory.handleLabels(mainFile);
	}
	
	private MainProgramFile handleCommentsAndEmptyLines(MainProgramFile mainFile){
		// CLEAN SINGLE LINE COMMENTS, MULTILINE COMMENTS AND EMPTY LINES:
		String assembly=mainFile.getFile().getAssemblyCode();
		List<Object> commentsMatch=RegexHandler.match(PATT_COMMENT, assembly, Pattern.MULTILINE, null);
		for(int i=0;i<commentsMatch.size();i++) assembly=assembly.replace((String)commentsMatch.get(i), "");
		mainFile.getFile().setAssemblyCode(assembly.replaceAll(PATT_EMPTYLINE, "")); //REMOVE EMPTY LINES: (APPEND NEWLINE BECAUSE OF THE SPLIT("\\n"))
		return mainFile;
	}
	
	private MainProgramFile handleOffsets(MainProgramFile mainFile) throws Exception{
		assemblerMemory.handleOffsets(mainFile); // STRINGS OCCUPY NOT JUST ONE LINE, NOR DO VARIABLE DECLARATIONS OR INTERVALS (X..Y)
		mainFile=stringIntervals.replaceAll(mainFile); // REPLACE STRINGS INTO THEIR CHARACTER ARRAYS
		mainFile=handleConstantReplacements(mainFile); // SUBSITUTE CONSTANTS INTO THEIR RESPECTIVE VALUE, SUCH AS NUMBER BASES, '$' CONSTANT AND REGISTERS'/QUBITS' ALIASES
		mainFile=assemblerMemory.handleDefines(mainFile); // HANDLE MACROS AND CONSTANT DEFINITIONS
		mainFile=stringIntervals.replaceAll(mainFile); // DO STRINGS AGAIN BECAUSE THE MACROS MAY CONTAIN STRINGS INSIDE THEM
		mainFile=handleExpressions(mainFile); // HANDLE EXPRESSIONS BEFORE MACROS BECAUSE THERE MAY BE EXPRESSIONS THERE
		mainFile=intervals.handleIntervals(mainFile); // HANDLE INTERVALS AGAIN FOR THERE MAY BE LEFTOVER INTERVALS AFTER THE EXPRESSIONS
		mainFile=assemblerMemory.handleVariables(mainFile); // DECLARE VARIABLES AFTER OVERWRITING CONSTANTS INTO THEIR PLACES
		mainFile=handleCommentsAndEmptyLines(mainFile); // THE DEFINITION OF CONSTANTS CREATES EMPTY LINES. CLEAN THEM
		mainFile=handleLabels(mainFile); // FINALLY AFTER THE CODE BEING CLEAN, DECLARE THE LABELS 
		return mainFile;
	}
	
	private void preDeclareConstants(){
		// DECLARE ASSEMBLER CONSTANTS SUCH AS PI,E, AND SOME OTHER IMPORTANT OR RELEVANT MACROS
		for(int i=0;i<preConstants.length;i++) assemblerMemory.getDefinesHandler().declare(preConstants[i][0], preConstants[i][1]);
	}
	
	public MainProgramFile preAssemble(MainProgramFile mainFile) throws Exception{
		preDeclareConstants();
		mainFile.getFile().appendCode("\nHALT"); // APPEND HALT IN THE END OF THE PROGRAM
		mainFile=handleIncluding(mainFile);
		mainFile=handleCommentsAndEmptyLines(mainFile);
		mainFile=handleOffsets(mainFile);
		mainFile=handleExpressions(mainFile);
		return mainFile;
	}
}