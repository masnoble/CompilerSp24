package compiler.parser;

import java.io.BufferedWriter;
import java.io.IOException;

import compiler.compiler.CMinusCompiler;
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
        Operand lhsOp;

        //change operatino if this is a global variable
        OperationType opType;
        Operation op;
        if(!f.getTable().containsKey(lhs.ID)){
            opType = OperationType.STORE_I;
            lhsOp =  new Operand(OperandType.STRING,
                                 CMinusCompiler.globalHash.get(lhs.ID));

            op = new Operation(opType, f.getCurrBlock());
            op.setSrcOperand(1, lhsOp);
        }
        else{
            opType = OperationType.ASSIGN;
            lhsOp =  new Operand(OperandType.REGISTER, lhsReg);
            op = new Operation(opType, f.getCurrBlock());
            op.setDestOperand(0, lhsOp);
        }

        
        int rhsReg = rhs.genLLCode(f);
        Operand rhsOp = new Operand(OperandType.REGISTER, rhsReg);

        
        op.setSrcOperand(0,rhsOp);

        
        f.getCurrBlock().appendOper(op);
    
        return lhsReg;
    }

    void print(String prefix, BufferedWriter writer) throws IOException{
        writer.write(prefix + "=");
        lhs.print(prefix + "    ", writer);
        rhs.print(prefix + "    ", writer);
    }
}