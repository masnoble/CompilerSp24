package scanner;

public class Token {

    public enum TokenType {
        INT_TOKEN, // "int"
        RETURN_TOKEN,
        VOID_TOKEN,
        WHILE_TOKEN,
        IF_TOKEN, // "if"
        THEN_TOKEN, // "then"
        ELSE_TOKEN, // "else"
        END_TOKEN, // "end"
        REPEAT_TOKEN, // "repeat"
        UNTIL_TOKEN, // "until"
        READ_TOKEN, // "read"
        WRITE_TOKEN, // "write"
        ID_TOKEN, // {identifier}
        NUM_TOKEN, // {number}
        ASSIGN_TOKEN, // "="
        EQ_TOKEN, // "=="
        LT_TOKEN, // "<"
        GT_TOKEN, // ">"
        ERROR_TOKEN, //
        SEMI_TOKEN, // ";"
        RPAREN_TOKEN, // ")"
        LPAREN_TOKEN, // (
        DIVIDE_TOKEN, // "/"
        TIMES_TOKEN, // "*"
        MINUS_TOKEN, // "-"
        PLUS_TOKEN, // "+"
        NOT_TOKEN, // "!"
        LBRACKET_TOKEN, // "["
        RBRACKET_TOKEN, // "]"
        LCURLEY_TOKEN, // "{"
        RCURLEY_TOKEN, // "}"
        LETTER_TOKEN // "letter"
        //LEXICAL CONVENTIONS OF C PG.4 491/92
        // DFA PG 77
    } 
    
    private TokenType tokenType;
    private Object tokenData;

    public Token (TokenType type) {
        this (type, null);
    }

    public Token (TokenType type, Object data) {
        tokenType = type;
        tokenData = data;
    }

    public TokenType getType(){
        return tokenType;
    }

    public Object getData(){
        return tokenData;
    }

    

    // some access methods
}