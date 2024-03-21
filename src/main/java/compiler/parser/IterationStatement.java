package compiler.parser;

public class IterationStatement extends Statement{
    Expression expression;
    Statement statement;
    IterationStatement(Expression inExpression, Statement inStatement){
        expression = inExpression;
        statement = inStatement;
    }

    void print(String prefix){
        System.out.println(prefix + "while (");
        expression.print(prefix + "    ");
        System.out.println(prefix + ")");
        statement.print(prefix + "    ");
    }

}