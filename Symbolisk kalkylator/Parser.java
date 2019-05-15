import java.util.*;


//       class Parser
public class Parser {
  
  // private SimplifiedTokenizer tokenizer;
  private Stokenizer tokenizer;
  private Set<String> unaryFunctions;
  
  public Parser(Stokenizer tokenizer) {
    this.tokenizer = tokenizer;
    unaryFunctions = new TreeSet<String>();
    unaryFunctions.add("sin");
    unaryFunctions.add("cos");
    unaryFunctions.add("exp");
    unaryFunctions.add("log");
  }
  

  
  /**
   * anterar tilldelningar
   *     uttryck [= variabel [= variabel ...]]
   */
  public Sexpr assignment() {
    Sexpr ass = expression();
    while (tokenizer.getChar()=='=') {
      tokenizer.nextToken();
      if (tokenizer.isWord()) {
        ass = new Assignment(ass, new Variable(tokenizer.getToken()));
        tokenizer.nextToken();   
      } else {
        throw new SyntaxException("Förväntade identifierare efter '='");
      }
    }
    return ass;
  }
  
  /**
   * Hanterar en summa av termer: term +|- term +|- term +|- ...
   */ 
  public Sexpr expression() {
    Sexpr sum = term();
    int c;
    while ((c=tokenizer.getChar())=='+' ||
           c == '-') {
      tokenizer.nextToken();
      if (c=='+') {
        sum = new Addition(sum, term());
      } else {
        sum = new Subtraction(sum,term());
      }
    }
    return sum;
  }
  
  /**
   * Hanterar en produkt av faktorer: faktor *|/ faktor *|/ faktor *|/ ...
   */ 
  public Sexpr term() {
    int c;
    Sexpr prod = factor();
    while ((c=tokenizer.getChar())=='*'  ||
           c=='/' || c=='^') {
      tokenizer.nextToken();
      if (c=='*')
        prod = new Multiplication(prod, factor());
      else
        prod = new Division(prod, factor()); 
    }
    return prod;
  }
  

  /**
   * Hanterar deriveringsuttryck:
   *     uttryck['variabel['variabel ...]]
   */
  public Sexpr factor() {
    Sexpr prim = primary();
    while (tokenizer.getChar()=='\'') {
      tokenizer.nextToken();
      if (tokenizer.isWord()){
        prim = new Differentiation(prim, new Variable(tokenizer.getToken()));
        tokenizer.nextToken();   
      } else {
        throw new SyntaxException("Förväntade identifierare efter ' men fann " + tokenizer.getToken());
      }
    }
    return prim;
  }
  
  /**
   * Hanterar en "primaries" dvs konstanter, variabler, funktionsanrop,
   * unära operatorer (-, & ") eller parentetiserade uttryck.
   */ 
  public Sexpr primary() {
    Sexpr result = null;
    if (tokenizer.getChar()=='(') {
      tokenizer.nextToken();
      result = assignment();
      if (tokenizer.getChar()==')') {
        tokenizer.nextToken();
      } else {
        throw new SyntaxException("Saknar ')'");
      }
    } else if (tokenizer.isNumber()) {
      result = new Constant(tokenizer.getNumber());
      tokenizer.nextToken();
    } else if (tokenizer.getChar()=='-') {
      tokenizer.nextToken();
      result = new Negation(primary());
    } else if (tokenizer.getChar()=='"') {
      tokenizer.nextToken();
      result = new Quotation(primary());
    } else if (tokenizer.getChar()=='&') {
      tokenizer.nextToken();
      result = new Evaluation(primary());
    } else if (unaryFunctions.contains(tokenizer.getToken())) {
      result = unaryFunction();
    } else if (tokenizer.isWord()) {
      result = new Variable(tokenizer.getWord());
      tokenizer.nextToken();
    } else {
      throw new SyntaxException("Förväntade identifierare, tal, unär operator, eller '('");
    }
    return result;
  }
  
  
  public Sexpr unaryFunction() {
    String foo = tokenizer.getToken();
    Sexpr arg;
    tokenizer.nextToken();
    if (tokenizer.getChar()=='(') {
      arg = primary();
    } else {
      throw new SyntaxException("'(' förväntades efter '" + foo +"'");
    }
    if (foo.equals("exp"))
      return new Exp(arg);
    else if (foo.equals("log"))
      return new Log(arg);
    else if (foo.equals("sin")){
     return new Sin(arg);
    }
    else if (foo.equals("cos"))
      return new Cos(arg);
    else
      throw new RuntimeException("Internt fel! Odefinierad funktion " + foo);
 // return arg; // tas bort när det ovanför inte är kommenterat
  }
  
}