	.section        ".text"
	.align 4
	.global tailrecursive
	.type    tailrecursive, #function
tailrecursive:
	!#PROLOGUE# 0
	save     %sp, -120, %sp
	!#PROLOGUE# 1
	mov     %i0, %i0
	mov     %i0, %i2
	mov     %g0, %i1
	cmp     %i2, %i1
	bg      .L1
	nop
.L2:
	ba      .L3
	nop
.L4:
.L1:
	mov     12, %o0
	call    malloc
	nop
	mov     %o0, %o0
	mov     %o0, %i1
	mov     %i0, %i2
	mov     1, %i1
	sub     %i2, %i1, %o0
	mov     %o0, %o0
	call    tailrecursive
	nop
.L3:
.L5:
	ret
	restore
	.size    tailrecursive, .-tailrecursive
	.align 4
	.global add
	.type    add, #function
add:
	!#PROLOGUE# 0
	save     %sp, -112, %sp
	!#PROLOGUE# 1
	mov     %i0, %i0
	mov     %i1, %i1
	mov     %i0, %i2
	mov     %i1, %i0
	add     %i2, %i0, %i0
	mov     %i0, %i0
.L6:
.L7:
	ret
	restore
	.size    add, .-add
	.align 4
	.global domath
	.type    domath, #function
domath:
	!#PROLOGUE# 0
	save     %sp, -128, %sp
	!#PROLOGUE# 1
	mov     %i0, %i2
	mov     12, %o0
	call    malloc
	nop
	mov     %o0, %o0
	mov     %o0, %i5
	mov     4, %o0
	call    malloc
	nop
	mov     %o0, %o0
	mov     %i5, %i5
	st      %o0, [%i5+8]
	mov     12, %o0
	call    malloc
	nop
	mov     %o0, %o0
	mov     %o0, %i4
	mov     4, %o0
	call    malloc
	nop
	mov     %o0, %o0
	mov     %i4, %i4
	st      %o0, [%i4+8]
	mov     %i2, %i2
	mov     %i5, %i5
	st      %i2, [%i5]
	mov     3, %i1
	mov     %i4, %i4
	st      %i1, [%i4]
	mov     %i5, %i5
	ldsw    [%i5], %i3
	mov     %i5, %i5
	ldsw    [%i5+8], %i1
	st      %i3, [%i1]
	mov     %i4, %i4
	ldsw    [%i4], %i3
	mov     %i4, %i4
	ldsw    [%i4+8], %i1
	st      %i3, [%i1]
	mov     %i2, %i2
	mov     %g0, %i1
	cmp     %i2, %i1
	ble     .L8
	nop
.L9:
	mov     %i5, %i5
	ldsw    [%i5], %i3
	mov     %i4, %i4
	ldsw    [%i4], %i1
	sra     %i3, %g0, %i3
	sra     %i1, %g0, %i1
	mulx    %i3, %i1, %o0
	mov     %o0, %o0
	mov     %i5, %i5
	ldsw    [%i5+8], %i1
	ldsw    [%i1], %i3
	mov     %o0, %i1
	sra     %i1, %g0, %i1
	sra     %i3, %g0, %i3
	mulx    %i1, %i3, %o1
	mov     %i4, %i4
	ldsw    [%i4], %o0
	sra     %o1, %g0, %o1
	sra     %o0, %g0, %o0
	sdivx   %o1, %o0, %o0
	mov     %o0, %o0
	mov     %i4, %i4
	ldsw    [%i4+8], %i1
	ldsw    [%i1], %o0
	mov     %i5, %i5
	ldsw    [%i5], %o1
	mov     %o0, %o0
	mov     %o1, %o1
	call    add
	nop
	mov     %o0, %o0
	mov     %o0, %o0
	mov     %i4, %i4
	ldsw    [%i4], %i3
	mov     %i5, %i5
	ldsw    [%i5], %i1
	sub     %i3, %i1, %i1
	mov     %i1, %o0
	mov     %i2, %i2
	mov     1, %i1
	sub     %i2, %i1, %i2
	mov     %i2, %i2
	mov     %i2, %i2
	mov     %g0, %i1
	cmp     %i2, %i1
	bg      .L9
	nop
.L8:
	mov     %i5, %i5
	mov     %i5, %o0
	call    free
	nop
	mov     %i4, %i4
	mov     %i4, %o0
	call    free
	nop
.L10:
	ret
	restore
	.size    domath, .-domath
	.align 4
	.global objinstantiation
	.type    objinstantiation, #function
objinstantiation:
	!#PROLOGUE# 0
	save     %sp, -120, %sp
	!#PROLOGUE# 1
	mov     %i0, %i2
	mov     %i2, %i2
	mov     %g0, %i1
	cmp     %i2, %i1
	ble     .L11
	nop
.L12:
	mov     12, %o0
	call    malloc
	nop
	mov     %o0, %o0
	mov     %o0, %i1
	mov     %i1, %i1
	mov     %i1, %o0
	call    free
	nop
	mov     %i2, %i2
	mov     1, %i1
	sub     %i2, %i1, %i2
	mov     %i2, %i2
	mov     %i2, %i2
	mov     %g0, %i1
	cmp     %i2, %i1
	bg      .L12
	nop
.L11:
.L13:
	ret
	restore
	.size    objinstantiation, .-objinstantiation
	.align 4
	.global ackermann
	.type    ackermann, #function
ackermann:
	!#PROLOGUE# 0
	save     %sp, -112, %sp
	!#PROLOGUE# 1
	mov     %i0, %i0
	mov     %i1, %i1
	mov     %i0, %i0
	mov     %g0, %i2
	cmp     %i0, %i2
	bne     .L14
	nop
.L15:
	mov     %i1, %i1
	mov     1, %i0
	add     %i1, %i0, %i0
	mov     %i0, %i0
	ba      .L16
	nop
.L17:
.L14:
	mov     %i1, %i1
	mov     %g0, %i2
	cmp     %i1, %i2
	bne     .L18
	nop
.L19:
	mov     %i0, %i1
	mov     1, %i0
	sub     %i1, %i0, %o0
	mov     1, %o1
	mov     %o0, %o0
	mov     %o1, %o1
	call    ackermann
	nop
	mov     %o0, %o0
	mov     %o0, %i0
	ba      .L20
	nop
.L18:
	mov     %i1, %i2
	mov     1, %i1
	sub     %i2, %i1, %o1
	mov     %i0, %o0
	mov     %o0, %o0
	mov     %o1, %o1
	call    ackermann
	nop
	mov     %o0, %o1
	mov     %i0, %i1
	mov     1, %i0
	sub     %i1, %i0, %o0
	mov     %o0, %o0
	mov     %o1, %o1
	call    ackermann
	nop
	mov     %o0, %o0
	mov     %o0, %i0
.L16:
.L20:
.L21:
.L22:
	ret
	restore
	.size    ackermann, .-ackermann
	.align 4
	.global main
	.type    main, #function
main:
	!#PROLOGUE# 0
	save     %sp, -136, %sp
	!#PROLOGUE# 1
	add     %fp, -24, %o1
	sethi   %hi(.LLC2), %i0
	or      %i0, %lo(.LLC2), %o0
	mov     %o1, %o1
	call    scanf
	nop
	ldsw    [%fp-24], %i5
	add     %fp, -32, %o1
	sethi   %hi(.LLC2), %i0
	or      %i0, %lo(.LLC2), %o0
	mov     %o1, %o1
	call    scanf
	nop
	ldsw    [%fp-32], %i4
	add     %fp, -28, %o1
	sethi   %hi(.LLC2), %i0
	or      %i0, %lo(.LLC2), %o0
	mov     %o1, %o1
	call    scanf
	nop
	ldsw    [%fp-28], %i3
	add     %fp, -20, %o1
	sethi   %hi(.LLC2), %i0
	or      %i0, %lo(.LLC2), %o0
	mov     %o1, %o1
	call    scanf
	nop
	ldsw    [%fp-20], %i2
	add     %fp, -36, %o1
	sethi   %hi(.LLC2), %i0
	or      %i0, %lo(.LLC2), %o0
	mov     %o1, %o1
	call    scanf
	nop
	ldsw    [%fp-36], %i1
	mov     %i5, %i5
	mov     %i5, %o0
	call    tailrecursive
	nop
	mov     %i5, %i5
	sethi   %hi(.LLC1), %i0
	or      %i0, %lo(.LLC1), %o0
	mov     %i5, %o1
	call    printf
	nop
	mov     %i4, %i4
	mov     %i4, %o0
	call    domath
	nop
	mov     %i4, %i4
	sethi   %hi(.LLC1), %i0
	or      %i0, %lo(.LLC1), %o0
	mov     %i4, %o1
	call    printf
	nop
	mov     %i3, %i3
	mov     %i3, %o0
	call    objinstantiation
	nop
	mov     %i3, %i3
	sethi   %hi(.LLC1), %i0
	or      %i0, %lo(.LLC1), %o0
	mov     %i3, %o1
	call    printf
	nop
	mov     %i2, %i2
	mov     %i1, %i1
	mov     %i2, %o0
	mov     %i1, %o1
	call    ackermann
	nop
	mov     %o0, %o1
	sethi   %hi(.LLC1), %i0
	or      %i0, %lo(.LLC1), %o0
	mov     %o1, %o1
	call    printf
	nop
	mov     %g0, %i0
	mov     %i0, %i0
.L23:
.L24:
	ret
	restore
	.size    main, .-main
	.common globalfoo,4,4

	.section        ".rodata"
	.align 8
.LLC0:
	.asciz  "%d "
	.align 8
.LLC1:
	.asciz  "%d\n"
	.align 8
.LLC2:
	.asciz  "%d"
