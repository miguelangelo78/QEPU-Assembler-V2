package com.core.qassembler;

import com.core.qassembler.file.MainProgramFile;
import com.core.qassembler.file.ProgramFileHandler;
import com.core.qassembler.preassembler.Preassembler;
import com.core.qassembler.translator.InstructionTranslator;

public class QAssembler {
	private MainProgramFile mainFile; // THE MAIN FILE ITSELF
	private Preassembler preAssembler; // PRE ASSEMBLER WHICH SOLVES VARIABLES, OFFSETS, LABELS, EXPRESSIONS AND EVERYTHING ELSE
	private InstructionTranslator translator; // TRANSLATE LINES AND INSERT TRANSLATION INTO MAINFILE
	private int programCounter,programCounterMax;
	
	public QAssembler(String program_filepath) throws Exception{
		preAssembler=new Preassembler(program_filepath);
		
		//GET RESULTS FROM PREASSEMBLING:
		mainFile=preAssembler.preAssemble(new MainProgramFile(new ProgramFileHandler(program_filepath)));
		mainFile.getFile().updateSplittedLines();
		
		//MORE INITIALIZATIONS:
		translator=new InstructionTranslator(preAssembler.getAssemblerMemory());
		programCounter=0;
		programCounterMax=mainFile.getFile().getCodeLineCount();
		
		//GO:
		System.out.println(assemble(mainFile.getFile().getAssemblyCode()));
	}
	
	private String assemble(String assembly_code){
		String result="";
		// DO ALL KINDS OF STUFFS HERE
		try{
			for(programCounter=0;programCounter<programCounterMax;programCounter++){
				String line=mainFile.getFile().getLine(programCounter);
				if(translator.isInstructionValid(line)) mainFile.insertMachineCode(translator.translate(line,programCounter));
			}
		}catch(Exception e){
			result=e.getMessage();
			e.printStackTrace();
			return result;
		}
		mainFile.insertMachineCode(translator.getEOFoperands());
		mainFile.createBinaryFile(); // CREATE THE FINAL FILE WHICH IS THE BINARY FILE
		result="STATUS: The main file '"+mainFile.getFile().getPreAssembledName()+"' has been successfully assembled ("+programCounterMax+" lines).";
		return result;
	}
}