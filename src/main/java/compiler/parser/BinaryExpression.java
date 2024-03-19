package compiler.parser;

import compiler.scanner.Token.TokenType;

public class BinaryExpression extends Expression{
    Expression lhs;
    Expression rhs;
    TokenType operator;

    BinaryExpression(Expression inLHS, Expression inRHS, TokenType inOperator){
        lhs = inLHS;
        rhs = inRHS;
        operator = inOperator;
    }
    
}