package compiler.parser;

public class IterationStatement extends Statement{
    Expression expression;
    Statement statement;
    IterationStatement(Expression inExpression, Statement inStatement){
        expression = inExpression;
        statement = inStatement;
    }
}