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
main:
	$number,10 ; number to convert
	$number_str," ",3 ; variable which is going to recieve the string

	mov {0},[$number] ; give register 0 the number
	mov {4},$number_str ; give register 4 the address of the variable destination
	call @int2str ; do it!

	mov {0},$number_str ; print it
	int 1
hlt