import java.util.Map;

public class Evaluation extends Uoperator{
  
  public Evaluation(Sexpr argument){
    super(argument);
  }
  
  public String getName(){
    return "&";
  }
  
  public Sexpr eval(Map<java.lang.String,Sexpr> m){
  return this.argument.eval(m).eval(m);
  }
  
}