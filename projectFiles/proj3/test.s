.text
	.align 4
.globl  josh
josh:
josh_bb2:
josh_bb3:
	movl	$1, %EDI
	movl	$2, %EAX
	addl	%EAX, %EDI
josh_bb4:
	movl	$10, %EAX
	cmpl	%EAX, %EDI
	jge	josh_bb6
	movl	$1, %EAX
	addl	%EAX, %EDI
	jmp	josh_bb4
josh_bb6:
	ret
