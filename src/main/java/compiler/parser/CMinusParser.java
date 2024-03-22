package compiler.parser;

import java.util.ArrayList;

import compiler.scanner.CMinusScanner;
import compiler.scanner.Scanner;
import compiler.scanner.Token;
import compiler.scanner.Token.TokenType;

public class CMinusParser implements Parser {
    
    private Scanner myScanner;
    private Program myProgram;

    CMinusParser(String filename){
        myScanner = new CMinusScanner(filename);
    }

    public Program parse() throws CMinusException {
        myProgram = parseProgram();
        
        return myProgram;
    }
    
    public void printTree() {
        myProgram.print();
    }
    
    private Token matchToken(TokenType tokenType) throws CMinusException {
        Token tkn = myScanner.getNextToken();
        if (tkn.getType() != tokenType){
            throw new CMinusException("matchToken exception: expected " + tokenType + " got "+ tkn.getType());
        }
        return tkn;
    }
    
    private String getString (TokenType tokenType) throws CMinusException{
        switch(tokenType){
            case GTE_TOKEN: 
                return ">=";
            case GT_TOKEN:
                return ">";
            case LTE_TOKEN:
                return "<=";
            case LT_TOKEN: 
                return "<";
            case NOT_EQ_TOKEN: 
                return "!=";
            case EQ_TOKEN:
                return "==";
            case PLUS_TOKEN:
                return "+";
            case MINUS_TOKEN:
                return "-";
            case DIVIDE_TOKEN:
                return "/";
            case TIMES_TOKEN:
                return "*";
            default:
                throw new CMinusException("getString Exception: Expected Token not Found");
        }

    }


    private Program parseProgram() throws CMinusException{
        Program program = new Program();

        program.declarations.add(parseDeclaration());

        TokenType currTokenType = myScanner.viewNextToken().getType();
        while(currTokenType == TokenType.VOID_TOKEN || currTokenType == TokenType.INT_TOKEN){
            program.declarations.add(parseDeclaration());
            currTokenType = myScanner.viewNextToken().getType();
        }

        return program;
    }
    
    
    private Declaration parseDeclaration() throws CMinusException{
        TokenType declType = myScanner.getNextToken().getType();
        Boolean IV; 

        if(declType == TokenType.VOID_TOKEN){
            IV = false;
            String id = (String) matchToken(TokenType.ID_TOKEN).getData();         
            return parseFunDeclarationPrime(IV, id);
        }
        else if (declType == TokenType.INT_TOKEN) {
            IV = true;
            String id = (String) matchToken(TokenType.ID_TOKEN).getData();
            return parseDeclarationPrime(IV, id);
        } else {
            //error
            throw new CMinusException("parseDeclaration Exception");
        }
    }


    private Declaration parseDeclarationPrime(boolean IV, String ID) throws CMinusException{    
        switch (myScanner.viewNextToken().getType()) {
            case LBRACKET_TOKEN:
                matchToken(TokenType.LBRACKET_TOKEN);
                int num = Integer.parseInt((String) matchToken(TokenType.NUM_TOKEN).getData());
                matchToken(TokenType.RBRACKET_TOKEN);
                matchToken(TokenType.SEMI_TOKEN);
                return new Declaration(IV, ID, num);
            case SEMI_TOKEN:
                matchToken(TokenType.SEMI_TOKEN);
                return new Declaration(IV, ID, 0);
            case LPAREN_TOKEN:
                return parseFunDeclarationPrime(IV, ID);
            default:
                // err
                throw new CMinusException("parseDeclarationPrime Exception");
        }        
    }

    private Declaration parseFunDeclarationPrime(boolean IV, String ID) throws CMinusException{
        matchToken(TokenType.LPAREN_TOKEN);
        ArrayList<Param> params = parseParams();
        matchToken(TokenType.RPAREN_TOKEN);
        CompoundStatement compoundStatement = parseCompoundStatement();

        return new FunctionDeclaration(IV, ID, params, compoundStatement);
    }


    private ArrayList<Param> parseParams() throws CMinusException{
        switch (myScanner.viewNextToken().getType()) {
            case VOID_TOKEN:
                matchToken(TokenType.VOID_TOKEN);
                return new ArrayList<Param>();
                
            case INT_TOKEN:
                return parseParamList();
        
            default:
                throw new CMinusException("parseParams Exception");
        }
    }

    private ArrayList<Param> parseParamList() throws CMinusException{
        ArrayList<Param> params = new ArrayList<Param>();

        params.add(parseParam());

        while(myScanner.viewNextToken().getType() == TokenType.COMMA_TOKEN){
            matchToken(TokenType.COMMA_TOKEN);
            params.add(parseParam()); 
        }

        return params;
    }

    
    private Param parseParam() throws CMinusException{
        matchToken(TokenType.INT_TOKEN);
        String id = (String) matchToken(TokenType.ID_TOKEN).getData();
        Boolean retBoolean = false;
        if(myScanner.viewNextToken().getType() == TokenType.LBRACKET_TOKEN){
            matchToken(TokenType.LBRACKET_TOKEN);
            matchToken(TokenType.RBRACKET_TOKEN);
            retBoolean = true;
        }

        return new Param(id, retBoolean);
    }

    private CompoundStatement parseCompoundStatement() throws CMinusException{
        matchToken(TokenType.LCURLEY_TOKEN);
        ArrayList<VarDeclaraction> localDecls = parseLocalDeclarations();
        ArrayList<Statement> stmtList  = parseStatementList();
        matchToken(TokenType.RCURLEY_TOKEN);

        return new CompoundStatement(localDecls, stmtList);
    }

    private ArrayList<VarDeclaraction> parseLocalDeclarations() throws CMinusException{
        
        ArrayList<VarDeclaraction> decls = new ArrayList<VarDeclaraction>();

        while(myScanner.viewNextToken().getType() == TokenType.INT_TOKEN){
            decls.add(parseLocaDeclaraction()); 
        }

        return decls;
    }

    private VarDeclaraction parseLocaDeclaraction() throws CMinusException {
        
        matchToken(TokenType.INT_TOKEN);
        String id = (String) matchToken(TokenType.ID_TOKEN).getData();

        if(myScanner.viewNextToken().getType() == TokenType.LBRACKET_TOKEN){
            matchToken(TokenType.LBRACKET_TOKEN);
            int num = Integer.parseInt((String) matchToken(TokenType.NUM_TOKEN).getData());
            matchToken(TokenType.LBRACKET_TOKEN);
            
            return new VarDeclaraction(true, id, num);
        }

        matchToken(TokenType.SEMI_TOKEN);

        return new VarDeclaraction(true, id, 0);
    }
    
    private ArrayList<Statement> parseStatementList() throws CMinusException{
        ArrayList<Statement> stmts = new ArrayList<Statement>();

        TokenType currTok = myScanner.viewNextToken().getType();
        while(currTok == TokenType.SEMI_TOKEN || currTok == TokenType.ID_TOKEN || currTok == TokenType.NUM_TOKEN || currTok == TokenType.LPAREN_TOKEN || currTok == TokenType.LCURLEY_TOKEN || currTok == TokenType.IF_TOKEN || currTok == TokenType.WHILE_TOKEN || currTok == TokenType.RETURN_TOKEN){
            stmts.add(parseStatement()); 
            currTok = myScanner.viewNextToken().getType();
        }

        return stmts;
        

    }

    private Statement parseStatement() throws CMinusException{
        switch (myScanner.viewNextToken().getType()) {
            case LCURLEY_TOKEN:
                return parseCompoundStatement();
            case SEMI_TOKEN:
            case ID_TOKEN:
            case NUM_TOKEN:
            case LPAREN_TOKEN:
                return parseExpressionStatement();
            case IF_TOKEN:
                return parseSelectionStatement();
            case WHILE_TOKEN:
                return parseIterationStatement();
            case RETURN_TOKEN:
                return parseReturnStatement();
            default:
                throw new CMinusException("parseStatement Exception");                
        }
    }
    
    private ExpressionStatement parseExpressionStatement() throws CMinusException{
        
        Expression e = null;
        
        switch (myScanner.viewNextToken().getType()) {
            case ID_TOKEN:
            case NUM_TOKEN:
            case LCURLEY_TOKEN:
                e = parseExpression();
            default: 
                // no expression here
        }

        matchToken(TokenType.SEMI_TOKEN);

        return new ExpressionStatement(e);
    }

    private SelectionStatement parseSelectionStatement() throws CMinusException{
        matchToken(TokenType.IF_TOKEN);
        matchToken(TokenType.LPAREN_TOKEN);

        Expression e = parseExpression();

        matchToken(TokenType.RPAREN_TOKEN);

        Statement s1 = parseStatement();

        if(myScanner.viewNextToken().getType() == TokenType.ELSE_TOKEN){
            matchToken(TokenType.ELSE_TOKEN);

            Statement s2 = parseStatement();

            return new SelectionStatement(e, s1, s2);
        }
        return new SelectionStatement(e, s1, null);
    }

    private IterationStatement parseIterationStatement() throws CMinusException{
        matchToken(TokenType.WHILE_TOKEN);
        matchToken(TokenType.LPAREN_TOKEN);
        Expression e = parseExpression();
        matchToken(TokenType.RPAREN_TOKEN);

        Statement s = parseStatement();

        return new IterationStatement(e, s);
    }

    private ReturnStatement parseReturnStatement() throws CMinusException{
        matchToken(TokenType.RETURN_TOKEN);

        Expression e = null;
        
        switch (myScanner.viewNextToken().getType()) {
            case ID_TOKEN:
            case NUM_TOKEN:
            case LCURLEY_TOKEN:
                e = parseExpression();
            default:
                // no expression here
            }

        matchToken(TokenType.SEMI_TOKEN);

        return new ReturnStatement(e);
    }

    private Expression parseExpression() throws CMinusException{

        Expression e;
        
        switch (myScanner.viewNextToken().getType()) {
            case ID_TOKEN:
                String id = (String) matchToken(TokenType.ID_TOKEN).getData();
                return parseExpressionPrime(id);
            case NUM_TOKEN:
                int num = Integer.parseInt((String) matchToken(TokenType.NUM_TOKEN).getData());
                e = new NumExpression(num);
                return parseSimpleExpressionPrime(e);
            case LPAREN_TOKEN:
                matchToken(TokenType.LPAREN_TOKEN);
                e = parseExpression();
                matchToken(TokenType.RPAREN_TOKEN);
                return parseSimpleExpressionPrime(e);
            default:
                throw new CMinusException("parseExpression Exception");
        }
        
    }

    private Expression parseExpressionPrime(String ID) throws CMinusException{
        Expression e2;
        switch (myScanner.viewNextToken().getType()) {
            case ASSIGN_TOKEN:
                matchToken(TokenType.ASSIGN_TOKEN);
                e2 = parseExpression();
                return new AssignExpression(new VarExpression(ID, null), e2);
            case LBRACKET_TOKEN:
                matchToken(TokenType.LBRACKET_TOKEN);
                e2 = parseExpression();
                matchToken(TokenType.RBRACKET_TOKEN);
                return parseExpression2Prime(new VarExpression(ID, e2));
            case LPAREN_TOKEN:
                matchToken(TokenType.LPAREN_TOKEN);
                ArrayList<Expression> args = parseArgs();
                matchToken(TokenType.RPAREN_TOKEN);
                return parseSimpleExpressionPrime(new CallExpression(ID, args));
            case RPAREN_TOKEN:
            case RBRACKET_TOKEN:
            case SEMI_TOKEN:
            case TIMES_TOKEN:
            case DIVIDE_TOKEN:
            case COMMA_TOKEN:
            case PLUS_TOKEN:
            case MINUS_TOKEN:
            case LT_TOKEN:
            case LTE_TOKEN:
            case GT_TOKEN:
            case GTE_TOKEN:
            case EQ_TOKEN:
            case NOT_EQ_TOKEN:
                return parseSimpleExpressionPrime(new VarExpression(ID, null));            
            default:
                throw new CMinusException("parseExpressionPrime Exception");
        }
        
    }

    private Expression parseExpression2Prime(VarExpression E) throws CMinusException{
        Expression e2;
        switch(myScanner.viewNextToken().getType()){
            case ASSIGN_TOKEN:
                matchToken(TokenType.ASSIGN_TOKEN);
                e2 = parseExpression();
                return new AssignExpression(E, e2);
            case RPAREN_TOKEN:
            case RBRACKET_TOKEN:
            case SEMI_TOKEN:
            case TIMES_TOKEN:
            case DIVIDE_TOKEN:
            case COMMA_TOKEN:
            case PLUS_TOKEN:
            case MINUS_TOKEN:
            case LT_TOKEN:
            case LTE_TOKEN:
            case GT_TOKEN:
            case GTE_TOKEN:
            case EQ_TOKEN:
            case NOT_EQ_TOKEN:
                return parseSimpleExpressionPrime(E);
            default: 
                throw new CMinusException("parseExpression2Prime Exception");
        }
        
    }
    

    private Expression parseSimpleExpressionPrime(Expression E) throws CMinusException{
        
        Expression lhs = parseAdditiveExpressionPrime(E);
        
        switch (myScanner.viewNextToken().getType()) {
            case GT_TOKEN:
            case GTE_TOKEN:
            case LT_TOKEN:
            case LTE_TOKEN:
            case NOT_EQ_TOKEN:
            case EQ_TOKEN:
                String relop = getString(parseRelop());
                Expression e2 = parseAdditiveExpression();
                lhs = new BinaryExpression(lhs, e2, relop);
            default:
                // do nothing
        }

        return lhs;
    }


    
    private Expression parseAdditiveExpression() throws CMinusException{
        Expression LHS = parseTerm();
        
        TokenType currTokenType = myScanner.viewNextToken().getType();
        while(currTokenType == TokenType.PLUS_TOKEN || currTokenType == TokenType.MINUS_TOKEN){
            myScanner.getNextToken();
            Expression RHS = parseTerm();
            LHS = new BinaryExpression(LHS, RHS, getString(currTokenType));
            currTokenType = myScanner.viewNextToken().getType();
        }
        return LHS;
    }

    private Expression parseAdditiveExpressionPrime(Expression E) throws CMinusException{
        Expression LHS = parseTermPrime(E);

        TokenType currTokenType = myScanner.viewNextToken().getType();
        while(currTokenType == TokenType.PLUS_TOKEN || currTokenType == TokenType.MINUS_TOKEN){
            myScanner.getNextToken();
            Expression RHS = parseTerm();
            LHS = new BinaryExpression(LHS, RHS, getString(currTokenType));
            currTokenType = myScanner.viewNextToken().getType();
        }
        return LHS;
    }

    private Expression parseTerm() throws CMinusException{
        Expression LHS = parseFactor();
        
        TokenType currTokenType = myScanner.viewNextToken().getType();
        while(currTokenType == TokenType.TIMES_TOKEN || currTokenType == TokenType.DIVIDE_TOKEN){
            myScanner.getNextToken();
            Expression RHS = parseFactor();
            LHS = new BinaryExpression(LHS, RHS, getString(currTokenType));
            currTokenType = myScanner.viewNextToken().getType();
        }
        return LHS;
    }
    
    private Expression parseTermPrime(Expression LHSparam) throws CMinusException{
        Expression LHS = LHSparam;

        TokenType currTokenType = myScanner.viewNextToken().getType();
        while(currTokenType == TokenType.TIMES_TOKEN || currTokenType == TokenType.DIVIDE_TOKEN){
            myScanner.getNextToken();
            Expression RHS = parseFactor();
            LHS = new BinaryExpression(LHS, RHS, getString(currTokenType));
            currTokenType = myScanner.viewNextToken().getType();
        }
        return LHS;
    }

    private Expression parseFactor() throws CMinusException{
        switch (myScanner.viewNextToken().getType()) {
            case LPAREN_TOKEN:
                matchToken(TokenType.LPAREN_TOKEN);
                Expression e = parseExpression();
                matchToken(TokenType.RPAREN_TOKEN);
                return e;
            case ID_TOKEN:
                String tokenID = (String) matchToken(TokenType.ID_TOKEN).getData();
                return parseVarCall(tokenID);
            case NUM_TOKEN: 
                int num = Integer.parseInt((String) matchToken(TokenType.NUM_TOKEN).getData());
                return new NumExpression(num);
            default:
                throw new CMinusException("parseFactor Exception");
        }
    }

    private Expression parseVarCall(String id) throws CMinusException{
        switch(myScanner.viewNextToken().getType()){
            case LBRACKET_TOKEN:
                return new VarExpression(id, parseExpression());
            case LPAREN_TOKEN:
                matchToken(TokenType.LPAREN_TOKEN);
                ArrayList<Expression> args = parseArgs();
                matchToken(TokenType.RPAREN_TOKEN);
                return new CallExpression(id, args);
            case RPAREN_TOKEN:
            case RBRACKET_TOKEN:
            case SEMI_TOKEN:
            case ASSIGN_TOKEN:
            case TIMES_TOKEN:
            case DIVIDE_TOKEN:
            case COMMA_TOKEN:
            case PLUS_TOKEN:
            case MINUS_TOKEN:
                return new VarExpression(id, null);
            default: 
                throw new CMinusException("parseVarCall Exception");
        }
         
    }

    private TokenType parseRelop() throws CMinusException{
        switch (myScanner.viewNextToken().getType()) {
            case GTE_TOKEN:
                return matchToken(TokenType.GTE_TOKEN).getType();
            case GT_TOKEN:
                return matchToken(TokenType.GT_TOKEN).getType();
            case LT_TOKEN:
                return matchToken(TokenType.LT_TOKEN).getType();
            case LTE_TOKEN:
                return matchToken(TokenType.LTE_TOKEN).getType();
            case NOT_EQ_TOKEN:
                return matchToken(TokenType.NOT_EQ_TOKEN).getType();
            case EQ_TOKEN:
                return matchToken(TokenType.EQ_TOKEN).getType();
            default:
                System.err.println("parseRelop Exception");
                return null;
        }
    }    

    private ArrayList<Expression> parseArgs() throws CMinusException{
        ArrayList<Expression> args = new ArrayList<Expression>();
        TokenType currTok = myScanner.viewNextToken().getType();
        if(currTok == TokenType.ID_TOKEN || currTok == TokenType.NUM_TOKEN || currTok == TokenType.LPAREN_TOKEN){
            args.add(parseExpression());
        }
        while(myScanner.viewNextToken().getType() == TokenType.COMMA_TOKEN){
            matchToken(TokenType.COMMA_TOKEN);
            args.add(parseExpression());
        }

        return args;
    }

    
    public static void main(String[] args) {
        String filename = "ben";
        Parser myParser = new CMinusParser("./projectFiles/proj2/" + filename + ".cm");

        try{
            myParser.parse();
            myParser.printTree();
        }
        catch(CMinusException e){
            System.err.println(e);
        }
        
    }
    
}