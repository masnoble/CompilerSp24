package main.java.compiler.scanner;

import java.io.BufferedReader;
import java.io.FileReader;

import scanner.Token;
import scanner.Token.TokenType;

public interface Scanner {
    public Token getNextToken();
    public Token viewNextToken();
}


public class CMinusScanner {
    
    private BufferedReader inFile;
    private Token nextToken;

    public CMinusScanner(String filename){
        inFile = new BufferedReader(new FileReader(filename));
        nextToken = scanToken();
    }


    public Token getNextToken(){
        Token returnToken = nextToken;
        if(nextToken.getType() != TokenType.EOF_TOKEN){
            nextToken = scanToken();
        }
        return returnToken;
    }


    // Main for Proj 1
    public static void main(String[] args) {

        // Prints "Hello, World" in the terminal window.
        System.out.println("Hello, World- SCANNER!");
    }
}
