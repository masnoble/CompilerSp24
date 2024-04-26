package compiler.parser;

import java.io.BufferedWriter;
import java.io.IOException;

import compiler.lowlevel.BasicBlock;
import compiler.lowlevel.Function;
import compiler.lowlevel.Operand;
import compiler.lowlevel.Operation;
import compiler.lowlevel.Operand.OperandType;
import compiler.lowlevel.Operation.OperationType;

public class ReturnStatement extends Statement{
    Expression expression; // may be null
    ReturnStatement(Expression inExpression){
        expression = inExpression;
    }

    void print(String prefix, BufferedWriter writer) throws IOException {
        writer.write(prefix + "return");

        if(expression != null){
            expression.print(prefix + "    ", writer);
        }

        writer.write(prefix + ";");
    }

    void genLLCode(Function f) {
        BasicBlock returnBlock =  f.genReturnBlock();
        if(expression != null){
            int regNum = expression.genLLCode(f);
            Operand expOperand = new Operand(OperandType.REGISTER, regNum);


            Operand op = new Operand(OperandType.MACRO, "RetReg");
            Operation oper = new Operation(OperationType.ASSIGN, f.getCurrBlock());
            oper.setSrcOperand(0,expOperand);
            oper.setDestOperand(0,op);
            returnBlock.appendOper(oper);
        }
        
        f.appendToCurrentBlock(returnBlock);
    }
}