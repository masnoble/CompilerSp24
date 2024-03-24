package compiler.parser;

public class ExpressionStatement extends Statement{
    Expression expression;
    ExpressionStatement(Expression inExpression){
        expression = inExpression;
    }

    void print(String prefix){
        if(expression != null){
            expression.print(prefix);
        }
        System.out.println(prefix + ";");
    }
}
    

