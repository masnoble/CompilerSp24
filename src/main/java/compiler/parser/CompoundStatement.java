package compiler.parser;

import java.util.ArrayList;

public class CompoundStatement extends Statement{
    LocalDeclarations localDeclaration;
    ArrayList<Statement> statement;
    CompoundStatement(LocalDeclarations inLocalDeclaration, ArrayList<Statement> inStatement){
        localDeclaration = inLocalDeclaration;
        statement = inStatement;
    }
}