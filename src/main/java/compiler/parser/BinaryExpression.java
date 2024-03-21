package compiler.parser;

public class BinaryExpression extends Expression{
    Expression lhs;
    Expression rhs;
    String operator;

    BinaryExpression(Expression inLHS, Expression inRHS, String inOperator){
        lhs = inLHS;
        rhs = inRHS;
        operator = inOperator;
    }
    
    void print(String prefix){
        System.out.println(prefix + operator);
        lhs.print(prefix + "    ");
        rhs.print(prefix + "    ");
    }
}