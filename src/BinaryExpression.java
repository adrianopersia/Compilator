public class BinaryExpression extends ASTNode {
    private ASTNode left;
    private Token operator;
    private ASTNode right;

    // Constructor
    public BinaryExpression(ASTNode left, Token operator, ASTNode right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    // Getters y setters
    public ASTNode getLeft() {
        return left;
    }

    public void setLeft(ASTNode left) {
        this.left = left;
    }

    public Token getOperator() {
        return operator;
    }

    public void setOperator(Token operator) {
        this.operator = operator;
    }

    public ASTNode getRight() {
        return right;
    }

    public void setRight(ASTNode right) {
        this.right = right;
    }
}

