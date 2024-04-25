package compiler.parser;

import java.io.BufferedWriter;
import java.io.IOException;

import compiler.lowlevel.Function;
import compiler.lowlevel.Operand;
import compiler.lowlevel.Operand.OperandType;
import compiler.lowlevel.Operation.OperationType;
import compiler.lowlevel.Operation;

public class AssignExpression extends Expression {
    VarExpression lhs;
    Expression rhs;
    AssignExpression(VarExpression inLHS, Expression inRHS){
        lhs = inLHS;
        rhs = inRHS;
    }

    int genLLCode(Function f){
        int lhsReg = lhs.genLLCode(f);
        Operand lhsOp = new Operand(OperandType.REGISTER, lhsReg);
        
        int rhsReg = rhs.genLLCode(f);
        Operand rhsOp = new Operand(OperandType.REGISTER, rhsReg);

        Operation op = new Operation(OperationType.ASSIGN, f.getCurrBlock());
        op.setSrcOperand(0,rhsOp);

        op.setDestOperand(0, lhsOp);
        f.getCurrBlock().appendOper(op);
    
        return lhsReg;
    }

    void print(String prefix, BufferedWriter writer) throws IOException{
        writer.write(prefix + "=");
        lhs.print(prefix + "    ", writer);
        rhs.print(prefix + "    ", writer);
    }
}