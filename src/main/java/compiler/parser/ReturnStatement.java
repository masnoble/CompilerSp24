package compiler.parser;

import java.io.BufferedWriter;
import java.io.IOException;

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
}