package compiler.parser;

import java.io.BufferedWriter;
import java.io.IOException;

import compiler.lowlevel.BasicBlock;
import compiler.lowlevel.Function;
import compiler.lowlevel.Operand;
import compiler.lowlevel.Operand.OperandType;
import compiler.lowlevel.Operation;
import compiler.lowlevel.Operation.OperationType;

public class SelectionStatement extends Statement{
    Expression expression;
    Statement statement;
    Statement elseStatement; //may be null
    SelectionStatement(Expression inExpression, Statement inStatement, Statement inElseStatement){
        expression = inExpression;
        statement = inStatement;
        elseStatement = inElseStatement;
    }

    void print(String prefix, BufferedWriter writer) throws IOException{
        writer.write(prefix + "if(");
        expression.print(prefix + "    ", writer);
        writer.write(prefix + "){");
        statement.print(prefix + "    ", writer);
        writer.write(prefix + "}");

        if(elseStatement != null){
            writer.write(prefix + "else{");
            elseStatement.print(prefix + "    ", writer);
            writer.write(prefix + "}");
        }
    }

    void genLLCode(Function f) {
        //make 2/3 blocks
        BasicBlock thenBlock = new BasicBlock(f);
        BasicBlock postBlock = new BasicBlock(f);

        //gencode Expression
        int expReg = expression.genLLCode(f);
        Operand expOperand = new Operand(OperandType.REGISTER, expReg);
        
        //create branch
        Operation op = new Operation(OperationType.BEQ, f.getCurrBlock());
        op.setSrcOperand(0,expOperand);
        Operand zero = new Operand(OperandType.INTEGER, 0);
        op.setSrcOperand(1, zero);
        Operand branchDest;
        BasicBlock elseBlock = new BasicBlock(f);;
        if(elseStatement != null){
            branchDest = new Operand(OperandType.BLOCK, elseBlock.getBlockNum());
        } else {
            branchDest = new Operand(OperandType.BLOCK, postBlock.getBlockNum());
        }
        
        op.setSrcOperand(2, branchDest);
        f.getCurrBlock().appendOper(op);
        //append the then
        f.appendToCurrentBlock(thenBlock);
        f.setCurrBlock(thenBlock);
        //gen the then
        statement.genLLCode(f);
        

        //append post
        f.appendToCurrentBlock(postBlock);


        //else stuff
        if(elseStatement != null){
            f.setCurrBlock(elseBlock);
            elseStatement.genLLCode(f);
            
            Operand postBlockOperand = new Operand(OperandType.BLOCK, postBlock.getBlockNum());
            Operation jmpOp = new Operation(OperationType.JMP, elseBlock);
            jmpOp.setSrcOperand(0, postBlockOperand);
            elseBlock.appendOper(jmpOp);
            f.appendUnconnectedBlock(elseBlock);
        }


        f.setCurrBlock(postBlock);
    }

}