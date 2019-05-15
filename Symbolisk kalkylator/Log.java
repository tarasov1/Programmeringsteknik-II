import java.util.Map;

public class Log extends Function{
  
  public Log(Sexpr arg){
    super(arg);
  }
  
  public String getName(){
    return "log";
  }
  
  public Sexpr eval(Map<java.lang.String,Sexpr> m){
  //  if(this.argument.isConstant()){
      return Symbolic.log(this.argument.eval(m));
  }
  
  public Sexpr diff(Sexpr x){
    return new Division(this.argument.diff(x), this.argument);
  }

}// klasssen