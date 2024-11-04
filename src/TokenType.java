public enum TokenType {
    // Palabras reservadas
    LONG, DOUBLE, IF, THEN, ELSE, WHILE, BREAK, READ, WRITE,

    // Operadores aritméticos
    PLUS, MINUS, MULTIPLY, DIVIDE,

    // Operadores relacionales
    GREATER_THAN, LESS_THAN, GREATER_EQUAL, LESS_EQUAL,
    EQUAL, NOT_EQUAL, ASSIGN,

    // Símbolos especiales
    SEMICOLON, COMMA, LEFT_PAREN, RIGHT_PAREN, LEFT_BRACE, RIGHT_BRACE,

    // Identificadores y literales
    IDENTIFIER, NUMBER,

    // Fin de archivo
    EOF,
}

