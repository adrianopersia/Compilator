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
            throw new RuntimeException("Error de sintaxis en la línea " + currentToken.getLineNumber() +
                    ". Se esperaba " + type + " pero se encontró " + currentToken.getType());
        }
    }

    public ASTNode parse() throws IOException {
        return program();
    }

    private ASTNode program() throws IOException {
        ProgramNode programNode = new ProgramNode();
        while (currentToken.getType() != TokenType.EOF) {
            programNode.addStatement(statement());
        }
        return programNode;
    }

    private ASTNode statement() throws IOException {
        switch (currentToken.getType()) {
            case LONG:
            case DOUBLE:
                return variableDeclaration();
            case IDENTIFIER:
                return assignment();
            case IF:
                return ifStatement();
            case WHILE:
                return whileStatement();
            case READ:
                return readStatement();
            case WRITE:
                return writeStatement();
            case LEFT_BRACE:
                return block();
            default:
                throw new RuntimeException("Error de sintaxis en la línea " + currentToken.getLineNumber() +
                        ". Declaración inesperada: " + currentToken.getLexeme());
        }
    }

    private ASTNode variableDeclaration() throws IOException {
        Token typeToken = currentToken;
        eat(currentToken.getType()); // Consume LONG o DOUBLE
        Token identifierToken = currentToken;
        eat(TokenType.IDENTIFIER);

        ASTNode expression = null;
        if (currentToken.getType() == TokenType.ASSIGN) {
            eat(TokenType.ASSIGN);
            expression = expression();
        }

        eat(TokenType.SEMICOLON);
        return new VariableDeclarationNode(typeToken, identifierToken, expression);
    }

    private ASTNode assignment() throws IOException {
        Token identifierToken = currentToken;
        eat(TokenType.IDENTIFIER);
        eat(TokenType.ASSIGN);
        ASTNode expr = expression();
        eat(TokenType.SEMICOLON);
        return new AssignmentNode(identifierToken, expr);
    }

    private ASTNode ifStatement() throws IOException {
        eat(TokenType.IF);
        eat(TokenType.LEFT_PAREN);
        ASTNode condition = condition();
        eat(TokenType.RIGHT_PAREN);
        eat(TokenType.THEN);
        ASTNode thenBlock = block();
        ASTNode elseBlock = null;
        if (currentToken.getType() == TokenType.ELSE) {
            eat(TokenType.ELSE);
            elseBlock = block();
        }
        return new IfNode(condition, thenBlock, elseBlock);
    }

    private ASTNode whileStatement() throws IOException {
        eat(TokenType.WHILE);
        eat(TokenType.LEFT_PAREN);
        ASTNode condition = condition();
        eat(TokenType.RIGHT_PAREN);
        ASTNode body = block();
        return new WhileNode(condition, body);
    }

    private ASTNode readStatement() throws IOException {
        eat(TokenType.READ);
        Token identifierToken = currentToken;
        eat(TokenType.IDENTIFIER);
        eat(TokenType.SEMICOLON);
        return new ReadNode(identifierToken);
    }

    private ASTNode writeStatement() throws IOException {
        eat(TokenType.WRITE);
        ASTNode expr = expression();
        eat(TokenType.SEMICOLON);
        return new WriteNode(expr);
    }

    private ASTNode block() throws IOException {
        eat(TokenType.LEFT_BRACE);
        BlockNode blockNode = new BlockNode();
        while (currentToken.getType() != TokenType.RIGHT_BRACE) {
            blockNode.addStatement(statement());
        }
        eat(TokenType.RIGHT_BRACE);
        return blockNode;
    }

    private ASTNode expression() throws IOException {
        ASTNode node = term();
        while (currentToken.getType() == TokenType.PLUS || currentToken.getType() == TokenType.MINUS) {
            Token op = currentToken;
            if (currentToken.getType() == TokenType.PLUS) {
                eat(TokenType.PLUS);
            } else {
                eat(TokenType.MINUS);
            }
            node = new BinaryExpression(node, op, term());
        }
        return node;
    }

    private ASTNode term() throws IOException {
        ASTNode node = factor();
        while (currentToken.getType() == TokenType.MULTIPLY || currentToken.getType() == TokenType.DIVIDE) {
            Token op = currentToken;
            if (currentToken.getType() == TokenType.MULTIPLY) {
                eat(TokenType.MULTIPLY);
            } else {
                eat(TokenType.DIVIDE);
            }
            node = new BinaryExpression(node, op, factor());
        }
        return node;
    }

    private ASTNode factor() throws IOException {
        Token token = currentToken;
        if (token.getType() == TokenType.NUMBER) {
            eat(TokenType.NUMBER);
            return new NumberNode(token);
        } else if (token.getType() == TokenType.IDENTIFIER) {
            eat(TokenType.IDENTIFIER);
            return new VariableNode(token);
        } else if (token.getType() == TokenType.LEFT_PAREN) {
            eat(TokenType.LEFT_PAREN);
            ASTNode node = expression();
            eat(TokenType.RIGHT_PAREN);
            return node;
        } else {
            throw new RuntimeException("Error de sintaxis en la línea " + currentToken.getLineNumber() +
                    ". Factor inesperado: " + token.getLexeme());
        }
    }

    private ASTNode condition() throws IOException {
        ASTNode left = expression();
        Token op = currentToken;
        switch (currentToken.getType()) {
            case EQUAL:
                eat(TokenType.EQUAL);
                break;
            case NOT_EQUAL:
                eat(TokenType.NOT_EQUAL);
                break;
            case LESS_THAN:
                eat(TokenType.LESS_THAN);
                break;
            case LESS_EQUAL:
                eat(TokenType.LESS_EQUAL);
                break;
            case GREATER_THAN:
                eat(TokenType.GREATER_THAN);
                break;
            case GREATER_EQUAL:
                eat(TokenType.GREATER_EQUAL);
                break;
            default:
                throw new RuntimeException("Error de sintaxis en la línea " + currentToken.getLineNumber() +
                        ". Operador de condición esperado, pero se encontró: " + currentToken.getLexeme());
        }
        ASTNode right = expression();
        return new ConditionNode(left, op, right);
    }
}


