get stdlib.qasm

scan_ram:
	$ramcontent,3
	mov {1},($ramcontent+3) ;FIRST ADDRESS TO SCAN

	mov [10>>35],'a'>>'z' ; FILL THE RAM WITH CONTENT
	
	;SCAN THE RAM:
	next_address:
		mov {0},[{1}] ; MOVE THE CONTENT OF ADDRESS {1} INTO ANOTHER REGISTER FOR THE FUNCTION CALL
		add {1},{1},1 ; INCREMENT THE ADDRESS IN ORDER TO READ THE NEXT ONE
		mov {4},$ramcontent ; PASS THE ADDRESS OF THE STRING WHICH WILL RECIEVE THE CONTENT CONVERTED INTO STRING
		call @int2str 
		
		;PRINT ADDRESS CONTENT
		mov {0},$ramcontent
		int 1
		mov {0},10 ; PRINT_NEWLINE
		int 0 ; PRINT_NEWLINE
		delay 50
		jmp @next_address
ret

main:
	call @scan_ram