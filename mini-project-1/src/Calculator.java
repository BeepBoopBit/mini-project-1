public class Calculator {
    public double add(double a, double b){
        return a + b;
    }
    public double sub(double a, double b){
        return a-b;
    }
    public double mul(double a, double b){
        return a*b;
    }
    public double div(double a, double b){
        try{
            return a/b;
        }catch (ArithmeticException e){
            throw new ArithmeticException("Can't Divide by zero");
        }
    }
}
