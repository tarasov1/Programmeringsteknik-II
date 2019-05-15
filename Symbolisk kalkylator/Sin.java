import java.util.Map;

public class Sin extends Function{
  
  public Sin(Sexpr arg){
    super(arg);
  }
  
  public String getName(){
    return "sin";
  }
  
  public Sexpr eval(Map<java.lang.String,Sexpr> m){
      return Symbolic.sin(this.argument.eval(m));
  }

  public Sexpr diff(Sexpr variable){
    return Symbolic.mul(argument.diff(variable), new Cos(argument));
  }
}// klasssen