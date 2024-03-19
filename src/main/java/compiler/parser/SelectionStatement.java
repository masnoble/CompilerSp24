package compiler.parser;

public class SelectionStatement extends Statement{
    Expression expression;
    Statement statement;
    Statement elseStatement;
    SelectionStatement(Expression inExpression, Statement inStatement, Statement inElseStatement){
        expression = inExpression;
        statement = inStatement;
        elseStatement = inElseStatement;
    }
}