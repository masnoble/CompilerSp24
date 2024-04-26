.text
	.align 4
.globl  josh
josh:
josh_bb2:
	movl	%EDI, %EAX
josh_bb3:
	addl	%ESI, %EAX
	movl	%EAX, %EDI
josh_bb4:
	movl	$10, %EAX
	cmpl	%EAX, %EDI
	jge	josh_bb5
	movl	$1, %EAX
	addl	%EAX, %EDI
	jmp	josh_bb4
josh_bb5:
	movl	%EDI, %EAX
josh_bb6:
	ret
.globl  david
david:
david_bb2:
	pushq	%R15
david_bb3:
	movl	$5, %EAX
	movl	%EAX, %EDI
	movl	$6, %EAX
	movl	%EAX, %ESI
	movl	$2, %EAX
	movl	%EAX, %EDX
	call	josh
	movl	$7, %EAX
	movl	%R15D, %EDI
	addl	%EAX, %EDI
	movl	%EDI, %EAX
david_bb4:
	popq	%R15
	ret
