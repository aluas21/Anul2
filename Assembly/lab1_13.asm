; Sa se evalueze expresa (3+(c*c))/(6-(b*b))+((a*a-b*b)/(a*a+c*c))

ASSUME cs:text_,ds:data_

data_ SEGMENT
a db 2
b db 4
c db 4
d db 8
rez dw ?
data_ ENDS

text_ SEGMENT

start:
mov ax, data_
mov ds, ax

;bx = 3+(c*c)
mov al, c
imul c   
add ax,3
mov bx, ax

;dx = 6-(b*b)
mov al, b
imul b
mov dx, 6
sub dx, ax
;bx->dx, ax->bx
mov cx, dx
mov ax, bx
cwd
mov bx, cx
idiv bx
mov bx, ax
;bx = (3+(c*c))/(6-b*b)

mov rez,bx


;Terminarea programului
mov ax, 4c00h
int 21h
text_ ENDS

END start