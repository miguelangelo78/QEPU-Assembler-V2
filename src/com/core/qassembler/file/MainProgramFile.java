package com.core.qassembler.file;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import com.core.qassembler.constants.Global_Constants;
import com.utils.Misc;

public class MainProgramFile implements Global_Constants{
	private ProgramFileHandler mainProgram;
	private ArrayList<Long> machinecode;
	private DataOutputStream mainFileHandler;
	
	public MainProgramFile(ProgramFileHandler mainProgram){
		this.mainProgram=mainProgram;
		machinecode=new ArrayList<Long>();
	}
	
	private void openFile(){
		try {
			mainFileHandler=new DataOutputStream(new FileOutputStream(mainProgram.getFullBinPath()));
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
			String linked_filepath=mainProgram.getPath()+mainProgram.getName()+FILELINKED_SUFFIX;
			System.out.println("Creating linked file ("+linked_filepath+")...");
			BufferedWriter linkedFileWriter=new BufferedWriter(new FileWriter(new File(linked_filepath)));
			linkedFileWriter.write(linkedAssemblyCode);
			linkedFileWriter.close();
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public void createBinaryFile(){
		openFile();
		insertMachineCode(BINARY_FILE_EOF, BINARY_FILE_EOF, BINARY_FILE_EOF, BINARY_FILE_EOF, BINARY_FILE_EOF, BINARY_FILE_EOF, BINARY_FILE_EOF); // INSERT EOF INTO FILE
		
		try{
			int meta_counter=1;
			for(int i=0;i<machinecode.size();i++){
				if(i%7==0 || meta_counter%2==0){
					mainFileHandler.write((int)(machinecode.get(i)+0)); // WRITE FUNCTION AND METADATA (LONG TO INT CAST)
					meta_counter=0;
				}
				else mainFileHandler.write(ByteBuffer.allocate(4).put(Misc.getByteSubFromTo(machinecode.get(i), 4, 0)).array()); // WRITE FUNCTION WITH PADDING OF 4 BYTES
				meta_counter++;
			}
		}catch(Exception e){ e.printStackTrace(); }
		closeFile();
	}
	
	public void insertMachineCode(long[]operands){
		machinecode.addAll(Misc.asList(operands));
	}
	
	public void insertMachineCode(int func,int op1,int meta1,int op2,int meta2,int op3,int meta3){
		machinecode.addAll(Misc.asList(new long[]{func,op1,meta1,op2,meta2,op3,meta3}));
	}
}