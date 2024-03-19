package compiler.parser;

public class ExpressionStatement extends Statement{
    Expression expression;
    ExpressionStatement(Expression inExpression){
        expression = inExpression;
    }
}