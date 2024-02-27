package compiler.parser;

class OperatorExpression extends Expression {

    Expression lhs = null;
    Expression rhs = null;

    char op;

    public OperatorExpression(char type, Expression l, Expression r){
        op = type;
        lhs = l;
        rhs = r;
    }

    long compute(){
        if (op == '+'){
            return lhs.compute() + rhs.compute();
        }
        else{
            return lhs.compute() * rhs.compute();
        }
    }
}
