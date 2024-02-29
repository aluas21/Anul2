assume cs: text_, ds: data_
data_ segment

A db 'a','a','a','a','d','e','a','a','h','i','j','G','a','G'
lenA equ ($-A)
LitereMici dw 0
nrcif db 0
val10 dw 10

data_ ends

text_ segment
start:
	mov ax,data_
	mov ds,ax
	mov si,0
	mov cx, lenA
	repeta:
		mov bl, A[si]
		cmp bl,'a'
		JAE maiMare
		JB endif1
		maiMare:
			cmp bl,'z'
			JBE literaMica
			JA endif1
			literaMica:
				mov ah,02h
				mov dl,bl
				int 21h
				inc LitereMici
		endif1:
		inc si
	loop repeta
	
	mov cx,0
	mov ax,LitereMici
	mov bl,10
	accsareCifre:
		mov dx,0
		idiv bx
		push dx
		inc cx
		cmp ax,0
		JNE accsareCifre
		JE afisareCifre
		afisareCifre:
			pop dx
			add dl, '0'
			mov ah,02h
			int 21h
		loop afisareCifre
		
		
		
	;mov ah, 02h
	;mov dl,LitereMici
	;add dl,'0'
	;int 21h
	mov ax,4c00h
	int 21h
text_ ends
end start