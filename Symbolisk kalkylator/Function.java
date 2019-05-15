import java.util.Map;

public abstract class Function extends Unary{

  public Function(Sexpr arg){
    super(arg);
  }
  
  public String toString(){
    return "" + this.getName() + "(" + argument.toString() + ")";
  }
  
  
  
}// klasssen