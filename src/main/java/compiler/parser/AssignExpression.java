package compiler.parser;

import java.io.BufferedWriter;
import java.io.IOException;

public class AssignExpression extends Expression {
    VarExpression lhs;
    Expression rhs;
    AssignExpression(VarExpression inLHS, Expression inRHS){
        lhs = inLHS;
        rhs = inRHS;
    }

    void print(String prefix, BufferedWriter writer) throws IOException{
        writer.write(prefix + "=");
        lhs.print(prefix + "    ", writer);
        rhs.print(prefix + "    ", writer);
    }
}