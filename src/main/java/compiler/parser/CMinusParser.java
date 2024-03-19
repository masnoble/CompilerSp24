package compiler.parser;

import java.util.ArrayList;

import compiler.scanner.CMinusScanner;
import compiler.scanner.Scanner;
import compiler.scanner.Token;
import compiler.scanner.Token.TokenType;

public class CMinusParser implements Parser {
    
    private Scanner myScanner;

    CMinusParser(String filename){
        myScanner = new CMinusScanner(filename);
    }

    public Program parse() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parse'");
    }
    
    public void printTree() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'printTree'");
    }
    
    private Token matchToken(TokenType tokenType){

        Token tkn = myScanner.getNextToken();
        if (tkn.getType() != tokenType){
            System.err.println("bad parser");
        }
        return tkn;
    }


    private Program parseProgram(){
        Program program = new Program();

        program.declarations.add(parseDeclaration());

        TokenType currTokenType = myScanner.viewNextToken().getType();
        while(currTokenType == TokenType.VOID_TOKEN || currTokenType == TokenType.INT_TOKEN){
            program.declarations.add(parseDeclaration());
            currTokenType = myScanner.viewNextToken().getType();
        }

        return program;
    }
    
    
    private Declaration parseDeclaration(){
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
            return null;
        }
    }


    private Declaration parseDeclarationPrime(boolean IV, String ID){    
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
                System.err.println("Declaration Prime Error");
                return null;
        }        
    }

    private Declaration parseFunDeclarationPrime(boolean IV, String ID){
        matchToken(TokenType.LPAREN_TOKEN);
        ArrayList<Param> params = parseParams();
        matchToken(TokenType.RPAREN_TOKEN);
        CompoundStatement compoundStatement = parseCompoundStatement();

        return new FunctionDeclaration(IV, ID, params, compoundStatement);
    }


    private ArrayList<Param> parseParams(){
        switch (myScanner.viewNextToken().getType()) {
            case VOID_TOKEN:
                matchToken(TokenType.VOID_TOKEN);
                return new ArrayList<Param>();
                
            case INT_TOKEN:
                return parseParamList();
        
            default:
                System.err.println("Parse Param Error");
                return null;
        }
    }

    private ArrayList<Param> parseParamList(){
        ArrayList<Param> params = new ArrayList<Param>();

        params.add(parseParam());

        while(myScanner.viewNextToken().getType() == TokenType.COMMA_TOKEN){
            matchToken(TokenType.COMMA_TOKEN);
            params.add(parseParam()); 
        }

        return params;
    }

    
    private Param parseParam(){
        matchToken(TokenType.INT_TOKEN);
        String id = (String) matchToken(TokenType.ID_TOKEN).getData();

        if(myScanner.viewNextToken().getType() == TokenType.LBRACKET_TOKEN){
            matchToken(TokenType.LBRACKET_TOKEN);
            matchToken(TokenType.RBRACKET_TOKEN);
        }

        return new Param(id);
    }

    private CompoundStatement parseCompoundStatement(){
        matchToken(TokenType.LCURLEY_TOKEN);
        LocalDeclarations localDecl = parseLocalDeclarations();
        ArrayList<Statement> stmtList  = parseStatementList();
        matchToken(TokenType.RCURLEY_TOKEN);

        return new CompoundStatement(localDecl, stmtList);
    }

    private LocalDeclarations parseLocalDeclarations(){
        LocalDeclarations localDecl = parseLocalDeclarations();


    }
    
    private ArrayList<Statement> parseStatementList(){
       
        

    }

    private Statement parseStatement(){
        switch (myScanner.viewNextToken().getType()) {
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
                System.err.println("Statement Error");                
                break;
        }
    }
    
    private ExpressionStatement parseExpressionStatement(){
        
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

    private SelectionStatement parseSelectionStatement(){
        matchToken(TokenType.IF_TOKEN);
        matchToken(TokenType.LPAREN_TOKEN);

        Expression e = parseExpression();

        Statement s1 = parseStatement();

        if(myScanner.viewNextToken().getType() == TokenType.ELSE_TOKEN){
            matchToken(TokenType.ELSE_TOKEN);

            Statement s2 = parseStatement();

            return new SelectionStatement(s1, s2);
        }


        return new SelectionStatement(s1, null);
        
    }

    private IterationStatement parseIterationStatement(){
        matchToken(TokenType.WHILE_TOKEN);
        matchToken(TokenType.LPAREN_TOKEN);
        Expression e = parseExpression();
        matchToken(TokenType.RPAREN_TOKEN);

        Statement s = parseStatement();

        return new IterationStatement(e, s);
    }

    private ReturnStatement parseReturnStatement(){
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

    private Expression parseExpression(){
        switch (myScanner.viewNextToken().getType()) {
            case ID_TOKEN:
                String id = (String) matchToken(TokenType.ID_TOKEN).getData();
                return parseExpressionPrime(id);
            case NUM_TOKEN:
                int num = Integer.parseInt((String) matchToken(TokenType.NUM_TOKEN).getData());
                return parseSimpleExpressionPrime(num);
            case LPAREN_TOKEN:

            
        
            default:
                break;
        }

        
    }

    private Expression parseExpressionPrime(String ID){

        
    }

    private Expression parseExpression2Prime(){

        
    }
    

    private Expression parseSimpleExpressionPrime(){
        
    }

    private Expression parseAdditiveExpression(){
        Expression LHS = parseTerm();
        
        TokenType currTokenType = myScanner.viewNextToken().getType();
        while(currTokenType == TokenType.PLUS_TOKEN || currTokenType == TokenType.MINUS_TOKEN){
            myScanner.getNextToken();
            Expression RHS = parseTerm();
            LHS = new BinaryExpression(LHS, RHS, currTokenType);
            currTokenType = myScanner.viewNextToken().getType();
        }
        return LHS;
    }

    private Expression parseAdditiveExpressionPrime(){
        Expression termPrime = parseTermPrime();
        TokenType addOp = parseAddop();
        Expression term = parseTerm(addOp);

    }

    private Expression parseTerm(){
        
    }
    
    private Expression parseTermPrime(){
        
    }

    private Expression parseFactor(){
        switch (myScanner.viewNextToken().getType()) {
            case LPAREN_TOKEN:
                matchToken(TokenType.LPAREN_TOKEN);
                return parseExpression();
            case ID_TOKEN:
                String tokenID = (String) matchToken(TokenType.ID_TOKEN).getData();
                return parseVarCall(tokenID);
            case NUM_TOKEN: 
                int tokenNum = Integer.parseInt((String) matchToken(TokenType.NUM_TOKEN).getData());
                break;
            default:
                break;
        }
    }

    private Expression parseVarCall(String id){
        
    }

    private TokenType parseRelop(){
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
                System.err.println("Error parse addop");
                return null;
        }
    }    

    private TokenType parseAddop(){
        switch (myScanner.viewNextToken().getType()) {
            case PLUS_TOKEN:
                return matchToken(TokenType.PLUS_TOKEN).getType();
            case MINUS_TOKEN:
                return matchToken(TokenType.MINUS_TOKEN).getType();
            default:
                System.err.println("Error parse addop");
                return null;
        }
    }

    private TokenType parseMulop(){
        switch (myScanner.viewNextToken().getType()) {
            case TIMES_TOKEN:
                return matchToken(TokenType.TIMES_TOKEN).getType();
            case DIVIDE_TOKEN:
                return matchToken(TokenType.DIVIDE_TOKEN).getType();
            default:
                System.err.println("Error parse mulop");
                return null;
        }
    }
    private Expression parseArgs(){

    }



    public static void main(String[] args) {
        
    }
    
}