package compiler.parser;

import java.util.ArrayList;

public class CompoundStatement extends Statement{
    ArrayList<VarDeclaraction> localDeclaration;
    ArrayList<Statement> statements;
    CompoundStatement(ArrayList<VarDeclaraction> inLocalDeclaration, ArrayList<Statement> inStatement){
        localDeclaration = inLocalDeclaration;
        statements = inStatement;
    }

    void print(String prefix){
        System.out.println(prefix + "{");
        for (int i = 0; i < localDeclaration.size(); i++){
            localDeclaration.get(i).print(prefix + "    ");
        }
        for (int i = 0; i < statements.size(); i++){
            statements.get(i).print(prefix + "    ");
        }
        System.out.println(prefix + ")");
    }

}