package compiler.parser;

import java.io.BufferedWriter;
import java.util.ArrayList;

import compiler.lowlevel.Function;

import java.io.IOException;

public class CompoundStatement extends Statement{
    ArrayList<VarDeclaraction> localDeclaration;
    ArrayList<Statement> statements;

    CompoundStatement(ArrayList<VarDeclaraction> inLocalDeclaration, ArrayList<Statement> inStatement){
        localDeclaration = inLocalDeclaration;
        statements = inStatement;
    }

    void genLLCode(Function f){
        for(int i = 0; i < localDeclaration.size(); i++){
            localDeclaration.get(i).genLLCode(f);
        }

        for(int i = 0; i < statements.size(); i++){
            statements.get(i).genLLCode(f);
        }
    }

    void print(String prefix , BufferedWriter writer) throws IOException{
        writer.write(prefix + "{");
        for (int i = 0; i < localDeclaration.size(); i++){
            localDeclaration.get(i).print(prefix + "    ", writer);
        }
        for (int i = 0; i < statements.size(); i++){
            statements.get(i).print(prefix + "    ", writer);
        }
        writer.write(prefix + "}");
    }

}