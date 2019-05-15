import java.util.Map;

public class Negation extends Uoperator{
  
  public Negation(Sexpr arg){
    super(arg);
  }
  
  public String getName(){
    return " -" ;
  }

  public Sexpr eval(Map<java.lang.String,Sexpr> m){
    return Symbolic.sub(new Constant(0), this.argument.eval(m));
  }
  
  

  
}