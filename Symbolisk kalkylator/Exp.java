import java.util.Map;

public class Exp extends Function{
  
  public Exp(Sexpr arg){
    super(arg);
  }
  
  public String getName(){
    return "exp";
  }
  
  public Sexpr eval(Map<java.lang.String,Sexpr> m){
  //  if(this.argument.isConstant()){
      return Symbolic.exp(this.argument.eval(m));
  }
  
  public Sexpr diff(Sexpr variable){
    return Symbolic.mul(argument.diff(variable), new Exp(argument));
  }

}// klasssen