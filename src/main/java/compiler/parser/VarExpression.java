package compiler.parser;

import java.io.BufferedWriter;
import java.io.IOException;

import compiler.lowlevel.Function;

public class VarExpression extends Expression{
    String ID;
    Expression expression;

    VarExpression(String inID, Expression inExpression){
        ID = inID;
        expression = inExpression;
    }

    int genLLCode(Function f) {
        return (int)f.getTable().get(ID);

        //TODO: HANDLE GLOBALS
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