package compiler.parser;

import java.io.BufferedWriter;
import java.io.IOException;

import compiler.lowlevel.BasicBlock;
import compiler.lowlevel.Function;
import compiler.lowlevel.Operand;
import compiler.lowlevel.Operand.OperandType;
import compiler.lowlevel.Operation;
import compiler.lowlevel.Operation.OperationType;

public class IterationStatement extends Statement{
    Expression expression;
    Statement statement;
    IterationStatement(Expression inExpression, Statement inStatement){
        expression = inExpression;
        statement = inStatement;
    }

    void print(String prefix, BufferedWriter writer) throws IOException{
        writer.write(prefix + "while (");
        expression.print(prefix + "    ", writer);
        writer.write(prefix + ")");
        statement.print(prefix + "    ", writer);
    }

    void genLLCode(Function f) {
        //make 2/3 blocks
        BasicBlock thenBlock = new BasicBlock(f);
        BasicBlock postBlock = new BasicBlock(f);

        
        //append the then
        f.appendToCurrentBlock(thenBlock);
        f.setCurrBlock(thenBlock);


        //gencode Expression
        int expReg = expression.genLLCode(f);
        Operand expOperand = new Operand(OperandType.REGISTER, expReg);

        
        //branch to post if needed
        Operation op = new Operation(OperationType.BEQ, f.getCurrBlock());
        op.setSrcOperand(0,expOperand);
        Operand zero = new Operand(OperandType.INTEGER, 0);
        op.setSrcOperand(1, zero);
        Operand branchDest;
        branchDest = new Operand(OperandType.BLOCK, postBlock.getBlockNum());        
        op.setSrcOperand(2, branchDest);
        f.getCurrBlock().appendOper(op);


        //Now do the then code
        statement.genLLCode(f);


        // unconditionally go back to beginning of then
        Operand thenBlockOperand = new Operand(OperandType.BLOCK, thenBlock.getBlockNum());
        Operation jmpOp = new Operation(OperationType.JMP, thenBlock);
        jmpOp.setSrcOperand(0, thenBlockOperand);
        thenBlock.appendOper(jmpOp);


        //append post
        f.appendToCurrentBlock(postBlock);
        f.setCurrBlock(postBlock);
    }   

}