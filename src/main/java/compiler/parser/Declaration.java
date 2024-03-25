package compiler.parser;

import java.io.BufferedWriter;
import java.io.IOException;

public class Declaration{
    boolean voidint; //if false is void, if true is int
    String ID; 
    int num;
    
    Declaration(boolean VI, String identifier, int number){
        voidint = VI;
        ID = identifier;
        num = number;
    }
    
    void print(String prefix , BufferedWriter writer) throws IOException{
        String vi;

        if(voidint){
            vi = "int";
        }
        else{
            vi = "void";
        }

        String arr = "";

        if(num > 0){
            arr = "[" + num + "]";
        }

        writer.write(prefix + vi + " " + ID + arr + ";");
    }
}