import java.util.Map;

//                                                   class Constant
public class Constant extends Atom {
  private final double value;
  
  public Constant(double value) {
    this.value = value;
  }
  
  public String getName() {
    return "" + this.value;
  }
  
  public Sexpr eval(Map<String,Sexpr> map) {
    return this;
  }
  
  
  public boolean isConstant() {
    return true;
  }
  
  public boolean isConstant(double value) {
    return this.value == value;
  }
  
  public double getValue() {
    return this.value;
  }
  
  public Sexpr diff(Sexpr x) {
    return new Constant(0);
  }
}