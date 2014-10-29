package com.core.qassembler;

import java.util.List;
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
		translator=new InstructionTranslator();
		programCounter=0;
		programCounterMax=mainFile.getFile().getCodeLineCount();
		
		//GO:
		System.out.println(assemble(mainFile.getFile().getAssemblyCode()));
	}
	
	private String assemble(String assembly_code){
		String result="";
		System.out.println("OUTPUT:\n"+assembly_code);
		if(assembly_code.trim().length()>0){ // THE FILE CANNOT BE EMPTY
			try{
				for(programCounter=0;programCounter<programCounterMax;programCounter++){
					String line=mainFile.getFile().getLine(programCounter);
					List<Object> validate=translator.isInstructionValid(line,programCounter);
					if((Boolean)validate.get(0))
						mainFile.insertMachineCode(translator.translate(line,programCounter));
					else throw new Exception("**** ASSEMBLING ERROR: "+(String)validate.get(1));
				}
			}catch(Exception e){
				e.printStackTrace();
				return e.getMessage();
			}
		}
		mainFile.createBinaryFile(); // CREATE THE FINAL FILE WHICH IS THE BINARY FILE
		result="STATUS: The main file '"+mainFile.getFile().getPreAssembledName()+"' has been successfully assembled ("+programCounterMax+" lines).";
		return result;
	}
}