import java.util.Map;

public class Symbolic {
  
  public static Sexpr add(Sexpr left, Sexpr right) {
    if ( left.isConstant() && right.isConstant() )
      return new Constant(left.getValue() + right.getValue());
    else if ( left.isConstant(0.) )
      return right;
    else if ( right.isConstant(0.) )
      return left;
    else if (right.toString().equals(left.toString()))
      return Symbolic.mul(new Constant(2.), left);
    else 
      return new Addition(left, right);
  }
  
  public static Sexpr sub(Sexpr left, Sexpr right) {
    if ( left.isConstant() && right.isConstant() )
      return new Constant(left.getValue() - right.getValue());
    else if ( right.isConstant(0.) )
      return left;
    else if ( left.toString().equals(right.toString()) )
      return new Constant(0.);
    else 
      return new Subtraction(left, right);
  }
  
//  public static Sexpr neg(Sexpr arg){
//    
//  }
  
  public static Sexpr mul(Sexpr left, Sexpr right) {
    if ( left.isConstant() && right.isConstant() )
      return new Constant(left.getValue() * right.getValue());
    else if ( left.isConstant(1.) )
      return right;
    else if ( right.isConstant(1.) )
      return left;
    else if (left.isConstant(0.) || right.isConstant(0.))
      return new Constant(0.);
    else 
      return new Multiplication(left, right);    
  }
  
  public static Sexpr div(Sexpr left, Sexpr right) {
    if (right.isConstant(0.))
      throw new EvaluationException("Division med noll är odefinerat. ");
    else if (left.isConstant() && right.isConstant())
      return new Constant(left.getValue()/right.getValue());
    else if (left.toString().equals(right.toString()))
      return new Constant(1.);
    else
      return new Division(left, right);
  }
  
  public static Sexpr neg(Sexpr arg) {
    if (arg.isConstant())
      return new Constant(-arg.getValue());
    else
      return new Negation(arg);
  }
  
  public static Sexpr exp(Sexpr arg) {
    if (arg.isConstant())
      return new Constant(Math.exp(arg.getValue()));
    else
      return new Exp(arg);
  }
  
  public static Sexpr log(Sexpr arg){
    if (arg.isConstant()){
      if(arg.getValue() <= 0.)
        throw new EvaluationException("Logaritmen av ett ta mindre eller lika med noll är odefinerad. ");
      else
        return new Constant(Math.log(arg.getValue()));
    }
    else
      return new Log(arg);
  }
  
  public static Sexpr sin(Sexpr arg) {
    if (arg.isConstant())
      return new Constant(Math.sin(arg.getValue()));
    else
      return new Sin(arg);
  }
  
  public static Sexpr cos(Sexpr arg) {
    if (arg.isConstant())
      return new Constant(Math.cos(arg.getValue()));
    else
      return new Cos(arg);
  }
  
//  public static Sexpr pot(Sexpr left, Sexpr right){
//    if(left.isConstant() && right.isConstant())
//      return new Constant(Math.pow(left.getValue(), right.getValue()));
//    else if(left.isPotens() && right.isPotens())
//      //return 
//   // else
//      return new Potens(left, right);
//  }
  
//  public static Sexpr max(Sexpr arg1, Sexpr arg2) {
//    if (arg1.isConstant() && arg2.isConstant())
//      return new Constant(Math.max(arg1.getValue(),arg2.getValue()));
//    else
//      return new Maximum(arg1, arg2);
//  } 
//  
//  public static Sexpr min(Sexpr arg1, Sexpr arg2) {
//    if (arg1.isConstant() && arg2.isConstant())
//      return new Constant(Math.min(arg1.getValue(),arg2.getValue()));
//    else
//      return new Minimum(arg1, arg2);
//  } 
}