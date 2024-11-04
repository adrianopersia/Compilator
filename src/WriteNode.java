public class WriteNode extends ASTNode {
    private ASTNode expression;

    public WriteNode(ASTNode expression) {
        this.expression = expression;
    }

    public ASTNode getExpression() {
        return expression;
    }
}