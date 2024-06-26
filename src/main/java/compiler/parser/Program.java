package compiler.parser;

import java.util.ArrayList;

import compiler.lowlevel.CodeItem;
import java.io.BufferedWriter;
import java.io.IOException;

public class Program{
    ArrayList<Declaration> declarations = new ArrayList<>();

    

    public CodeItem genLLCode(){
        
        CodeItem head = declarations.get(0).genLLCode();
        CodeItem curr = head;

        for(int i = 1; i < declarations.size(); i++){
            curr.setNextItem(declarations.get(i).genLLCode());
            curr = curr.getNextItem();
        }
        
        return head;
    }


    void print(BufferedWriter writer)throws IOException{
        writer.write("Program{");
        for(int i = 0; i < declarations.size(); i++){
            declarations.get(i).print("    " + "\r\n", writer);
        }
        writer.write("\r\n"+"}");
    }
}