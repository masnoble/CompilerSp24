package compiler.parser;

public class ReturnStatement extends Statement{
    Expression expression; // may be null
    ReturnStatement(Expression inExpression){
        expression = inExpression;
    }

    void print(String prefix) {
        System.out.println(prefix + "return");

        if(expression != null){
            expression.print(prefix + "    ");
        }

        System.out.println(prefix + ";");
    }
}