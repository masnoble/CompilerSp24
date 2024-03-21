package compiler.parser;

public class VarExpression extends Expression{
    String ID;
    Expression expression;

    VarExpression(String inID, Expression inExpression){
        ID = inID;
        expression = inExpression;
    }

    void print(String prefix) {
        if(expression == null){
            System.out.println(prefix + ID);
        }
        else{
            System.out.println(prefix + ID + "[");
            expression.print(prefix + "    ");
            System.out.println(prefix + "]");
        }
    }
}