import java.util.*;
import java.io.*;
public class Calculator {
  private Parser parser;
  private Stokenizer tokenizer;
  private Map<String, Double> variables;
  

  public Calculator(Stokenizer tokenizer) {
    this.tokenizer = tokenizer;
    variables = new TreeMap<String, Double>();
    variables.put("PI", Math.PI); // Predefined constant
    variables.put("E", Math.E); // Predefined constant
    variables.put("ans", new Double(0.)); // Initial value for ans
    parser = new Parser(tokenizer, variables);
  }

  public static void main(String[] args) throws IOException {
    System.out.println("Numerical calculator version 2013-04-29");
    Calculator calc = new Calculator(new Stokenizer());
    while (true) {
      calc.statement(calc);
    }
  }

  public void statement(Calculator calc) throws IOException {
    try {
      calc.line();
    } catch (SyntaxException syntaxError) {
      System.out.println(syntaxError.getMessage());
      //calc = new Calculator(new Stokenizer());
      while(!tokenizer.isEOL()){
        tokenizer.nextToken();
      }
    
    } catch (EvaluationException evaluationException) {
      System.out.println(evaluationException.getMessage());
      //calc = new Calculator(new Stokenizer());
      while(!tokenizer.isEOL()){
        tokenizer.nextToken();
      }
    }
  }

  private void line() {
  do { // Skip empty lines
    System.out.print("> ");
    tokenizer.nextToken();
  } while (tokenizer.isEOL());
  String command = tokenizer.getToken(); // First token
  if (command.equals("quit") || tokenizer.isEOS()) { // could be the quit command,
    System.out.println("Bye!");
    System.exit(0);
  } else if (command.equals("vars")) { // the vars command or
    System.out.println(variables);
    tokenizer.nextToken();
  } else {
    double parsed = parser.assignment(); // the first element in an expression.
    //System.out.println("fdfdfdf");
    System.out.println(parsed);
    variables.put("ans", new Double(parsed));
  }

  if (!tokenizer.isEOL()) {
    throw new SyntaxException("Expected EOL");
  }
  }
}