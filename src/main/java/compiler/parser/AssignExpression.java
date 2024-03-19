package compiler.parser;

public class AssignExpression extends Expression {
    IDExpression lhs;
    Expression rhs;
    AssignExpression(IDExpression inLHS, Expression inRHS){
        lhs = inLHS;
        rhs = inRHS;
    }
}