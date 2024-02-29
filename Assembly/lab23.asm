;Sa se inlocuiasca primii trei biti ai word-ului B cu ultimii trei biti ai byte-ului A

ASSUME cs:text_,ds:data_

data_ SEGMENT
a db 0b2h
b dw 0f0fh


data_ ENDS

text_ SEGMENT

start:
mov ax,data_
mov ds,ax

and b, 1111111111111000b
mov al,a
mov ah,0
and ax,0000000011100000b
shr ax,5
or b,ax


mov ax, 4c00h
int 21h
text_ ENDS

END start