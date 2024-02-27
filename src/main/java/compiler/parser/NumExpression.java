package compiler.parser;

public class NumExpression extends Expression {
    
    int val;

    public NumExpression(int v){
        val = v;
    }

    long compute(){
        return val;
    }
}
