package compiler.parser;

import java.io.BufferedWriter;
import java.io.IOException;

public class VarExpression extends Expression{
    String ID;
    Expression expression;

    VarExpression(String inID, Expression inExpression){
        ID = inID;
        expression = inExpression;
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