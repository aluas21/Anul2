assume cs:text_,ds:data_

data_ segment
sir dd 12345678h, 57694A5Bh, 0FFEEDDCCh, 5F7B6C2Ah
lens equ ($-sir)/4
D db lens dup(0)
maxim db 0
cifre db 0
trei db 3
data_ ends

text_ segment

start:
    mov ax, data_
    mov ds, ax

	mov si,0
	mov cl, lens
	mov ch, 0
	mov di,0
	repeta:
		mov al,byte ptr sir[si]
		mov maxim, al
		mov trei, 3
		cautMaxim:
			inc si
			mov al, byte ptr sir[si]
			cmp al,maxim
			JBE skip
			mov maxim,al
			skip:
			dec trei
			cmp trei,0
			JNE cautMaxim
		mov al,maxim
		mov D[di],al
		inc si
		inc di
	loop repeta
	
	mov cl, lens
	mov ch,0
	mov di,0
	mov bl,10
	repeta2:
		mov cifre,0
		mov al, D[di]
		puneStiva:
			mov ah,0
			div bl
			mov dl,ah
			mov dh,0
			push dx
			mov ah, 0
			inc cifre 
			cmp al,0
			JNE puneStiva
		afisare:
			dec cifre
			pop dx
			add dl,'0'
			mov ah, 02h
			int 21h
			cmp cifre,0
			JNE afisare
		mov dl, ' '
		mov ah, 02h
		int 21h
		inc di
	loop repeta2
	
	
	mov ax, 4c00h      
    int 21h  

	text_ ends
	end start