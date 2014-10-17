mov dx,@tocall
mov bx,0
mov cx,5

jmp @main

tocall:
	pusha
	mov ax,'a'
	int 0
	popa
ret

main:
	;FOR LOOP:
	for_ret:
		cmp bx,cx
		bge @for_out
		inc bx
		callr dx
		jmp @for_ret
	for_out:
hlt