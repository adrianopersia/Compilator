import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Lexer lexer = new Lexer("D:\\Adriano\\Documents\\Universidad\\Tercer Año\\Compiladores\\test.txt");
            Parser parser = new Parser(lexer);
            ASTNode ast = parser.parse();
            System.out.println("Compilación completada exitosamente.");
        } catch (RuntimeException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        }
    }
}