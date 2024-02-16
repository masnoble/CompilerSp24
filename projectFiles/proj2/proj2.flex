/* JFlex example: partial Java language lexer specification */
package compiler.scanner;
import compiler.scanner.Token.TokenType;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;

/**
    * This class is a simple example lexer.
    */
%%

%class JFlexScanner
%implements Scanner
%public 
%unicode
%line
%column
%function scanToken
%type Token


%{


    Token nextToken;
    
    public Token getNextToken() {
        if (nextToken == null){
            try{
              nextToken = scanToken();
          }
          catch (IOException e) {
              e.printStackTrace();
              System.out.println("Couldn't read char from buffer");
          }
        }

        Token returnToken = nextToken;
        if (nextToken.getType() != TokenType.EOF_TOKEN) {
            try{
                nextToken = scanToken();
            }
            catch (IOException e) {
                e.printStackTrace();
                System.out.println("Couldn't read char from buffer");
            }
        }

        if (nextToken == null){
            nextToken = new Token(TokenType.EOF_TOKEN);
        }

        return returnToken;
    }

    public Token viewNextToken() {
      if (nextToken == null){
        try{
            nextToken = scanToken();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't read char from buffer");
        }
      }

      return nextToken;
    }


    public static void main(String[] args) {
        String fileName = "Test1";

        FileReader reader;

        try{
            reader = new FileReader("./projectFiles/proj2/" + fileName + ".cm");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        JFlexScanner scanner = new JFlexScanner(reader);

        BufferedWriter writer;

        try {
            writer = new BufferedWriter(new FileWriter("./projectFiles/proj2/" + fileName + "Result.txt"));
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
%}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]
Digit          = [0-9]
Num            = {Digit}+
Letter         = [A-Za-z]
ID             = {Letter}+


TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
// Comment can be the last line of the file, without line terminator.
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}
%%

/* keywords */
<YYINITIAL> "int"               { return new Token(TokenType.INT_TOKEN, yytext()); }
<YYINITIAL> "return"            { return new Token(TokenType.RETURN_TOKEN, yytext()); }
<YYINITIAL> "void"              { return new Token(TokenType.VOID_TOKEN, yytext()); }
<YYINITIAL> "while"             { return new Token(TokenType.WHILE_TOKEN, yytext()); }
<YYINITIAL> "if"                { return new Token(TokenType.IF_TOKEN, yytext()); }
<YYINITIAL> "else"              { return new Token(TokenType.ELSE_TOKEN, yytext()); }

<YYINITIAL> {
    /* identifiers */ 
    {ID}                   { return new Token(TokenType.ID_TOKEN, yytext()); }
    {Num}                   { return new Token(TokenType.NUM_TOKEN, yytext()); }


    /* operators */
    "="                            { return new Token(TokenType.ASSIGN_TOKEN); }
    "!="                           { return new Token(TokenType.NOT_EQ_TOKEN); }
    "=="                           { return new Token(TokenType.EQ_TOKEN); }
    "<"                            { return new Token(TokenType.LT_TOKEN); }
    "<="                           { return new Token(TokenType.LTE_TOKEN); }
    ">"                            { return new Token(TokenType.GT_TOKEN); }
    ">="                           { return new Token(TokenType.GTE_TOKEN); }
    ";"                            { return new Token(TokenType.SEMI_TOKEN); }
    ")"                            { return new Token(TokenType.RPAREN_TOKEN); }
    "("                            { return new Token(TokenType.LPAREN_TOKEN); }
    "/"                            { return new Token(TokenType.DIVIDE_TOKEN); }
    "*"                            { return new Token(TokenType.TIMES_TOKEN); }
    "-"                            { return new Token(TokenType.MINUS_TOKEN); }
    "+"                            { return new Token(TokenType.PLUS_TOKEN); }
    ","                            { return new Token(TokenType.COMMA_TOKEN); }
    "["                            { return new Token(TokenType.LBRACKET_TOKEN); }
    "]"                            { return new Token(TokenType.RBRACKET_TOKEN); }
    "{"                            { return new Token(TokenType.LCURLEY_TOKEN); }
    "}"                            { return new Token(TokenType.RCURLEY_TOKEN); }
    /* comments */
    {Comment}                      { /* ignore */ }
    
    /* whitespace */
    {WhiteSpace}                   { /* ignore */ }
}

.                     { return new Token(TokenType.ERROR_TOKEN);}  