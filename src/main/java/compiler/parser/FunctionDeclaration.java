package compiler.parser;

import java.io.BufferedWriter;
import java.util.ArrayList;

import compiler.lowlevel.BasicBlock;
import compiler.lowlevel.FuncParam;
import compiler.lowlevel.Function;
import compiler.lowlevel.Operation;
import compiler.lowlevel.Operation.OperationType;

import java.io.IOException;

public class FunctionDeclaration extends Declaration{
    ArrayList<Param> params;
    CompoundStatement compoundStatement;
    
    FunctionDeclaration (boolean IV, String ID, ArrayList<Param> inParams, CompoundStatement inCS){
        super(IV, ID, 0);
        params = inParams;
        compoundStatement = inCS;
    }

    Function genLLCode() {
        int type;

        if(voidint){
            type = 1;
        }
        else{
            type = 0;
        }
        Function f = new Function(type, ID);

        if (params.size() > 0){
            FuncParam param = new FuncParam(1, params.get(0).id);
            f.setFirstParam(param);
            for(int i = 1; i < params.size(); i++){
                param.setNextParam(new FuncParam(1, params.get(i).id));
            }
        }

        f.createBlock0();
        BasicBlock block = new BasicBlock(f);
        f.appendBlock(block);

        f.setCurrBlock(block);
        compoundStatement.genLLCode(f);


        
        if(f.getLastBlock().getFirstOper().getType() != OperationType.FUNC_EXIT){
            BasicBlock returnBlock =  f.genReturnBlock();
    
            f.appendBlock(returnBlock);
        }

        return f;
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