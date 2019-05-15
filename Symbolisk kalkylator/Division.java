  import java.util.Map;
  
  public class Division extends Binary{
    
    
    
    public Division(Sexpr l, Sexpr r){
      super(l, r);
    }
    
    public int priority(){
      return 40;
    }
    
    public String getName(){
      return " / ";
    }
    
    
    
    public Sexpr eval(Map<java.lang.String,Sexpr> m){
      return Symbolic.div(this.left.eval(m), this.right.eval(m));
    }
  
    public Sexpr diff(Sexpr x){
      return new Division( new Subtraction(Symbolic.mul(this.left.diff(x), this.right), Symbolic.mul(this.left, this.right.diff(x))), Symbolic.mul(this.right, this.right));
  }
    
  }