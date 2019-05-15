
import java.util.*;

public class Calculator {
  
  private Parser parser;
  private Stokenizer tokenizer;
  private Map<String, Sexpr> variables;
  
  public Calculator() {
    tokenizer = new Stokenizer();
    variables = new TreeMap<String, Sexpr>();
    parser    = new Parser(tokenizer);
  }
  
  public static void main(String  [] args) {
    System.out.println("V�kommen till kalkylatorn version 2.0");
    Calculator calc = new Calculator();
    while (true) { 
      try {
        calc.statement();
      }
      catch(SyntaxException se) {
        System.out.println("" + se.getMessage()); 
//        while(!(tokenizer.isEOL()) ){
//          tokenizer.nextToken();
//        }
      }
      catch(EvaluationException ee) {
        System.out.println("" + ee.getMessage());
      }
    }
  }
  
  /**
   * Uppt�cker och hanterar kommandon.
   * Precondition:  Aktuell tok �r token F�RE f�rsta tok i statement
   * Postcondition: Aktuell tok �r den tok som terminerade statement
   *                (vanligen EOL)
   */
  public void statement() {
    do {
      System.out.print("> ");
      tokenizer.nextToken();
    } while (tokenizer.isEOL());
      
    if(tokenizer.getToken().equals("quit")) {
      System.exit(0);
    } else if (tokenizer.getToken().equals("vars")) {
      System.out.println(variables);
      tokenizer.nextToken();
    } else {
      Sexpr parsed = parser.assignment();
      Sexpr evaluated = parsed.eval(variables);
      System.out.println("L�st uttryck: " + parsed.toString());
      System.out.println("Evaluerat   : " + evaluated);
      variables.put("ans", evaluated);
    }
    if ( !(tokenizer.isEOL()) ){
      throw new SyntaxException("F�rv�ntade EOL");
    }
     
  }  
  
}