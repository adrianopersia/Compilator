public class AssignmentNode extends ASTNode {
    private Token identifier;
    private ASTNode expression;

    public AssignmentNode(Token identifier, ASTNode expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    // Getters
}

