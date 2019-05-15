import java.util.Map;

//                                                   class Sexpr
public abstract class Sexpr {
  
  public abstract String getName();
  
  public abstract Sexpr eval(Map<String,Sexpr> map);
  
  public boolean isConstant() {
    return false;
  }
  
  public boolean isConstant(double value) {
    return false;
  }
  
  public boolean isVariable(){
    return false;
  }
  
  public int priority() {
    return 100; // Need not be parenthesized
  }
  
  public double getValue() {
    throw new RuntimeException("Internal error: illegal call to getValue()");
  }
  
  public Sexpr diff(Sexpr x){
    throw new EvaluationException("Can not differentiate: " + this.getName());
  }
}