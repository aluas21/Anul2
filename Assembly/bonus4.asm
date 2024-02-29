assume cs:text_,ds:data_

data_ segment
sir db 'sFgsdyrfbgdddce'
len equ ($-sir)
char db 'd'
nr db 0
data_ ends

text_ segment

start:
    mov ax, data_
    mov ds, ax
	
	mov si,0
	mov cx,len
	repeta:
		mov al,sir[si]
		cmp al,char 
		je egale
		jne inegale
		egale:
			add nr,1
		inegale:
		inc si
	loop repeta
	mov cx,0
	mov ax,nr
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
	
	mov ax, 4c00h      
    int 21h           
	
	text_ ends
	end start