import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Lexer {
    private BufferedReader reader;
    private int currentChar;
    private int lineNumber = 1;

    public Lexer(String filePath) throws IOException {
        reader = new BufferedReader(new FileReader(filePath));
        advance();
    }

    private void advance() throws IOException {
        currentChar = reader.read();
    }

    public Token getNextToken() throws IOException {
        while (currentChar != -1) {
            // Manejar espacios en blanco y nuevas líneas
            if (Character.isWhitespace(currentChar)) {
                if (currentChar == '\n') {
                    lineNumber++;
                }
                advance();
                continue;
            }

            // Manejar comentarios
            if (currentChar == '/') {
                advance();
                if (currentChar == '/') {
                    // Comentario de una línea
                    while (currentChar != '\n' && currentChar != -1) {
                        advance();
                    }
                } else if (currentChar == '*') {
                    // Comentario de varias líneas
                    advance();
                    while (true) {
                        if (currentChar == '*' && reader.read() == '/') {
                            advance();
                            break;
                        }
                        advance();
                    }
                } else {
                    return new Token(TokenType.DIVIDE, "/", lineNumber);
                }
                continue;
            }

            // Manejar números
            if (Character.isDigit(currentChar)) {
                StringBuilder number = new StringBuilder();
                while (Character.isDigit(currentChar)) {
                    number.append((char) currentChar);
                    advance();
                }
                return new Token(TokenType.NUMBER, number.toString(), lineNumber);
            }

            // Manejar identificadores y palabras reservadas
            if (Character.isLetter(currentChar)) {
                StringBuilder identifier = new StringBuilder();
                while (Character.isLetterOrDigit(currentChar)) {
                    identifier.append((char) currentChar);
                    advance();
                }
                String lexeme = identifier.toString();
                TokenType type = getKeywordTokenType(lexeme);
                return new Token(type, lexeme, lineNumber);
            }

            // Manejar operadores y símbolos
            // ... (agrega lógica para otros operadores)

            // Carácter no reconocido
            advance();
        }
        return new Token(TokenType.EOF, "", lineNumber);
    }

    private TokenType getKeywordTokenType(String lexeme) {
        switch (lexeme) {
            case "long":
                return TokenType.LONG;
            case "double":
                return TokenType.DOUBLE;
            case "if":
                return TokenType.IF;
            case "then":
                return TokenType.THEN;
            case "else":
                return TokenType.ELSE;
            case "while":
                return TokenType.WHILE;
            case "break":
                return TokenType.BREAK;
            case "read":
                return TokenType.READ;
            case "write":
                return TokenType.WRITE;
            // Agrega otros casos si es necesario
            default:
                return TokenType.IDENTIFIER;
        }
    }
}

