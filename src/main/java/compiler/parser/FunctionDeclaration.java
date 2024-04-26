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
            String paramId = params.get(0).id;

            FuncParam param = new FuncParam(1, paramId);
            f.setFirstParam(param);
            
            int reg = f.getNewRegNum();
            f.getTable().put(paramId, reg);

            for(int i = 1; i < params.size(); i++){
                paramId = params.get(i).id;
                
                param.setNextParam(new FuncParam(1, paramId));
                
                reg = f.getNewRegNum();
                f.getTable().put(paramId, reg);
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

        if(f.getFirstUnconnectedBlock() != null){
            f.appendBlock(f.getFirstUnconnectedBlock());
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