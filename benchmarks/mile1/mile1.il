calcPower:
	loadinargument base, 0, evil.util.ActivationRecord@182d9c06, r0
	loadinargument exp, 1, evil.util.ActivationRecord@182d9c06, r1
	loadi 1, r3
	mov r3, r2
	mov r1, r4
	loadi 0, r5
	comp r4, r5, ccr
	cbrle ccr, L2, L1
L1:
	mov r2, r7
	mov r0, r8
	mult r7, r8, r9
	mov r9, r2
	mov r1, r10
	loadi 1, r11
	sub r10, r11, r12
	mov r12, r1
	mov r1, r13
	loadi 0, r14
	comp r13, r14, ccr
	cbrgt ccr, L1, L2
L2:
	mov r2, r16
	storeret r16
L3:
L4:
	ret true

main:
	new Power, [exp, base], r5
	mov r5, r0
	addi rarp, input, r6
	read r6
	loadai rarp, input, r1
	mov r1, r7
	mov r0, r8
	storeai r7, r8, base
	addi rarp, input, r9
	read r9
	loadai rarp, input, r1
	mov r1, r10
	loadi 0, r11
	comp r10, r11, ccr
	cbrge ccr, L7, L6
L6:
	loadi -1, r14
	storeret r14
	jumpi L5
L8:
L7:
	mov r1, r15
	mov r0, r16
	storeai r15, r16, exp
	loadi 0, r17
	mov r17, r4
	mov r4, r18
	loadi 1000000, r19
	comp r18, r19, ccr
	cbrge ccr, L10, L9
L9:
	mov r4, r21
	loadi 1, r22
	add r21, r22, r23
	mov r23, r4
	mov r0, r24
	loadai r24, base, r25
	mov r0, r26
	loadai r26, exp, r27
	storeoutargument r25, 0
	storeoutargument r27, 1
	call calcPower, 2
	loadret r28
	mov r28, r2
	mov r4, r29
	loadi 1000000, r30
	comp r29, r30, ccr
	cbrlt ccr, L9, L10
L10:
	mov r2, r32
	println r32
	loadi 1, r33
	storeret r33
L5:
L11:
L12:
	ret true

