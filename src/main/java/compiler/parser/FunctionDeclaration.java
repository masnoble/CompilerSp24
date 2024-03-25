package compiler.parser;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.IOException;

public class FunctionDeclaration extends Declaration{
    ArrayList<Param> params;
    CompoundStatement compoundStatement;
    
    FunctionDeclaration (boolean IV, String ID, ArrayList<Param> inParams, CompoundStatement inCS){
        super(IV, ID, 0);
        params = inParams;
        compoundStatement = inCS;
    }

    @Override
    void print(String prefix, BufferedWriter writer) throws IOException{
        if(params.isEmpty()){
            writer.write(prefix + ID + "( )");
        }
        else{
            writer.write(prefix + ID + "(");
            for (int i = 0; i < params.size(); i++){
                params.get(i).print(prefix + "    ", writer);
            }
            writer.write(prefix + ")");
        }
        compoundStatement.print(prefix + "    ", writer);
    }
}