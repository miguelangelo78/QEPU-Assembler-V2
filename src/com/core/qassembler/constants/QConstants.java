package com.core.qassembler.constants;

public interface QConstants {
	// GLOBAL VARIABLES:
    // TYPES OF OPERANDS:
    final int FUNC=0;
	final int OP1=1;
	final int OP2=2;
	final int OP3=3;
	final int REGISTER=0,REGISTER_SIZE=8; 		  // {K}
	final int QUBIT=1,QUBIT_THETA=2,QUBIT_PHI=3,QUBIT_SIZE=1; // <K> |K| !K! (< QUBIT BOTH DIMS,| QUBIT THETA, ! QUBIT PHI)
	final int MEMORYCONTAINER=4,MEMORY_SIZE=8;    // [K]
	final int CONSTANT=5,CONSTANT_SIZE=8; 		  // K
	final int[][] TYPES_SIZES=new int[][]{{REGISTER,REGISTER_SIZE}, // TO PREVENT VARIABLES FROM DIFFERENT SIZES TO BE ASSMEBLED
											 {QUBIT	  ,QUBIT_SIZE},
											 {MEMORYCONTAINER,MEMORY_SIZE},
											 {CONSTANT,CONSTANT_SIZE}};
	final String FILESOURCE_FORMAT="qasm";
	final String FILEBINARY_FORMAT="bin";
	final String FILEMAIN_ENTRYPOINT="main";
	final String FILELINKED_SUFFIX="_linked."+FILESOURCE_FORMAT;
	final int MAX_OPERAND_COUNT=3;
	final int BINARY_FILE_EOF=0xFF;
	final char STRING_TERMINATOR='\0';
    //REGEX PATTERNS:
	final String PATT_FILENAME="[^\\\\]*$";
	final String PATT_FILEFORMAT="[^\\.]*$";
	final String PATT_INCLUDING="^(?:.+?)?get.+?([a-z|A-Z].+?"+FILESOURCE_FORMAT+")";
	final String PATT_FILEPATH=".+\\\\";
	final String PATT_MAINLABEL="^(?:[ |	]+?)?("+FILEMAIN_ENTRYPOINT+")(?:[ |	]+?)?\\:";
	final String PATT_LABEL="^(?:[ |	]+?)?(.+?)(?:[ |	]+?)?\\:";
	final String PATT_LABELREF="@(.+?)(?:[ |	,\\[\\]]|$)";
	final String PATT_COMMENT="(?:\\;.+?$)|\\/\\*(?:(?:.|\n)+?)?\\*\\/|^(?:[ |	]+?)?$";
	final String PATT_EMPTYLINE="(?m)^[ \\t]*\r?\n";
	final String PATT_EXPRESSION_VAR="((?:[0-9]|\\$.+?)+?)(?:[ |	]+?)?([+\\-\\*\\/])+?(?:[ |	]+?)?((?:[0-9]|\\$.+?)+?$)";
	final String PATT_EXPRESSION="([0-9]+?)(?:[ |	]+?)?([+\\-\\*\\/])+?(?:[ |	]+?)?([0-9]+?$)";
	final String PATT_EXPRESSION_V2="[^a-z|0-9](?:[ |	|\\(]+)?[0-9]+?(?:[ |	]+?)?(?:[\\(\\)+\\-\\*\\/]+?(?:[ |	]+?)?[0-9])+(?:[ |	|\\)]+)?";
	final String PATT_EXPRESSION_V3="\\(+?(?:(?:[0-9])?.+)?\\)";
	final String PATT_VARIABLEDECL="^(?:[ |	]+?)?\\$.+?,.+?$";
	final String PATT_VARIABLENAME="^(?:[ |	]+?)?\\$(?:[ |	]+?)?(.+?)$";
	final String PATT_STRINGCONSTANT="mov.+?\\[.+?\\].+?\"(.+?)\"(?:.+?)?$";
	final String PATT_CHARCONSTANT="'(.)'";
	final String PATT_INTERVAL="([0-9]+)(?:[ |	]+?)?>>+(?:[ |	]+?)?([0-9]+)";
	final String PATT_FUNCIONCONTAINER="(.+?)\\[(.+?)\\]";
}