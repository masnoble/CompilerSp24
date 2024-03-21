package compiler.parser;

public class ReturnStatement extends Statement{
    Expression expression; // may be null
    ReturnStatement(Expression inExpression){
        expression = inExpression;
    }

    void print(String prefix) {
        System.out.print(prefix + "return");

        if(expression != null){
            System.out.print(" ");
            expression.print(prefix + "    ");
        }

        System.out.println(prefix + ";");
    }
}