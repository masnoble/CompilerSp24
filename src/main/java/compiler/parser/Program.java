package compiler.parser;

import java.util.ArrayList;

public class Program{
    ArrayList<Declaration> declarations = new ArrayList<>();


    void print(){
        System.out.println("Program{");
        for(int i = 0; i < declarations.size(); i++){
            declarations.get(i).print("    ");
        }
        System.out.println("}");
    }
}