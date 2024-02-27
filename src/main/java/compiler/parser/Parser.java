package compiler.parser;

import java.util.Scanner;
import java.lang.StringIndexOutOfBoundsException;

public class Parser {
    
    String parseString;
    int readIdx = 0;

    Parser(){
        Scanner inputReader = new Scanner(System.in);
        parseString = inputReader.nextLine();
        inputReader.close();
    }

    char nextChar(){
        // this will handle when we move out of bounds
        try{
            return parseString.charAt(readIdx++);
        }
        catch(StringIndexOutOfBoundsException e){
            // Return a char that means nothing so we exit
            return 'n';
        }
    }

    private Expression parseExpression(){
        Expression lhs = parseTerm();

        char currChar = nextChar();
        while(currChar == '*' || currChar == '+'){
            Expression rhs = parseTerm();
            lhs = new OperatorExpression(currChar, lhs, rhs);

            currChar = nextChar();
        }

        return lhs;
    }

    private Expression parseTerm(){
        char currChar = nextChar();

        if (currChar == '('){
            Expression returnExpression = parseExpression();
            return returnExpression;
        }
        else if (Character.isDigit(currChar)){
            return new NumExpression(Character.digit(currChar, 10));
        }

        // Error here 
        return null;
    }

    public static void main(String[] args) {
        Parser parser = new Parser();

        System.out.println(parser.parseExpression().compute());
    }
    
}
