
import java.util.*;
public class Sample {
	public static void main(String [ ] args) {
		Scanner keyboard = new Scanner(System.in);
		int num, den;
		double result;
		System.out.print("Numerator? ");
		num = keyboard.nextInt();
		System.out.print("Denominator? ");
		den = keyboard.nextInt();
		try {
			result = quotient(num, den);
			System.out.println("Answer: " + result);
		}
		catch (DivideByZeroException e) {
			System.out.println("DivideByZeroException caught!");
		}
		catch (ArithmeticException e) {
			System.out.println("ArithmeticException caught!");
		}
		catch (Exception e) {
			System.out.println("Exception caught!");
		}
	}
	public static double quotient(int num, int den) {
		if (den==0) 
			throw new DivideByZeroException();
		return (double) num / den;
	}
}

public class DivideByZeroException extends ArithmeticException {
}
