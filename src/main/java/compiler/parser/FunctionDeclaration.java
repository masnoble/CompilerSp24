package compiler.parser;

import java.util.ArrayList;

public class FunctionDeclaration extends Declaration{
    ArrayList<Param> params;
    CompoundStatement compoundStatement;
    
    FunctionDeclaration (boolean IV, String ID, ArrayList<Param> inParams, CompoundStatement inCS){
        super(IV, ID, 0);
        params = inParams;
        compoundStatement = inCS;
    }

    @Override
    void print(String prefix){
        System.out.println(prefix + ID + "(");
        for (int i = 0; i < params.size(); i++){
            params.get(i).print(prefix + "    ");
        }
        System.out.println(prefix + ")");
        compoundStatement.print(prefix + "    ");
    }
}