package com.core.qassembler;

import com.core.qassembler.file.MainProgramFile;
import com.core.qassembler.file.ProgramFileHandler;
import com.core.qassembler.includer.Includer;
import com.core.qassembler.memory.Memory;
import com.core.qassembler.preassembler.Preassembler;
import com.core.qassembler.translator.InstructionTranslator;
import com.core.qassembler.validator.InstructionValidator;

public class QAssembler {
	private MainProgramFile mainFile; // THE MAIN FILE ITSELF
	private Preassembler preAssembler; // PRE ASSEMBLER WHICH SOLVES VARIABLES, OFFSETS, LABELS, EXPRESSIONS AND EVERYTHING ELSE
	private Memory assemblerMemory; // THE MEMORY FOR THE ASSEMBELR
	private Includer includer; // INCLUDES FILES
	private InstructionValidator validator; // VALIDATE FUNCTIONS
	private InstructionTranslator translator; // TRANSLATE LINES AND INSERT TRANSLATION INTO MAINFILE
	
	public QAssembler(String program_filepath) throws Exception{
		preAssembler=new Preassembler(program_filepath);
		
		//GET RESULTS FROM PREASSEMBLING:
		mainFile=preAssembler.preAssemble(new MainProgramFile(new ProgramFileHandler(program_filepath)));
		assemblerMemory=preAssembler.getAssemblerMemory();
		includer=preAssembler.getIncluder();
		
		//MORE INITIALIZATIONS:
		validator=new InstructionValidator();
		translator=new InstructionTranslator();
		
		//GO:
		assemble(mainFile.getFile().getAssemblyCode());
	}
	
	private String assemble(String assembly_code){
		String result="";
		// DO ALL KINDS OF STUFFS HERE
		return result;
	}
}