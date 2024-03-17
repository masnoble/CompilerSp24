package compiler.parser;

import java.lang.reflect.Parameter;

public class Parser {
    private Program parseProgram();
    private Declaration parseDeclaration();
    private Declaration parseDeclarationPrime(bool INTVOID, string ID);
    private Declaration parseFunDeclaration(bool INTVOID, string ID);
    private Param parseParams();
    private Param parseParam();
    private CompoundStmt parseCompoundStmt();
    private Statement parseStatement();
    private Expression parseExpression();
    private BinaryExpression parseBinaryExpression();
    private Expression parseFactor();
    private Expression parseArgs();
    
}

