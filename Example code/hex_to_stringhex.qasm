jmp @main

print_hex:
	push {0>>3}
	$HEX_TEMPLATE,"0x??"
	$HEXABET,"0123456789abcdef"

	;CONVERT:
	;DIGIT 1:
	mov {2},{1}
	shr {2},{2},4
	add {3},{2},$HEXABET
	mov {3},[{3}]
	mov [($HEX_TEMPLATE+2)],{3}
	;DIGIT 2:
	mov {2},{1}
	shr {2},{2},0 ; DOESNT NEED TO SHIFT, BE LEAVING IT HERE ANYWAYS
	and {2},{2},0xF
	add {3},{2},$HEXABET
	mov {3},[{3}]
	mov [($HEX_TEMPLATE+3)],{3}

	;PRINT_RESULT:
	mov {0},$HEX_TEMPLATE
	call @print_str
	pop {0>>3}
ret
print_str:
	int 1
ret

main:
	mov {1},0xEC
	call @print_hex