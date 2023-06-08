import java.io.OutputStream;

public class Runner {
    public static void main(String[] args) {
        try {
            System.out.println(LexicalAnalyser.analyse("7230 + 2"));
            System.out.println(LexicalAnalyser.analyse("     4"));
            System.out.println(LexicalAnalyser.analyse("0"));
            System.out.println(LexicalAnalyser.analyse("12433 / 73"));
            System.out.println(LexicalAnalyser.analyse("120-7401+94-10-7110"));
            System.out.println(LexicalAnalyser.analyse("81040 - 43312"));
            System.out.println(LexicalAnalyser.analyse("0.1373"));
            System.out.println(LexicalAnalyser.analyse("5202"));
            System.out.println(LexicalAnalyser.analyse("0.12 + 1"));
        }
        catch (NumberException e) {

        }
        catch (ExpressionException e) {

        }
    }
}
