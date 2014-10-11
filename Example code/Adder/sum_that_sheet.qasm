;CONSTANTS:
	$zeroChar,'0'
	$message_n1,"Number 1: "
	$message_n2,"Number 2: "
	$message_result,"Result: "
	$result," ",3
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

memsub:
	push {0>>3}
	;[{0}]=[{0}]-[{1}]
	mov {2},[{0}]
	mov {3},[{1}]
	sub {2},{2},{3}
	mov [{0}],{2}
	pop {0>>3}
ret

memsum:
	push {0>>3}
	;[{0}]=[{0}]+[{1}]
	mov {2},[{0}]
	mov {3},[{1}]
	add {2},{2},{3}
	mov [{0}],{2}
	pop {0>>3}
ret

inputNumbers:
	push {0>>1}
	mov {0},$message_n1
	int 1

	;GIVE NUMBERS TO $n1 AND $n2
	;$n1:
	mov {1},$n1
	int 5
	mov {0},$n1
	mov {1},$zeroChar
	call @memsub ; transform from '0' to 0
	
	mov {0},$message_n2
	int 1
	;$n2:
	mov {1},$n2
	int 5
	mov {0},$n2
	mov {1},$zeroChar
	call @memsub ; transform from '0' to 0
	pop {0>>1}
ret

showOutput:
	push {0}
	mov {0},$message_result
	int 1
	;PRINT IT:
	mov {0},$result
	int 1
	pop {0}
ret

main:
	$n1,0
	$n2,0
	
	call @inputNumbers

	;MAKE SUM:
	mov {0},$n1
	mov {1},$n2
	call @memsum

	;CONVERT SUM RESULT TO STRING:
	mov {0},[$n1]
	mov {4},$result
	call @int2str
	call @showOutput
hlt