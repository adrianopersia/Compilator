public class ConditionNode extends ASTNode {
    private ASTNode left;
    private Token operator;
    private ASTNode right;

    public ConditionNode(ASTNode left, Token operator, ASTNode right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    // Getters
}
