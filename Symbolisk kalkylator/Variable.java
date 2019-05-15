import java.util.Map;

public class Variable extends Atom{
  
  public final String name;
  
  public Variable(String name){
    this.name = name;
  }
  
  public String getName(){
    return "" + this.name;
  }
  
  
  public Sexpr eval(Map<java.lang.String,Sexpr> m){
    if( m.get(this.name) == null)
      return this;
    else 
      return m.get(this.name);
  }
  
  public Sexpr diff(Sexpr x){
    if(this.name.equals(x.getName()))
      return new Constant(1.);
   
    else
      return new Constant(0.);
  }
  
  public boolean isVariable(){
    return true;
  }
    
  
}