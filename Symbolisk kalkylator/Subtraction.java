import java.util.Map;

public class Subtraction extends Binary{
  
  
  public Subtraction(Sexpr l, Sexpr r){
    super(l,r);
  }
  
  public String getName(){
    return " - ";
  }
  
  public int priority(){
    return 15;
  }
  
  public Sexpr eval(Map<java.lang.String,Sexpr> m){
    return Symbolic.sub(this.left.eval(m), this.right.eval(m));
  }
  
    public Sexpr diff(Sexpr variable){
    return Symbolic.sub(this.left.diff(variable), this.right.diff(variable));
  }
  
  
  
  
  
}