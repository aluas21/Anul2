assume cs:text_,ds:data_

data_ segment
S dw 1A2Bh, 0C366h, 78DCh, 1213h
lens equ ($-S)/2
D dw lens dup(0)
cifre db 0;
data_ ends

text_ segment

start:
    mov ax, data_
    mov ds, ax

	mov si, 0
	mov di, 0
	mov cx, lens
	repeta:
		mov bx,0
		mov ax,0
		mov al, byte ptr S[si]
		inc si
		mov bl, byte ptr S[si]
		mul bl		
		inc si
		mov D[di],ax
		inc di
		inc di
	loop repeta
	
	
	mov cx, lens 
	mov di,0
	repeta2:
		mov bx, 10
		mov ax, D[di]
		mov dx, 0
		puneStiva:
			div bx
			push dx
			mov dx,0
			inc cifre
			cmp ax,0
			JNE puneStiva
		
		afisare:
			pop dx
			add dl,'0'
			mov ah,02h
			int 21h
			dec cifre 
			cmp cifre, 0
			JNE afisare
		inc di
		inc di
		mov dl,' '
		mov ah,02h
		int 21h
		
	loop repeta2
	
	mov ax, 4c00h      
    int 21h  

text_ ends
end start