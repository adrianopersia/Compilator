public class Token {
    private TokenType type;
    private String lexeme;
    private int lineNumber;

    public Token(TokenType type, String lexeme, int lineNumber) {
        this.type = type;
        this.lexeme = lexeme;
        this.lineNumber = lineNumber;
    }

    // Getters y setters
    public TokenType getType() {
        return type;
    }

    public String getLexeme() {
        return lexeme;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}


