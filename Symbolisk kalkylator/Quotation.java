import java.util.Map;

public class Quotation extends Uoperator{
  
  public Quotation(Sexpr arg){
    super(arg);
  }
  
  public String getName(){
    return "\"";
  }
  
  public Sexpr eval(Map<java.lang.String,Sexpr> m){
    return this.argument;
  }  
  
} // Här slutar klassen.