;se da un sir de cuvinte A
;b1: contine partea superioara a cuvintelor din A
;b2: contine partea inferioara a cuvintelor din A
assume cs: text_, ds: data_

data_ segment

A dd 22232222h
lenA equ ($-A)
maxim db 0

data_ ends

text_ segment
start:
mov ax,data_
mov ds,ax
mov si,0
mov cx,lenA
repeta:
	mov al,byte ptr A[si]
	cmp al,maxim
	JA modmaxim
	modmaxim:
		mov maxim,al
	inc si
loop repeta
mov ax, 4c00h
int 21h
text_ ENDS

END start




mov ax,LitereMici
	mov dx,0
	inc nrcif
	div val10
	count_digits:
		cmp ax, 0                
		je  done                  
		inc nrcif                  
		push dx
		mov dx,0
		div val10
		jmp count_digits        

	done:
	
	mov cl,nrcif
	mov ch,0
	convertCif:
		pop dx
		mov ah, 02h
		mov dl,al
		add dl,'0'
		int 21h
	loop convertCif