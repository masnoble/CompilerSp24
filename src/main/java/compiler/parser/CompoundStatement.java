package compiler.parser;

import java.util.ArrayList;

public class CompoundStatement extends Statement{
    ArrayList<VarDeclaraction> localDeclaration;
    ArrayList<Statement> statement;
    CompoundStatement(ArrayList<VarDeclaraction> inLocalDeclaration, ArrayList<Statement> inStatement){
        localDeclaration = inLocalDeclaration;
        statement = inStatement;
    }
}