public class VariableNode extends ASTNode {
    private Token token;

    public VariableNode(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }
}