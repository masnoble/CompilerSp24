package compiler.parser;

import java.io.BufferedWriter;
import java.io.IOException;

import compiler.lowlevel.Function;
import compiler.lowlevel.Operand;
import compiler.lowlevel.Operand.OperandType;
import compiler.lowlevel.Operation;
import compiler.lowlevel.Operation.OperationType;

public class NumExpression extends Expression{
    int num;
    NumExpression(int inNum){
        num = inNum;
    }

    int genLLCode(Function f) {
        int regNum = f.getNewRegNum();
        
        Operand reg = new Operand(OperandType.REGISTER, regNum);
        Operand value = new Operand(OperandType.INTEGER, num);
        Operation op = new Operation(OperationType.ASSIGN, f.getCurrBlock());
        op.setSrcOperand(0,value);
        op.setDestOperand(0,reg);
        f.getCurrBlock().appendOper(op);
        return regNum;
    }

    void print(String prefix, BufferedWriter writer) throws IOException{
        writer.write(prefix + num);
    }
}