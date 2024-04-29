package compiler.parser;

import java.util.HashMap;

import compiler.compiler.CMinusCompiler;
import compiler.lowlevel.CodeItem;
import compiler.lowlevel.Data;
import compiler.lowlevel.Function;

public class VarDeclaraction extends Declaration{
    VarDeclaraction(boolean VI, String identifier, int number){
        super(VI, identifier, number);
    }

    void genLLCode(Function f){
        //locals
        //name, create reg num
        int reg = f.getNewRegNum();
        f.getTable().put(ID, reg);
    }
    

    // we don't need print here since it is implemented in Declaration
}