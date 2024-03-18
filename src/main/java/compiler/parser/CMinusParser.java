package compiler.parser;

public class CMinusParser implements Parser {

    public void parse() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parse'");
    }
    
    public void printTree() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'printTree'");
    }

    
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