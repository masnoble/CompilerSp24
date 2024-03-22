package compiler.parser;

public class ExpressionStatement extends Statement{
    Expression expression;
    ExpressionStatement(Expression inExpression){
        expression = inExpression;
    }

    void print(String prefix){
        expression.print(prefix);
        System.out.println(prefix + ";");
    }
}
    

