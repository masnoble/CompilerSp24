package compiler.parser;

import java.io.BufferedWriter;
import java.io.IOException;

public class Param{
    String id;
    boolean bracket;
    Param(String inID, boolean inBracket){
        id = inID;
        bracket = inBracket;
    }

    void print(String prefix, BufferedWriter writer) throws IOException{
        writer.write(prefix + "int " + id + " ");
        if(bracket){
            writer.write("[]");
        } else {
            writer.write("");
        }
    }
}