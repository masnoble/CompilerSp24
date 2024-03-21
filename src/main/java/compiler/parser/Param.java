package compiler.parser;

public class Param{
    String id;
    boolean bracket;
    Param(String inID, boolean inBracket){
        id = inID;
        bracket = inBracket;
    }

    void print(String prefix){
        System.out.print(prefix + "int " + id + " ");
        if(bracket){
            System.out.println("[]");
        } else {
            System.out.println("");
        }
    }
}