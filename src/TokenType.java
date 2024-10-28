public enum TokenType {
    // Palabras reservadas
    LONG, DOUBLE, IF, THEN, ELSE, WHILE, BREAK, READ, WRITE,

    // Operadores
    PLUS, MINUS, MULTIPLY, DIVIDE,
    GREATER_THAN, LESS_THAN, GREATER_EQUAL, LESS_EQUAL,
    EQUAL, NOT_EQUAL,

    // Símbolos especiales
    COMMENT, IDENTIFIER, NUMBER, EOF,
}

