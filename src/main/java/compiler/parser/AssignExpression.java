package compiler.parser;

public class AssignExpression extends Expression {
    VarExpression lhs;
    Expression rhs;
    AssignExpression(VarExpression inLHS, Expression inRHS){
        lhs = inLHS;
        rhs = inRHS;
    }
}