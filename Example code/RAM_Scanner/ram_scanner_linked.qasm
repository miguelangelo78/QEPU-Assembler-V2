jmp @main
int2str: ; [{4}] contains the address of the memory to give the value to
	push {0>>4}
	;;;;;;;;
	mov {3},3 ; COUNTER (NUMBER OF DIGITS)
	pushDigit:

		div {1},{0},10 ; get the remainder
		mov {2},{1} 
		mul {1},{1},10 ; get the remainder
		sub {1},{0},{1} ; get the remainder
		add {1},{1},48  ; convert to 'ascii'
		push {1} ; push that sh*t!
		mov {0},{2}
		
		; DECREMENT COUNTER
		sub {3},{3},1
		cmp {3},{3}
		bnz @pushDigit ; jump if not done yet

	pop {0>>2}

	mov [{4}],{0}
	add {4},{4},1
	mov [{4}],{1}
	add {4},{4},1
	mov [{4}],{2}
	;;;;;;;;
	pop {4>>0}
ret

scan_ram:
$ramcontent,3
mov {1},($ramcontent+3) 
mov [10>>35],'a'>>'z' 
next_address:
mov {0},[{1}] 
add {1},{1},1 
mov {4},$ramcontent 
call @int2str 
mov {0},$ramcontent
int 1
mov {0},10 
int 0 
delay 50
jmp @next_address
ret
main:
call @scan_ram
