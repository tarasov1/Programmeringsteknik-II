import java.util.Map;

public class Assignment extends Binary{
  
  
  public Assignment(Sexpr l,Sexpr r){
    super(l,r);
  }
  
  
  
  public String getName(){
    return " = ";
  }
  
  public int priority(){
    return 90;
  }
  
  public Sexpr eval(Map<java.lang.String,Sexpr> m){
    m.put(right.getName(), this.left);
    return this.left.eval(m);
  }
  
  public Sexpr diff(Sexpr x){
    if(left.isConstant()){
      return left.diff( x);
    }
    else
      return new Assignment(left.diff( x), right.diff(x) );
  }
  
  
  
}