public class IfNode extends ASTNode {
        private ASTNode condition;
        private ASTNode thenBlock;
        private ASTNode elseBlock;

        public IfNode(ASTNode condition, ASTNode thenBlock, ASTNode elseBlock) {
            this.condition = condition;
            this.thenBlock = thenBlock;
            this.elseBlock = elseBlock;
        }

        public ASTNode getCondition() {
            return condition;
        }

        public ASTNode getThenBlock() {
            return thenBlock;
        }

        public ASTNode getElseBlock() {
            return elseBlock;
        }
}

