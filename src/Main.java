public class Main {
    public static void main(String[] args) {
        try {
            Lexer lexer = new Lexer("D:\\Adriano\\Desktop\\prueba.txt");
            Parser parser = new Parser(lexer);
            ASTNode ast = parser.parse();
            System.out.println("Análisis completado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}