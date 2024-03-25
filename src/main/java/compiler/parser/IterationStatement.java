package compiler.parser;

import java.io.BufferedWriter;
import java.io.IOException;

public class IterationStatement extends Statement{
    Expression expression;
    Statement statement;
    IterationStatement(Expression inExpression, Statement inStatement){
        expression = inExpression;
        statement = inStatement;
    }

    void print(String prefix, BufferedWriter writer) throws IOException{
        writer.write(prefix + "while (");
        expression.print(prefix + "    ", writer);
        writer.write(prefix + ")");
        statement.print(prefix + "    ", writer);
    }

}