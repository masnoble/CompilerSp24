package compiler.parser;

public class AssignExpression extends Expression {
    VarExpression lhs;
    Expression rhs;
    AssignExpression(VarExpression inLHS, Expression inRHS){
        lhs = inLHS;
        rhs = inRHS;
    }

    void print(String prefix){
        System.out.println(prefix + "=");
        lhs.print(prefix + "    ");
        rhs.print(prefix + "    ");
    }
}