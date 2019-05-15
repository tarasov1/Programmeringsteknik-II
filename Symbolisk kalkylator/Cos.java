import java.util.Map;

public class Cos extends Function{
  
  public Cos(Sexpr arg){
    super(arg);
  }
  
  public String getName(){
    return "cos";
  }
  
  public Sexpr eval(Map<java.lang.String,Sexpr> m){
  //  if(this.argument.isConstant()){
      return Symbolic.cos(this.argument.eval(m));
  }
  
  public Sexpr diff(Sexpr variable){
    return Symbolic.mul(argument.diff(variable), new Negation(new Sin(argument)));
  }

}// klasssen