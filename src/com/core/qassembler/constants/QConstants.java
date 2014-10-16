package com.core.qassembler.constants;

import java.util.HashMap;

public interface QConstants {
	// GLOBAL VARIABLES:
    // TYPES OF OPERANDS:
    final int FUNC=0;
	final int OP1=1;
	final int OP2=2;
	final int OP3=3;
	final int REGISTER=0,REGISTER_SIZE=8; 		  // {K}
	final int REGISTER_POINTER=1; // {K}
	final int QUBIT=2,QUBIT_THETA=3,QUBIT_PHI=4,QUBIT_SIZE=1; // <K> |K| !K! (< QUBIT BOTH DIMS,| QUBIT THETA, ! QUBIT PHI)
	final int MEMORYCONTAINER=5,MEMORY_SIZE=8;    // [K]
	final int CONSTANT=6,CONSTANT_SIZE=8; 		  // K
	final int[][] TYPES_SIZES=new int[][]{{REGISTER,REGISTER_SIZE}, // TO PREVENT VARIABLES FROM DIFFERENT SIZES TO BE ASSMEBLED
										  {REGISTER_POINTER,REGISTER_SIZE},
										  {QUBIT  ,QUBIT_SIZE},
										  {MEMORYCONTAINER,MEMORY_SIZE},
										  {CONSTANT,CONSTANT_SIZE}};
	final HashMap<String, String> REGISTER_LAYOUT=new HashMap<String,String>();
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
	final String PATT_LABELREF="@(.+?)(?:[ |	,\\[\\]]|$|[^a-zA-Z0-9_])";
	final String PATT_COMMENT="(?:\\;.+?$)|\\/\\*(?:(?:.|\n)+?)?\\*\\/|^(?:[ |	]+?)?$";
	final String PATT_EMPTYLINE="(?m)^[ \\t]*\r?\n";
	final String PATT_EXPRESSION_VAR="((?:[0-9]|\\$.+?)+?)(?:[ |	]+?)?([+\\-\\*\\/])+?(?:[ |	]+?)?((?:[0-9]|\\$.+?)+?$)";
	final String PATT_EXPRESSION="([0-9]+?)(?:[ |	]+?)?([+\\-\\*\\/])+?(?:[ |	]+?)?([0-9]+?$)";
	final String PATT_EXPRESSION_V2="[^a-z|0-9](?:[ |	|\\(]+)?[0-9]+?(?:[ |	]+?)?(?:[\\(\\)+\\-\\*\\/]+?(?:[ |	]+?)?[0-9])+(?:[ |	|\\)]+)?";
	final String PATT_EXPRESSION_V3="\\(+?(?:(?:[0-9])?.+)?\\)";
	final String PATT_EXPRESSION_V4="\\(+?(?:(?:[0-9])?.+?)(?:,|\\.|$)";
	final String PATT_VARIABLEDECL="^(?:[ |	]+?)?\\$.+?,.+?$";
	final String PATT_VARIABLENAME="^(?:[ |	]+?)?\\$(?:[ |	]+?)?(.+?)$";
	final String PATT_VARIABLEREF_PART1="\\$(?:[ |	]+)?(?:\\b";
	final String PATT_VARIABLEREF_PART2="\\b)";
	final String PATT_STRINGCONSTANT="mov.+?\\[.+?\\].+?\"(.+?)\"(?:.+?)?$";
	final String PATT_CHARCONSTANT="'(.+?)'";
	final String PATT_INTERVAL="([0-9]+)(?:[ |	]+?)?\\.\\.+(?:[ |	]+?)?([0-9]+)";
	final String PATT_FUNCIONCONTAINER="(.+?)\\[(.+?)\\]";
	final String PATT_CURRLINE="\\$";
	final String PATT_HEX="0x[0-9a-f]+";
	final String PATT_BIN="0b[0-1]+";
	final String PATT_OCTAL="0o[0-7]+";
	final String PATT_ALLBASES="(?:0x[0-9a-f]+)|(?:0b[0-1]+)|(?:0o[0-7]+)";
	final String PATT_NUMBER="[-+]?(?:[ |	]+)?[0-9]*(?:[ |	]+)?\\.?(?:[ |	]+)?[0-9]+(?:[ |	]+)?";
}