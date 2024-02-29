;elementele de pe poz pare se aduna iar cele de pe poz impare se scad
assume cs: text_, ds: data_
;6,-4,10,-4
data_ segment
A db 1,2,3,4
lenA equ ($-A)
B db 5,6,7,8
lenB equ ($-B)
R db lenA dup(0)
lenR equ lenA

data_ ends

text_ segment
start:
Mov ax,data_
Mov ds,ax

Mov si,0;R
mov dl,0
mov bp,0;B
mov di,0;A
Mov cx, lenA
Repeta:
	mov al,dl
	cbw
	mov bl,2
	idiv bl
	cmp ah,0
	JE par
	JNE impar
	par:
		mov al,A[di]
		add al,B[bp]
		mov R[si],al
		jmp end_if
	impar:
		mov al,A[di]
		sub al,B[bp]
		mov R[si],al
		jmp end_if
	end_if:
	inc si
	inc di
	inc bp
	add dl,1
loop Repeta

mov si,0;
mov al,R[si]
inc si
mov ah,R[si]
inc si
mov bl,R[si]
inc si
mov bh,R[si]
inc si
mov si,0

mov ax, 4c00h
int 21h
text_ ends
end start