package compiler.parser;

import java.io.BufferedWriter;
import java.io.IOException;

public class BinaryExpression extends Expression{
    Expression lhs;
    Expression rhs;
    String operator;

    BinaryExpression(Expression inLHS, Expression inRHS, String inOperator){
        lhs = inLHS;
        rhs = inRHS;
        operator = inOperator;
    }
    
    void print(String prefix, BufferedWriter writer) throws IOException{
        writer.write(prefix + operator);
        lhs.print(prefix + "    ", writer);
        rhs.print(prefix + "    ", writer);
    }
}