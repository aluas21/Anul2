;elementele de pe poz pare se aduna iar cele de pe poz impare se scad
assume cs: text_, ds: data_

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
mov ax,data_
mov ds,ax

mov si, offset A
mov di, offset B 
mov bp, offset R
ClD
mov dl, 0
mov cx,lenA

repeta:
	;verificam paritatea pozitiei
	mov al,dl
	cbw
	mov bl,2
	idiv bl
	cmp ah,0
	JE par
	JNE impar
	par:
		LODSB ;mov al,A[si]
		add al,B[di]
		mov R[bp],al
		jmp end_if
	impar:
		LODSB ;mov al,A[si]
		sub al,B[di]
		mov R[bp],al
		jmp end_if
	end_if:
	add dl,1;
	
loop repeta
mov bp, offset R

mov ax, 4c00h
int 21h
text_ ENDS

END start
