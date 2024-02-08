package compiler.scanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import compiler.scanner.Token.TokenType;

interface Scanner {
    public Token getNextToken();
    public Token viewNextToken();
}

public class CMinusScanner implements Scanner {

    enum ScanState {
        START,
        INNUM,
        INID,
        INNOTEQUAL,
        INEQUAL,
        INGTE,
        INLTE,
        INCOMM1,
        INCOMM2,
        INCOMM3,
        DONE
    }

    private BufferedReader inFile;
    private Token nextToken;

    public CMinusScanner(String filename) {
        try {
            inFile = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
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

    public Token viewNextToken() {
        return nextToken;
    }

    private Token scanToken() {
        Token currentToken = new Token(TokenType.EOF_TOKEN);

        ScanState state = ScanState.START;
        String currentTokenString = "";
        Boolean foundEOF = false;
        while (state != ScanState.DONE) {
            char c = ' ';
            try {
                
                inFile.mark(1);

                if (foundEOF){
                    state = ScanState.DONE;
                }

                //Checking for end of file
                int readVal = inFile.read();
                if(readVal == -1){
                    foundEOF = true;
                }

                c = (char) readVal;

                switch (state) {
                    case START:
                        if (Character.isDigit(c)) {
                            currentTokenString += c;
                            state = ScanState.INNUM;
                        } else if (Character.isLetter(c)) {
                            currentTokenString += c;
                            state = ScanState.INID;
                        } else if (c == '\n' || c == ' ' || c == '\r') {
                            state = ScanState.START;
                        } else if (c == '!') {
                            state = ScanState.INNOTEQUAL;
                        } else if (c == '=') {
                            state = ScanState.INEQUAL;
                        } else if (c == '>') {
                            state = ScanState.INGTE;
                        } else if (c == '<') {
                            state = ScanState.INLTE;
                        } else if (c == '/') {
                            state = ScanState.INCOMM1;
                        } else if (c == '(') {
                            currentToken = new Token(TokenType.LPAREN_TOKEN);
                            state = ScanState.DONE;
                        }else if (c == ')') {
                            currentToken = new Token(TokenType.RPAREN_TOKEN);
                            state = ScanState.DONE;
                        }else if(c == '}'){
                            currentToken = new Token(TokenType.RCURLEY_TOKEN);
                            state = ScanState.DONE;
                        }else if(c == '{'){
                            currentToken = new Token(TokenType.LCURLEY_TOKEN);
                            state = ScanState.DONE;
                        }else if(c == '['){
                            currentToken = new Token(TokenType.LBRACKET_TOKEN);
                            state = ScanState.DONE;
                        }else if(c == ']'){
                            currentToken = new Token(TokenType.RBRACKET_TOKEN);
                            state = ScanState.DONE;
                        }else if(c == '+'){
                            currentToken = new Token(TokenType.PLUS_TOKEN);
                            state = ScanState.DONE;
                        }else if(c == '*'){
                            currentToken = new Token(TokenType.TIMES_TOKEN);
                            state = ScanState.DONE;
                        }else if(c == '-'){
                            currentToken = new Token(TokenType.MINUS_TOKEN);
                            state = ScanState.DONE;
                        }else if(c == ';'){
                            currentToken = new Token(TokenType.SEMI_TOKEN);
                            state = ScanState.DONE;
                        }else if(c == ','){
                            currentToken = new Token(TokenType.COMMA_TOKEN);
                            state = ScanState.DONE;
                        }
                        else {
                            if(foundEOF){
                                currentToken = new Token(TokenType.EOF_TOKEN);
                            } else {
                                currentToken = new Token(TokenType.ERROR_TOKEN);
                            }

                            state = ScanState.DONE;
                        }
                        break;

                    case INNUM:
                        if(Character.isLetter(c)){
                            state = ScanState.DONE;
                            currentToken = new Token(TokenType.ERROR_TOKEN);
                        }
                        else if (!Character.isDigit(c)) {
                            state = ScanState.DONE;
                            inFile.reset();
                            currentToken = new Token(TokenType.NUM_TOKEN, currentTokenString);
                        } else {
                            currentTokenString += c;
                        }
                        break;

                    case INID:
                        if(Character.isDigit(c)){
                            state = ScanState.DONE;
                            currentToken = new Token(TokenType.ERROR_TOKEN);                      
                        }
                        else if (!Character.isLetter(c)) {
                            state = ScanState.DONE;
                            inFile.reset();
                            TokenType retTokenType;
                            switch (currentTokenString) {
                                case ("int"):
                                    retTokenType = TokenType.INT_TOKEN;
                                    break;
                                case ("return"):
                                    retTokenType = TokenType.RETURN_TOKEN;
                                    break;
                                case ("void"):
                                    retTokenType = TokenType.VOID_TOKEN;
                                    break;
                                case ("while"):
                                    retTokenType = TokenType.WHILE_TOKEN;
                                    break;
                                case ("if"):
                                    retTokenType = TokenType.IF_TOKEN;
                                    break;
                                case ("else"):
                                    retTokenType = TokenType.ELSE_TOKEN;
                                    break;
                                default:
                                    retTokenType = TokenType.ID_TOKEN;
                                break;
                            }
                            currentToken = new Token(retTokenType, currentTokenString);
                        } else {
                            currentTokenString += c;
                        }
                        break;

                    case INEQUAL:
                        state = ScanState.DONE;
                        if (c == '=') {
                            currentToken = new Token(TokenType.EQ_TOKEN);
                        } else {
                            currentToken = new Token(TokenType.ASSIGN_TOKEN);
                            inFile.reset();
                        }
                        break;

                    case INNOTEQUAL:
                        state = ScanState.DONE;
                        if (c == '=') {
                            currentToken = new Token(TokenType.NOT_EQ_TOKEN);
                        } else {
                            currentToken = new Token(TokenType.ERROR_TOKEN);
                            inFile.reset();
                        }
                        break;

                    case INLTE:
                        state = ScanState.DONE;
                        if (c == '=') {
                            currentToken = new Token(TokenType.LTE_TOKEN);
                        } else {
                            currentToken = new Token(TokenType.LT_TOKEN);
                            inFile.reset();
                        }
                        break;

                    case INGTE:
                        state = ScanState.DONE;
                        if (c == '=') {
                            currentToken = new Token(TokenType.GTE_TOKEN);
                        } else {
                            currentToken = new Token(TokenType.GT_TOKEN);
                            inFile.reset();
                        }
                        break;
                    

                    case INCOMM1:
                        if (foundEOF){
                            currentToken = new Token(TokenType.ERROR_TOKEN);
                            state = ScanState.DONE;
                        } else {
                            if(c == '*'){
                                state = ScanState.INCOMM2;
                            }
                            else{
                                inFile.reset();
                            }
                        }
                        break;
                        
                    case INCOMM2:
                        if (foundEOF){
                            currentToken = new Token(TokenType.ERROR_TOKEN);
                            state = ScanState.DONE;
                        } else {
                            if(c == '*'){
                                state = ScanState.INCOMM3;
                            }
                        }
                        break;

                    case INCOMM3:
                        if (foundEOF){
                            currentToken = new Token(TokenType.ERROR_TOKEN);
                            state = ScanState.DONE;
                        } else {
                            if (c == '/'){
                                state = ScanState.START;
                            } else {
                                state = ScanState.INCOMM2;
                            }
                        }
                        break;

                    case DONE:
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Couldn't read char from buffer");
            }
        }

        return currentToken;
    };

    // Main for Proj 1
    public static void main(String[] args) {
        CMinusScanner scanner = new CMinusScanner("./projectFiles/proj1/Test2.cm");
        BufferedWriter writer;

        try {
            writer = new BufferedWriter(new FileWriter("./projectFiles/proj1/Test2Result.txt"));
            while(scanner.viewNextToken().getType() != TokenType.EOF_TOKEN){
                Token tkn = scanner.getNextToken();
                String str = ((String)tkn.getData()) +  " " + tkn.getType() + "\n";

                writer.write(str);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't write the file");
        }
    }
}