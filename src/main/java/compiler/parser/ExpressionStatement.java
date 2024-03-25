package compiler.parser;

import java.io.BufferedWriter;
import java.io.IOException;

public class ExpressionStatement extends Statement{
    Expression expression;
    ExpressionStatement(Expression inExpression){
        expression = inExpression;
    }

    void print(String prefix, BufferedWriter writer) throws IOException{
        if(expression != null){
            expression.print(prefix, writer);
        }
        writer.write(prefix + ";");
    }
}
    

