package compiler.parser;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.IOException;

public class CallExpression extends Expression{
    String ID;
    ArrayList<Expression> args;

    CallExpression(String inID, ArrayList<Expression> inArgs){
        ID = inID;
        args = inArgs;
    }

    void print(String prefix, BufferedWriter writer) throws IOException{
        writer.write(prefix + ID + "(");
        if(args.isEmpty()){
            writer.write(prefix + "    " +  "void");
        } else {
            for (int i = 0; i < args.size(); i++){
                args.get(i).print(prefix + "    ", writer);
            }
        }
        writer.write(prefix + ")");
    }
}