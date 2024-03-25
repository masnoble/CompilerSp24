package compiler.parser;

import java.io.BufferedWriter;
import java.io.IOException;

public class SelectionStatement extends Statement{
    Expression expression;
    Statement statement;
    Statement elseStatement; //may be null
    SelectionStatement(Expression inExpression, Statement inStatement, Statement inElseStatement){
        expression = inExpression;
        statement = inStatement;
        elseStatement = inElseStatement;
    }

    void print(String prefix, BufferedWriter writer) throws IOException{
        writer.write(prefix + "if(");
        expression.print(prefix + "    ", writer);
        writer.write(prefix + "){");
        statement.print(prefix + "    ", writer);
        writer.write(prefix + "}");

        if(elseStatement != null){
            writer.write(prefix + "else{");
            elseStatement.print(prefix + "    ", writer);
            writer.write(prefix + "}");
        }
    }

}