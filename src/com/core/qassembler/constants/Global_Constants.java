package com.core.qassembler.constants;

import java.util.HashMap;

public interface Global_Constants {
	// GLOBAL VARIABLES:
    // TYPES OF OPERANDS:
    final int FUNC=0;
	final int OP1=1;
	final int OP2=2;
	final int OP3=3;
	
	//OPERAND TYPES (MORE SPECIFIC) AND THEIR RESPECTIVE SIZES:
	final int QREGISTER=0,QREGISTER_SIZE=32; 		  // %K%
	final int QREGISTER_POINTER=1; 					  // [%K%]
	final int CREGISTER=2,CREGISTER_SIZE=32;		  // {K}
	final int CREGISTER_POINTER=3;					  // [{K}]
	final int QUBIT=4,QUBIT_THETA=5,QUBIT_PHI=6,QUBIT_SIZE=1; // <K> |K| !K! (< QUBIT BOTH DIMS,| QUBIT THETA, ! QUBIT PHI)
	final int MEMORYCONTAINER=7,MEMORY_SIZE=32;    	  // [K]
	final int CONSTANT=8,CONSTANT_SIZE=32; 		   	  // K
	
	// TO PREVENT VARIABLES FROM DIFFERENT SIZES TO BE ASSMEBLED:
	final int[][] TYPES_SIZES=new int[][]{{QREGISTER,		 QREGISTER_SIZE}, 
										  {QREGISTER_POINTER,QREGISTER_SIZE},
										  {CREGISTER,		 CREGISTER_SIZE},
										  {CREGISTER_POINTER,CREGISTER_SIZE},
										  {QUBIT,			 QUBIT_SIZE},
										  {QUBIT_THETA,		 QUBIT_SIZE},
										  {QUBIT_PHI,		 QUBIT_SIZE},
										  {MEMORYCONTAINER,	 MEMORY_SIZE},
										  {CONSTANT,		 CONSTANT_SIZE}};
	// METADATA CONSTANTS:
	final int META_NULL=0;
	final int META_CREGISTER=1;
	final int META_CREGISTER_POINTER=2;
	final int META_QREGISTER=3;
	final int META_QREGISTER_POINTER=4;
	final int META_MEM_CONTAINER=5;
	final int META_MEM_ADDRESS=6;
	final int META_CONSTANT=7;
	final int META_QUBIT=8;
	final int META_QUBIT_THETA=9;
	final int META_QUBIT_PHI=10;
	
	final HashMap<String, String> CREGISTER_CONTAINER=new HashMap<String,String>();
	final HashMap<String, String> CREGISTER_NOCONTAINER=new HashMap<String,String>();
	final HashMap<String, String> QREGISTER_CONTAINER=new HashMap<String,String>();
	final HashMap<String, String> QREGISTER_NOCONTAINER=new HashMap<String,String>();
	final HashMap<String, String> QREGISTER_OFF=new HashMap<String,String>();
	final HashMap<String, String> CREGISTER_OFF=new HashMap<String,String>();
	final HashMap<String,String> QBIT_ALL=new HashMap<String,String>();
	final HashMap<String,String> QBIT_THETA=new HashMap<String,String>();
	final HashMap<String,String> QBIT_PHI=new HashMap<String,String>();
	final String FILESOURCE_FORMAT="qasm";
	final String FILEBINARY_FORMAT="bin";
	final String FILEMAIN_ENTRYPOINT="main";
	final String FILELINKED_SUFFIX="_linked."+FILESOURCE_FORMAT;
	final int MAX_OPERAND_COUNT=3;
	final int BINARY_FILE_EOF=0xFF;
	final char STRING_TERMINATOR='\0';
	final int CREGISTER_COUNT=25;
	final int QREGISTER_COUNT=25;
	
	// PRE CONSTANTS:
	final double VERSION_QASM=0.42;
	final String[][] preConstants={{"PI","3.141592653589793"},
								   {"E","2.71828"},
								   {"VER","\"QASM (Quantum Assembler) Version "+VERSION_QASM+".\\\\0\""},
								   {"CRSIZE",""+CREGISTER_SIZE},
								   {"QRSIZE",""+QREGISTER_SIZE},
								   {"CRCOUNT",""+CREGISTER_COUNT},
								   {"QRCOUNT",""+QREGISTER_COUNT}};
	
    //REGEX PATTERNS:
	final String PATT_SPECIAL_REGEX_CHARS =  "[{}()\\[\\].+*?^$\\\\|]";
	final String PATT_FILENAME=	 			 "[^\\\\]*$";
	final String PATT_FILEFORMAT=	 		 "[^\\.]*$";
	final String PATT_INCLUDING= 	 		 "^(?:.+?)?get.+?([a-z|A-Z].+?"+FILESOURCE_FORMAT+")";
	final String PATT_FILEPATH=  	 		 ".+\\\\";
	final String PATT_MAINLABEL= 	 		 "^(?:[ |	]+?)?("+FILEMAIN_ENTRYPOINT+")(?:[ |	]+?)?\\:";
	final String PATT_LABEL=	 	 		 "^(?:[ |	]+?)?(.+?)(?:[ |	]+?)?\\:";
	final String PATT_COMMENT=	 	 		 "(?:\\;.+?$)|\\/\\*(?:(?:.|\\n)+?)?\\*\\/";
	final String PATT_EMPTYLINE= 	 		 "(?m)^[ \\t]*\r?\n";
	final String PATT_TEMPLATE_OUTSIDEQUOTES="(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
	final String PATT_EXPRESSION_VAR=		 "((?:[0-9]|\\$.+?)+?)(?:[ |	]+?)?([+\\-\\*\\/])+?(?:[ |	]+?)?((?:[0-9]|\\$.+?)+?$)";
	final String PATT_EXPRESSION=	 		 "([0-9]+?)(?:[ |	]+?)?([+\\-\\*\\/])+?(?:[ |	]+?)?([0-9]+?$)";
	final String PATT_EXPRESSION_V2= 		 "[^a-z|0-9](?:[ |	|\\(]+)?[0-9]+?(?:[ |	]+?)?(?:[\\(\\)+\\-\\*\\/]+?(?:[ |	]+?)?[0-9])+(?:[ |	|\\)]+)?";
	final String PATT_EXPRESSION_V3= 		 "\\(+?(?:(?:[0-9])?.+)?\\)";
	final String PATT_EXPRESSION_V4= 		 "\\(+?(?:(?:[0-9])?.+?)(?:,|\\.| |	|$)";
	final String PATT_EXPRESSION_V5=		 "\\B\\(.+?\\)\\B";
	final String PATT_EXPRESSION_V6=		 "_(.+?)_"+PATT_TEMPLATE_OUTSIDEQUOTES;
	final String PATT_VARIABLEDECL=  		 "^(?:[ |	]+?)?\\$.+?,.+?$";
	final String PATT_VARIABLENAME=  		 "^(?:[ |	]+?)?\\$(?:[ |	]+?)?(.+?)$";
	final String PATT_STRINGCONSTANT=		 "mov.+?\\[.+?\\].+?\"(.+?)\"(?:.+?)?$";
	final String PATT_CHARCONSTANT=			 "'(.+?)'";
	final String PATT_INTERVAL="(-?[0-9.]+)(?:[ \\t]+?)?\\.\\.+(?:[ \\t]+?)?(-?[0-9.]+)(?:(?:[ \\t]+?)?#(?:[ \\t]+?)?(-?[0-9.]+))?";
	final String PATT_FUNCIONCONTAINER=		 "(.+?)\\[(.+?)\\]";
	final String PATT_CURRLINE=				 "\\$";
	final String PATT_HEX=					 "0x[0-9a-f]+";
	final String PATT_BIN=					 "0b[0-1]+";
	final String PATT_OCTAL=				 "0o[0-7]+";
	final String PATT_ALLBASES=				 "(?:0x[0-9a-f]+)|(?:0b[0-1]+)|(?:0o[0-7]+)";
	final String PATT_NUMBER=				 "[-+]?(?:[ |	]+)?[0-9]*(?:[ |	]+)?\\.?(?:[ |	]+)?[0-9]+(?:[ |	]+)?";
	final String PATT_TIMES=				 "\\btimes(?:[ |	]+)?(-?[0-9]+?)\\b(?:[ |	]+)?(.+?)\\bendtimes\\b";
	final String PATT_MACRO_DEFINE_DECL=	 "(?i)#(?:[ |	]+)?define(?:[ |	]+)(\\b.+?\\b)(?:[ |	]+)(.+?)(?:[&]|$)";
	final String PATT_INSTR_NEWLINE=		 "&"+PATT_TEMPLATE_OUTSIDEQUOTES;
}
