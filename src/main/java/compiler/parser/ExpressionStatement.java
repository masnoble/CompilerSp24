package compiler.parser;

import java.io.BufferedWriter;
import java.io.IOException;

import compiler.lowlevel.Function;

public class ExpressionStatement extends Statement{
    Expression expression;
    
    ExpressionStatement(Expression inExpression){
        expression = inExpression;
    }

    void genLLCode(Function f){
        expression.genLLCode(f);
    }

    void print(String prefix, BufferedWriter writer) throws IOException{
        if(expression != null){
            expression.print(prefix, writer);
        }
        writer.write(prefix + ";");
    }
}
    

