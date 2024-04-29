package compiler.parser;

import java.io.BufferedWriter;
import java.io.IOException;

import compiler.compiler.CMinusCompiler;
import compiler.lowlevel.Function;
import compiler.lowlevel.Operand;
import compiler.lowlevel.Operation;
import compiler.lowlevel.Operand.OperandType;
import compiler.lowlevel.Operation.OperationType;

public class VarExpression extends Expression{
    String ID;
    Expression expression;

    VarExpression(String inID, Expression inExpression){
        ID = inID;
        expression = inExpression;
    }

    int genLLCode(Function f) {

        Object var = f.getTable().get(ID);

        if(var == null){
            int reg = f.getNewRegNum();
            Operation loadOperation = new Operation(OperationType.LOAD_I, f.getCurrBlock());

            String varId = (String) CMinusCompiler.globalHash.get(ID);
            loadOperation.setSrcOperand(0, new Operand(OperandType.STRING, varId));
            loadOperation.setDestOperand(0, new Operand(OperandType.REGISTER, reg));

            f.getCurrBlock().appendOper(loadOperation);

            return reg;
        }

        return (int) var;
    }

    void print(String prefix, BufferedWriter writer) throws IOException {
        if(expression == null){
            writer.write(prefix + ID);
        }
        else{
            writer.write(prefix + ID + "[");
            expression.print(prefix + "    ", writer);
            writer.write(prefix + "]");
        }
    }
}