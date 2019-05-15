import java.util.*;
import java.io.*;
public class Parser {
  private Stokenizer st;
  private Map<String, Double> vars;
  private final double TOLERANCE = 1E-10; 

  public Parser(Stokenizer tokenizer, Map<String, Double> variables){
    st = tokenizer;
    vars = variables;
  }

  public double assignment(){
    double parsed = expression();

    while (st.getToken().equals("=")) {
      st.nextToken();
      if (st.isWord()){
        vars.put(st.getToken() , parsed);
        st.nextToken();
      }else{
        throw new SyntaxException("Expected variable after '=' \n**** The Error occurred at token " + st.getToken() + " just after token " + st.getPreviousToken() );
      }
    }
    return parsed;
      
    
  }


  public double expression(){
    double sum = term();
    while(st.getToken().equals("+")){
      st.nextToken();
      sum += term();
    }
    while(st.getToken().equals("-")){
      st.nextToken();
      sum -= term();
    }
    
    return sum;
  }





  public double term(){
    double prod = factor();
    st.nextToken();
    
    while(st.getToken().equals("*")){
      st.nextToken();
      prod *= factor();
      st.nextToken();
    }
    while(st.getToken().equals("/")){
      st.nextToken();
      if (factor() != 0){
        
        prod /= factor();
        st.nextToken();
      }else{
        throw new EvaluationException("Division by zero");
      }
    }

    return prod;
  }


  public double factor() {
    return primary();
  }


  public double primary() throws SyntaxException{

    if (st.getToken().equals("(") ) {
      st.nextToken(); 
      double result = assignment();

      if (st.getToken().equals(")")) {

        return result;
      } else{
            throw new SyntaxException("Expected ')' \n**** The Error occurred at token " + st.getToken() + " just after token " + st.getPreviousToken() );
          }
    } else if ( st.isWord() ) {

        if ( st.getToken().equals("exp") ){
          st.nextToken();
          if (st.getToken().equals("(") ) {
            st.nextToken(); 
            double result = Math.exp(assignment());

            if (st.getToken().equals(")")) {
              return result;
            } else{
              throw new SyntaxException("Expected ')' \n**** The Error occurred at token " + st.getToken() + " just after token " + st.getPreviousToken() );
            }
          }
        
        }else if ( st.getToken().equals("log") ){
          st.nextToken();
          if (st.getToken().equals("(") ) {
            st.nextToken(); 
            double result = Math.log(assignment());

            if (st.getToken().equals(")")) {
              return result;
            } else{
              throw new SyntaxException("Expected ')' \n**** The Error occurred at token " + st.getToken() + " just after token " + st.getPreviousToken() );
            }
          }
        
        }else if ( st.getToken().equals("sin") ){
          st.nextToken();
          if (st.getToken().equals("(") ) {
            st.nextToken(); 
            double result = Math.sin(assignment());

            if (st.getToken().equals(")")) {
               if(result < TOLERANCE) return 0.; 
                else return result;
            }else{
              throw new SyntaxException("Expected ')' \n**** The Error occurred at token " + st.getToken() + " just after token " + st.getPreviousToken() );
            }
          }
        
        }else if ( st.getToken().equals("cos") ){
          st.nextToken();
          if (st.getToken().equals("(") ) {
            st.nextToken(); 
            double result = Math.cos(assignment());

            if (st.getToken().equals(")")) {
              return result;
            } else{
              throw new SyntaxException("Expected ')' \n**** The Error occurred at token " + st.getToken() + " just after token " + st.getPreviousToken() );
            }
          }
        
        }else if ( vars.containsKey(st.getToken()) ) {
          return vars.get(st.getToken());
        }else{
          throw new EvaluationException("Undefined variable " + st.getToken());
      }

    }//Word


    else if (st.isNumber()) {
      return st.getNumber();
    
    }else if ( st.getToken().equals("-") ){
      st.nextToken();
      return -primary();
    }else {
      throw new SyntaxException("Expected number, word or '(' \n**** The Error occurred at token " + st.getToken() + " just after token " + st.getPreviousToken() );
  
    }
    throw new EvaluationException("I can't let you do that ");

  }//Method 
}//Class
