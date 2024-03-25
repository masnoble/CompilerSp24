package compiler.parser;

import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.IOException;

public class Program{
    ArrayList<Declaration> declarations = new ArrayList<>();


    void print(BufferedWriter writer)throws IOException{
        writer.write("Program{");
        for(int i = 0; i < declarations.size(); i++){
            declarations.get(i).print("    " + "\r\n", writer);
        }
        writer.write("\r\n"+"}");
    }
}