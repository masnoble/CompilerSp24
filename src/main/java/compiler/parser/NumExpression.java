package compiler.parser;

public class NumExpression extends Expression{
    int num;
    NumExpression(int inNum){
        num = inNum;
    }

    void print(String prefix){
        System.out.println(prefix + num);
    }
}