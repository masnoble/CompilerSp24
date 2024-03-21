package compiler.parser;

public class SelectionStatement extends Statement{
    Expression expression;
    Statement statement;
    Statement elseStatement; //may be null
    SelectionStatement(Expression inExpression, Statement inStatement, Statement inElseStatement){
        expression = inExpression;
        statement = inStatement;
        elseStatement = inElseStatement;
    }

    void print(String prefix) {
        System.out.println(prefix + "if(");
        expression.print(prefix + "    ");
        System.out.println(prefix + "{");
        statement.print(prefix + "    ");
        System.out.println(prefix + "}");

        if(elseStatement != null){
            System.out.println(prefix + "else{");
            elseStatement.print(prefix + "    ");
            System.out.println(prefix + "}");
        }
    }

}