package compiler.parser;

import java.io.BufferedWriter;
import java.io.IOException;

import compiler.lowlevel.Function;
import compiler.lowlevel.Operand;
import compiler.lowlevel.Operand.OperandType;
import compiler.lowlevel.Operation;
import compiler.lowlevel.Operation.OperationType;

public class BinaryExpression extends Expression{
    Expression lhs;
    Expression rhs;
    String operator;

    BinaryExpression(Expression inLHS, Expression inRHS, String inOperator){
        lhs = inLHS;
        rhs = inRHS;
        operator = inOperator;
    }
    
    // returns register number 
    int genLLCode(Function f){
        int expReg = f.getNewRegNum();
        Operand expOp = new Operand(OperandType.REGISTER, expReg);

        int lhsReg = lhs.genLLCode(f);
        Operand lhsOp = new Operand(OperandType.REGISTER, lhsReg);
        
        int rhsReg = rhs.genLLCode(f);
        Operand rhsOp = new Operand(OperandType.REGISTER, rhsReg);
        
        Operation op = new Operation(getOperationType(operator), f.getCurrBlock());
        op.setSrcOperand(0,lhsOp);
        op.setSrcOperand(1,rhsOp);


        op.setDestOperand(0, expOp);
        f.getCurrBlock().appendOper(op);
    
        return expReg;
    }

    OperationType getOperationType(String operator){
        switch(operator){
            case "+":     
                return OperationType.ADD_I;
            case "-":
                return OperationType.SUB_I;
            case "*":
                return OperationType.MUL_I;
            case "/":
                return OperationType.DIV_I;
            case "<":
                return OperationType.LT;
            case ">":
                return OperationType.GT;
            case ">=":
                return OperationType.GTE;
            case "<=":
                return OperationType.LTE;
            case "==":
                return OperationType.EQUAL;
        }

        return null;
    }

    
    void print(String prefix, BufferedWriter writer) throws IOException{
        writer.write(prefix + operator);
        lhs.print(prefix + "    ", writer);
        rhs.print(prefix + "    ", writer);
    }
}