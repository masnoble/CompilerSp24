package compiler.parser;

import java.util.ArrayList;

public class CallExpression extends Expression{
    String ID;
    ArrayList<Expression> args;

    CallExpression(String inID, ArrayList<Expression> inArgs){
        ID = inID;
        args = inArgs;
    }

    void print(String prefix){
        System.out.println(prefix + ID + "(");
        if(args.isEmpty()){
            System.out.println(prefix + "    " +  "void");
        } else {
            for (int i = 0; i < args.size(); i++){
                args.get(i).print(prefix + "    ");
            }
        }
        System.out.println(prefix + ")");
    }
}