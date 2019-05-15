import java.util.Map;

public class Differentiation extends Binary{
  
  
  public Differentiation(Sexpr l,Sexpr r){
    super(l, r);
  }
  
  public String getName(){
    return "'";
  }
  
  public int priority(){
    return 2;
  }
  
  
  public Sexpr eval(Map<java.lang.String,Sexpr> m){
    return left.eval(m).diff(right);
  }
  
  public Sexpr diff(Map<java.lang.String,Sexpr> m, Sexpr x){
    return this.left.diff(this.right).diff(x);
  }
  
}