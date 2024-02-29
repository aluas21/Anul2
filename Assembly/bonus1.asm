; Sa se evalueze expresa (3+(c*c))/(6-(b*b))+((a*a-b*b)/(a*a+c*c))

ASSUME cs:text_,ds:data_

data_ SEGMENT
a dw 4
b dw 2
c dw 3
d db 5
val1 dw ?
val2 dw ?
rez dw ?NN

data_ ENDS

text_ SEGMENT

start:
mov ax, data_
mov ds, ax

mov ax,b;
imul c; dx:ax = b*c
mov cx,dx;cx:bx->dx:ax
mov bx,ax;
mov ax,a
cwd
add bx,ax
adc cx,dx
mov al,d;
cbw;
mov val1,ax
mov ax,bx
mov dx,cx
idiv val1


mov rez,ax


mov ax, 4c00h
int 21h
text_ ENDS

END start