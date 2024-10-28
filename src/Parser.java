import java.io.IOException;


public class Parser {
    private final Lexer lexer;
    private Token currentToken;

    public Parser(Lexer lexer) throws IOException {
        this.lexer = lexer;
        currentToken = lexer.getNextToken();
    }

    private void eat(TokenType type) throws IOException {
        if (currentToken.getType() == type) {
            currentToken = lexer.getNextToken();
        } else {
            throw new RuntimeException("Error de sintaxis en la línea " + currentToken.getLineNumber());
        }
    }

    public ASTNode parse() throws IOException {
        // Implementa la gramática aquí
        return program();
    }

    private ASTNode program() throws IOException {
        // Implementa la lógica para analizar el programa completo
        // ...
        return null;
    }

    // Agrega métodos para cada regla gramatical
}

