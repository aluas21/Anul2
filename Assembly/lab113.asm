; Sa se evalueze expresa (3+(c*c))/(6-(b*b))+((a*a-b*b)/(a*a+c*c))

ASSUME cs:text_,ds:data_

data_ SEGMENT
a db 5
b db 6
c db 7
val1 dw ?
val2 dw ?
rez dw ?

data_ ENDS

text_ SEGMENT

start:
mov ax, data_
mov ds, ax

;bx = 3+(c*c)
mov al, c
imul c
mov dx,ax
mov ax,3
add ax,dx
mov bx,ax

;cx = 6-(b*b)
mov al,b
imul b
mov dx,6
sub dx,ax;
mov cx,dx;

;ax->ax:dx, val1 = (3+(c*c))/(6-b*b)
mov ax,bx
cwd
idiv cx
mov val1,ax


;bx = a*a-b*b
mov al,b
imul b
mov bx,ax
mov al,a
imul a
sub ax,bx
mov bx,ax

;cx = a*a+c*c
mov al,c 
imul c 
mov cx,ax
mov al,a
imul a
add cx,ax

;ax->dx:ax, val2 = (a*a-b*b)/(a*a+c*c)
mov ax,bx
cwd
idiv cx
mov val2,ax


add ax,val1
mov rez,ax
mov ax, 4c00h
int 21h
text_ ENDS

END start