import java.util.Map;

public class Multiplication extends Binary{
  
  public Multiplication(Sexpr l, Sexpr r){
    super(l,r);
  }
  
  public String getName(){
    return " * ";
  }
  
  public int priority(){
    return 40;
  }
  
  public Sexpr eval(Map<java.lang.String,Sexpr> m){
   return Symbolic.mul(this.left.eval(m), this.right.eval(m));
  }
 
  public Sexpr diff(Sexpr x){
    return new Addition(Symbolic.mul(this.left.diff(x), this.right), Symbolic.mul(this.left, this.right.diff(x))); 
  }
}

