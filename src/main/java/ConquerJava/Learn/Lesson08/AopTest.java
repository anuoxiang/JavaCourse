package ConquerJava.Learn.Lesson08;

public class AopTest {
	public static void main(String[] args) {
		CalculatorImpl calcImpl = new CalculatorImpl();
        ICalculator proxied = (ICalculator)ProxyFactory.getProxy (ICalculator.class, calcImpl, 
                new SomeHandler(CalculatorImpl));
        int result = proxied.calculate(20, 10);
        System.out.println("FInal Result :::" + result);
	}
}
