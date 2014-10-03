package com.core.qassembler.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

import com.core.qassembler.constants.QConstants;

public class MainProgramFile implements QConstants{
	private ProgramFileHandler mainProgram;
	private ArrayList<Integer> machinecode;
	private FileOutputStream mainFileHandler;
	
	public MainProgramFile(ProgramFileHandler mainProgram){
		this.mainProgram=mainProgram;
		machinecode=new ArrayList<Integer>();
	}
	
	private void openFile(){
		try {
			mainFileHandler=new FileOutputStream(mainProgram.getFullBinPath());
		} catch (FileNotFoundException e) { e.printStackTrace(); }
	}
	
	private void closeFile(){
		try {
			mainFileHandler.close();
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public ProgramFileHandler getFile(){
		return mainProgram;
	}
	
	public void createLinkedFile(String linkedAssemblyCode){
		try {
			BufferedWriter linkedFileWriter=new BufferedWriter(new FileWriter(new File(mainProgram.getAssembledName())));
			linkedFileWriter.write(linkedAssemblyCode);
			linkedFileWriter.close();
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public void createBinaryFile(){
		openFile();
		insertMachineCode(BINARY_FILE_EOF, BINARY_FILE_EOF, BINARY_FILE_EOF, BINARY_FILE_EOF); // INSERT EOF INTO FILE
		try{
			for(int i=0;i<machinecode.size();i++)
				if(i%4==0) mainFileHandler.write(machinecode.get(i));
	    		else mainFileHandler.write((ByteBuffer.allocate(4).putInt(machinecode.get(i)).array()));	
    	}catch(Exception e){ e.printStackTrace(); }
		closeFile();
	}
	
	
	public void insertMachineCode(Integer[]operands){
		machinecode.addAll(Arrays.asList(operands));
	}
	
	public void insertMachineCode(int func,int op1,int op2,int op3){
		machinecode.addAll(Arrays.asList(func,op1,op2,op3));
	}
}