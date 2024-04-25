package compiler.parser;

import java.util.HashMap;

import compiler.compiler.CMinusCompiler;
import compiler.lowlevel.CodeItem;
import compiler.lowlevel.Data;
import compiler.lowlevel.Function;
import compiler.lowlevel.Operation;

public class VarDeclaraction extends Declaration{
    VarDeclaraction(boolean VI, String identifier, int number){
        super(VI, identifier, number);
    }

    CodeItem genLLCode(Function f){
        //locals
        //name, create reg num
        int reg = f.getNewRegNum();
        f.getTable().put(ID, reg);

        int type;

        if(voidint){
            type = 1;
        }
        else{
            type = 0;
        }
        return new Data(type, ID);
    }


    // i think we already have this
    CodeItem genLLCode(){
        //globals
        //cminuscompiler.hashmap
        //global name of variable twice
        HashMap global =  CMinusCompiler.globalHash;
        global.put(ID, ID);

        

        int type;

        if(voidint){
            type = 1;
        }
        else{
            type = 0;
        }
        return new Data(type, ID);
    }

    // we don't need print here since it is implemented in Declaration
}