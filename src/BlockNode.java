import java.util.ArrayList;
import java.util.List;

public class BlockNode extends ASTNode {
    private List<ASTNode> statements = new ArrayList<>();

    public void addStatement(ASTNode statement) {
        statements.add(statement);
    }

    public List<ASTNode> getStatements() {
        return statements;
    }
}

