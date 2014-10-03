package com.core.qassembler.constants;

public interface QConstants {
	// GLOBAL VARIABLES:
    // TYPES OF OPERANDS:
    final int FUNC=0;
	final int OP1=1;
	final int OP2=2;
	final int OP3=3;
	final int REGISTER=4;
	final int QUBIT=5;
	final int MEMORY=6;
	final int FLAG=7;
	final int CONSTANT=8;
	final String FILESOURCE_FORMAT="qasm";
	final String FILEBINARY_FORMAT="bin";
	final String FILEMAIN_ENTRYPOINT="main";
	final int MAX_OPERAND_COUNT=3;
	final int BINARY_FILE_EOF=0xFF;
	final char STRING_TERMINATOR='\0';
    
}