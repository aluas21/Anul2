assume cs:code,ds:data
data segment
n db 6, ?, 6 dup(0)	;n=7	1 1 2 3 5 8 13
a dw 1
b dw 1
n2 dw 0
cifre db 0
data ends

code segment
start:
	mov ax, data
	mov ds, ax

	mov ah, 0Ah
	mov dx, offset n
	int 21h

	mov si, 1
	mov cl, n[si]
	mov ch, 0
	inc si

	repeta:
		mov dl, n[si]
		sub dl, '0'
		mov dh, 0
		push dx
		;mov dh, '0'
		inc si
	loop repeta

	mov bx, 1
	mov si, 1
	mov cl, n[si]
	mov ch, 0
	inc si
	numar:
		pop dx
		mov ax, dx
		mul bx
		add n2, ax
		mov ax, bx
		mov dx, 10
		mul dx
		mov bx, ax
	loop numar

	mov ah, 02h
	mov dl, '1'
	int 21h

	mov ah, 02h
	mov dl, ' '
	int 21h

	mov ah, 02h
	mov dl, '1'
	int 21h

	sub n2, 2
	mov cx, n2

	afisare:
		mov ax, a
		mov bx,b
		add ax, bx
		mov a, bx
		mov b, ax
		mov ah, 02h
		mov dl, ' '
		int 21h
		
		mov cifre, 0
		mov bx,10
		mov ax,b
		mov dx,0
		puneStiva:
			div bx
			inc cifre
			push dx
			mov dx,0
			cmp ax,0
			JNE puneStiva
		afiseaza:
			dec cifre
			pop dx
			add dl,'0'
			mov ah,02h
			int 21h
			cmp cifre,0
			JNE afiseaza
	loop afisare
		
	mov ax, 4c00h      
    int 21h  

code ends
end start