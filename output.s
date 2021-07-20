	.section	.rodata
	.section	.bss
	.globl	a
	.type	a, @object
a:
	.zero	4
	.size	a, 4
	.globl	m
	.type	m, @object
m:
	.zero	4
	.size	m, 4
	.globl	k
	.type	k, @object
k:
	.zero	4
	.size	k, 4
	.globl	i
	.type	i, @object
i:
	.zero	4
	.size	i, 4
	.text
	.globl	main
	.type	main, @function
main:
	addi	sp,sp,-104
	sw	ra,0(sp)
	li t3, 50
	sw t3, 12(sp)
	lw t3, 12(sp)
	addi a0, t3, 1
	slli a0, a0, 2
	call __std_malloc
	mv t4,a0
	sw a0, 16(sp)
	lw t3, 12(sp)
	mv t4,t3
	lw t5, 16(sp)
	sw t3, 0(t5)
	lw t3, 16(sp)
	mv t4,t3
	sw t3, a, t5
	call __std_getInt
	mv t4,a0
	sw a0, 20(sp)
	lw t3, 20(sp)
	mv t4,t3
	sw t3, m, t5
	call __std_getInt
	mv t4,a0
	sw a0, 24(sp)
	lw t3, 24(sp)
	mv t4,t3
	sw t3, k, t5
	li t3, 0
	sw t3, 28(sp)
	lw t3, 28(sp)
	mv t4,t3
	sw t3, i, t5
.loop_condition1:
	lw t3, i
	lw t4, m
	slt t5, t3, t4
	sw t5, 32(sp)
	lw t3, 32(sp)
	beqz t3, .loop_end1
	lw t3, i
	addi t5, t3, 1
	sw t5, 36(sp)
	lw t3, 36(sp)
	slli t3, t3, 2
	sw t3, 36(sp)
	lw t3, 36(sp)
	lw t4, a
	add t3, t3, t4
	sw t3, 36(sp)
	call __std_getInt
	mv t4,a0
	sw a0, 40(sp)
	lw t3, 40(sp)
	mv t4,t3
	lw t5, 36(sp)
	sw t3, 0(t5)
.loop_inc1:
	lw t3, i
	mv t4,t3
	sw t3, 44(sp)
	lw t3, i
	addi t3, t3, 1
	sw t3, i, t6
	j .loop_condition1
.loop_end1:
	li t3, 0
	sw t3, 48(sp)
	lw t3, 48(sp)
	mv t4,t3
	sw t3, i, t5
.loop_condition2:
	lw t3, i
	addi t5, t3, 1
	sw t5, 52(sp)
	lw t3, 52(sp)
	slli t3, t3, 2
	sw t3, 52(sp)
	lw t3, 52(sp)
	lw t4, a
	add t3, t3, t4
	sw t3, 52(sp)
	li t3, 1
	sw t3, 56(sp)
	lw t3, k
	lw t4, 56(sp)
	sub t5, t3, t4
	sw t5, 60(sp)
	lw t3, 60(sp)
	addi t5, t3, 1
	sw t5, 64(sp)
	lw t3, 64(sp)
	slli t3, t3, 2
	sw t3, 64(sp)
	lw t3, 64(sp)
	lw t4, a
	add t3, t3, t4
	sw t3, 64(sp)
	lw t3, 52(sp)
	lw t3, 0(t3)
	lw t4, 64(sp)
	lw t4, 0(t4)
	slt t5, t3, t4
	sw t5, 68(sp)
	lw t3, 68(sp)
	xori t3, t3, 1
	sw t3, 68(sp)
	lw t3, 68(sp)
	beqz t3, .false_statement4
	lw t3, i
	addi t5, t3, 1
	sw t5, 72(sp)
	lw t3, 72(sp)
	slli t3, t3, 2
	sw t3, 72(sp)
	lw t3, 72(sp)
	lw t4, a
	add t3, t3, t4
	sw t3, 72(sp)
	li t3, 0
	sw t3, 76(sp)
	lw t3, 72(sp)
	lw t3, 0(t3)
	lw t4, 76(sp)
	sgt t5, t3, t4
	sw t5, 80(sp)
	lw t3, 80(sp)
	beqz t3, .false_statement4
	li t3, 1
	sw t3, 84(sp)
	j .end_statement4
.false_statement4:
	li t3, 0
	sw t3, 84(sp)
.end_statement4:
	lw t3, 84(sp)
	beqz t3, .false_statement3
	lw t3, i
	lw t4, m
	slt t5, t3, t4
	sw t5, 88(sp)
	lw t3, 88(sp)
	beqz t3, .false_statement3
	li t3, 1
	sw t3, 92(sp)
	j .end_statement3
.false_statement3:
	li t3, 0
	sw t3, 92(sp)
.end_statement3:
	lw t3, 92(sp)
	beqz t3, .loop_end2
.loop_inc2:
	lw t3, i
	mv t4,t3
	sw t3, 96(sp)
	lw t3, i
	addi t3, t3, 1
	sw t3, i, t6
	j .loop_condition2
.loop_end2:
	lw t3, i
	mv a0,t3
	call __std_printInt
	mv t4,a0
	sw a0, 100(sp)
	mv a0,zero
	j .Return_main
.Return_main:
	lw	ra,0(sp)
	addi	sp, sp, 104
	jr	ra
	.size	main, .-main
