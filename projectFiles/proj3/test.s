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
	ret
