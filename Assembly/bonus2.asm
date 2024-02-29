;Sa se inlocuiasca primii trei biti ai word-ului B cu ultimii trei biti ai byte-ului A

ASSUME cs:text_,ds:data_

data_ SEGMENT
;    --100-----011-----111-----001---
a dd 10100111000110001111111100001111b
sum dw 0;

data_ ENDS

text_ SEGMENT
start:
mov ax,data_
mov ds,ax

mov al,byte ptr a[0]
mov ah,0
and ax,0000000000111000b
shr ax,3
add sum,ax

mov al,byte ptr a[1]
mov ah,0
and ax,0000000000111000b
shr ax,3
add sum,ax


mov al,byte ptr a[2]
mov ah,0
and ax,0000000000111000b
shr ax,3
add sum,ax


mov al,byte ptr a[3]
mov ah,0
and ax,0000000000111000b
shr ax,3
add sum,ax




mov ax, 4c00h
int 21h
text_ ENDS

END start

