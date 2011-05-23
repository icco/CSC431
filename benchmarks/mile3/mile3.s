	.section	".text"
	.align 4
	.global sum
	.type sum, #function
sum:
	!#PROLOGUE# 0
	save %sp, -120, %sp
	!#PROLOGUE# 1
	mov %i1, %l3
	mov %i2, %i1
	add %l3, %i1, %i1
	mov %i1, %i0
	ret
	restore
	.size sum, .-sum
	.align 4
	.global main
	.type main, #function
main:
	!#PROLOGUE# 0
	save %sp, -120, %sp
	!#PROLOGUE# 1
	set 1, %l3
	set 2, %i1
	cmp %l3, %i1
	set 0, %i1
	movl %icc, 1, %i1
	cmp %i1, 1
	be if1then
	nop
	ba if1else
	nop
if1else:
	set 2, %i1
	set 2, %l3
	mov %i1, %o1
	mov %l3, %o2
	call sum
	nop
	mov %o0, %i1
	sethi %hi(.LLC1), %i0
	or %i0, %lo(.LLC1), %o0
	mov %i1, %o1
	call printf
	nop
	ba if1after
	nop
if1then:
	set 10000, %i1
	set 2, %l3
	mov %i1, %o1
	mov %l3, %o2
	call sum
	nop
	mov %o0, %i1
	sethi %hi(.LLC1), %i0
	or %i0, %lo(.LLC1), %o0
	mov %i1, %o1
	call printf
	nop
	ba if1after
	nop
if1after:
	set 0, %i1
	mov %i1, %i0
	ret
	restore
	.size main, .-main


.section	".rodata"
.align 8
.LLC0:
	.asciz  "%d "
.align 8
.LLC1:
	.asciz  "%d\n"
.align 8
.LLC2:
	.asciz  "%d"
