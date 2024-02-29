;se da un sir de cuvinte A
;b1: contine partea superioara a cuvintelor din A
;b2: contine partea inferioara a cuvintelor din A
assume cs: text_, ds: data_

data_ segment

A dw 1111111100000000b,000000001111111b,1100110011001100b
lenA equ ($-A)
B1 db lenA/2 dup(0)
B2 db lenA/2 dup(0)

data_ ends

text_ segment
start:
mov ax,data_
mov ds,ax

mov si,0; A
mov di,0; B1
mov bp,0; B2
mov cx, lenA/2
repeta:
	mov al,byte ptr A[si] ; A[0] partea inferioara
	add si,1;
	mov bl,byte ptr A[si] ; A[2] partea superioara
	add si,1;
	mov B1[di],bl;
	mov B2[bp],al;
	inc di
	inc bp
loop repeta

mov si, offset A
mov di, offset B1
mov bp, offset B2



mov ax, 4c00h
int 21h
text_ ENDS

END start







;







;SALVAM INTR-UN SIR DE WORDUri
mov si,0
mov di,0

mov cx, lenA/2
repeta1:
	mov ax, word ptr A[si]
	mov dx, word ptr A[si+2]
	add si,4
	mov B[di],ax
	mov B[di+2],dx
	add di,4
loop repeta1

;AFLAM MAXIMUL DIN SIRUL nou

mov si,0
mov al, byte ptr B[si]
mov bl,al
inc si
mov cx,lenA*2
sub cx,1
repeta:
	mov al, byte ptr B[si]
	cmp bl,al
	jb modMaxim
	modMaxim:
		mov bl,al
	inc si
	
loop repeta