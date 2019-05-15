import java.util.Map;


public abstract class Unary extends Sexpr {
  protected final Sexpr argument;
  
  public Unary(Sexpr argument) {
    this.argument = argument;
  }
}