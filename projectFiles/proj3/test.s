.text
	.align 4
.globl  putDigit
putDigit:
putDigit_bb2:
putDigit_bb3:
	movl	$48, %EAX
	addl	%EDI, %EAX
	movl	%EAX, %EDI
	call	putchar
putDigit_bb4:
	ret
.globl  printInt
printInt:
printInt_bb2:
	pushq	%R14
	pushq	%R15
	movl	%EDI, %R15D
printInt_bb3:
	movl	$0, %EAX
	movl	%EAX, %R14D
	movl	$10000, %EAX
	cmpl	%EAX, %R15D
	jl	printInt_bb6
printInt_bb4:
	movl	$45, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$1, %EAX
	movl	%EAX, %EDI
	call	putDigit
printInt_bb7:
	popq	%R15
	popq	%R14
	ret
printInt_bb20:
	popq	%R15
	popq	%R14
	ret
printInt_bb12:
	movl	$1, %EAX
	cmpl	%EAX, %R14D
	jne	printInt_bb11
printInt_bb13:
	movl	$0, %EAX
	movl	%EAX, %EDI
	call	putDigit
printInt_bb14:
	jmp	printInt_bb11
printInt_bb17:
	movl	$1, %EAX
	cmpl	%EAX, %R14D
	jne	printInt_bb16
printInt_bb18:
	movl	$0, %EAX
	movl	%EAX, %EDI
	call	putDigit
printInt_bb19:
	jmp	printInt_bb16
printInt_bb6:
	movl	$1000, %EAX
	cmpl	%EAX, %R15D
	jl	printInt_bb9
printInt_bb8:
	movl	$1000, %EDI
	movl	$0, %EDX
	movl	%R15D, %EAX
	idivl	%EDI, %EAX
	movl	%EAX, %R14D
	movl	%R14D, %EDI
	call	putDigit
	movl	$1000, %EDI
	movl	%R14D, %EAX
	imull	%EDI, %EAX
	movl	%R15D, %EDI
	subl	%EAX, %EDI
	movl	%EDI, %R15D
	movl	$1, %EAX
	movl	%EAX, %R14D
printInt_bb9:
	movl	$100, %EAX
	cmpl	%EAX, %R15D
	jl	printInt_bb12
printInt_bb10:
	movl	$100, %EDI
	movl	$0, %EDX
	movl	%R15D, %EAX
	idivl	%EDI, %EAX
	movl	%EAX, %R14D
	movl	%R14D, %EDI
	call	putDigit
	movl	$100, %EDI
	movl	%R14D, %EAX
	imull	%EDI, %EAX
	movl	%R15D, %EDI
	subl	%EAX, %EDI
	movl	%EDI, %R15D
	movl	$1, %EAX
	movl	%EAX, %R14D
printInt_bb11:
	movl	$10, %EAX
	cmpl	%EAX, %R15D
	jl	printInt_bb17
printInt_bb15:
	movl	$10, %EDI
	movl	$0, %EDX
	movl	%R15D, %EAX
	idivl	%EDI, %EAX
	movl	%EAX, %R14D
	movl	%R14D, %EDI
	call	putDigit
	movl	$10, %EDI
	movl	%R14D, %EAX
	imull	%EDI, %EAX
	movl	%R15D, %EDI
	subl	%EAX, %EDI
	movl	%EDI, %R15D
printInt_bb16:
	movl	%R15D, %EDI
	call	putDigit
	jmp	printInt_bb20
.globl  main
main:
main_bb2:
main_bb3:
	movl	$3410, %EAX
	movl	%EAX, %EDI
	call	printInt
	movl	$1, %EAX
main_bb4:
	ret
