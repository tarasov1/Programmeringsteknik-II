import java.util.Map;

public abstract class Binary extends Sexpr{
  public final Sexpr left;
  public final Sexpr right;
  
  
  
  public Binary(Sexpr left, Sexpr right){
    this.left = left;
    this.right = right;
  }
  


  public String toString(){  
    String s;
    if(left.priority() < this.priority()){
      s = "(" + left.toString() + ")";
    }
    else{
      s = left.toString();
    }
    s = s + this.getName();
    if(this.priority() >= right.priority()){
      s = s + "( " + right.toString() + ")";
    }
    else{
      s = s + right.toString();
    }
    return s;   
  }

  
}