package compiler.parser;


public class Declaration{
    private boolean voidint; //if false is void, if true is int
    private String ID; 
    private int num;
    private CompoundStatement CompoundStatement;
    private Param param;

    
    Declaration(boolean VI, String identifier, int number){
        voidint = VI;
        ID = identifier;
        num = number;
    }

    Declaration(boolean VI, String identifier, Param params, CompoundStatement CS){
        voidint = VI;
        ID = identifier;
        param = params; 
        CompoundStatement = CS;
    }

}