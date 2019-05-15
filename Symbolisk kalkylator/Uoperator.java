import java.util.Map;

//   class Uoperator
public abstract class Uoperator extends Unary{
  
  public Uoperator(Sexpr argument) {
    super(argument);
  }
  
  public String toString() {
    String arg = argument.toString();
    if (this.priority()>argument.priority())
      arg = "(" + arg + ")";
    return getName() + arg;
  }
}