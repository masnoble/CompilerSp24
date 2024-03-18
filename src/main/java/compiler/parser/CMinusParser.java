package compiler.parser;

import compiler.scanner.CMinusScanner;
import compiler.scanner.Scanner;
import compiler.scanner.Token;
import compiler.scanner.Token.TokenType;

public class CMinusParser implements Parser {
    
    Scanner myScanner;

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
            return parseFunDeclaration(IV, id);
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
                int num = (int) matchToken(TokenType.NUM_TOKEN).getData();
                matchToken(TokenType.RBRACKET_TOKEN);
                matchToken(TokenType.SEMI_TOKEN);
                return new Declaration(IV, ID, num);
            case SEMI_TOKEN:
                matchToken(TokenType.SEMI_TOKEN);
                return new Declaration(IV, ID, 0);
            case LPAREN_TOKEN:
                return parseFunDeclaration(IV, ID);
            default:
                // err
                System.err.println("Declaration Prime Error");
                return null;
        }

        
    }

    private Declaration parseFunDeclaration(boolean IV, String ID);
    private Param parseParams();
    private Param parseParamList();
    private Param parseParam();
    private CompoundStmt parseCompoundStatement();
    private Declaration parseLocalDeclaration();
    private Statement parseStatementList();
    private Statement parseStatement();
    private Expression parseExpression();
    private Expression parseExpressionStatement();
    private BinaryExpression parseBinaryExpression();
    private Expression parseFactor();
    private Expression parseArgs();

    private String Addop();
    private String Mulop();
    private String Relop();
    private 


    public static void main(String[] args) {
        
    }
    
}