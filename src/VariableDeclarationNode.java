public class VariableDeclarationNode extends ASTNode {
    private Token typeToken;
    private Token identifier;
    private ASTNode expression;

    public VariableDeclarationNode(Token typeToken, Token identifier, ASTNode expression) {
        this.typeToken = typeToken;
        this.identifier = identifier;
        this.expression = expression;
    }

    public Token getTypeToken() {
        return typeToken;
    }

    public Token getIdentifier() {
        return identifier;
    }

    public ASTNode getExpression() {
        return expression;
    }
}

