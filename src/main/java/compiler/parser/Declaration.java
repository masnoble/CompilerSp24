package compiler.parser;


public class Declaration{
    boolean voidint; //if false is void, if true is int
    String ID; 
    int num;

    
    Declaration(boolean VI, String identifier, int number){
        voidint = VI;
        ID = identifier;
        num = number;
    }
}