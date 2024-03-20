package compiler.parser;

public class ReturnStatement extends Statement{
    Expression expression;
    ReturnStatement(Expression inExpression){
        expression = inExpression;
    }
}