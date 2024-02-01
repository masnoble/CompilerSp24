package compiler.scanner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import compiler.scanner.Token.TokenType;

interface Scanner {
    public Token getNextToken();

    public Token viewNextToken();
}

public class CMinusScanner implements Scanner {

    private BufferedReader inFile;
    private Token nextToken;

    public CMinusScanner(String filename) {
        try {
            inFile = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }
        nextToken = scanToken();
    }

    public Token getNextToken() {
        Token returnToken = nextToken;
        if (nextToken.getType() != TokenType.EOF_TOKEN) {
            nextToken = scanToken();
        }
        return returnToken;
    }

    private Token scanToken() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewNextToken'");
    };

    public Token viewNextToken() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewNextToken'");
    }

    // Main for Proj 1
    public static void main(String[] args) {

        // Prints "Hello, World" in the terminal window.
        System.out.println("Hello, World- SCANNER!");
    }

}
