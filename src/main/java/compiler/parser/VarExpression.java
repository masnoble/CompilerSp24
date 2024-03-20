package compiler.parser;

public class VarExpression extends Expression{
    String ID;
    Expression expression;

    VarExpression(String inID, Expression inExpression){
        ID = inID;
        expression = inExpression;
    }
}