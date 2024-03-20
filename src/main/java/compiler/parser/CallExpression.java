package compiler.parser;

import java.util.ArrayList;

public class CallExpression extends Expression{
    String ID;
    ArrayList<Expression> args;

    CallExpression(String inID, ArrayList<Expression> inArgs){
        ID = inID;
        args = inArgs;
    }
}