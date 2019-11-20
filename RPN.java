package RPN;

import java.util.Scanner;

//stacknode class with methods
class StackNode {
	public StackNode userNum;
	public double dataOutput;
	
	public StackNode(double data, StackNode userNum) {
		this.dataOutput = data;
		this.userNum = userNum;
	}
}

//RPN class with methods
class RPN {
	private StackNode userName2;
	private String arithmetic_equation;
	
	public void into(double new_dataOutput) {
		StackNode new_node = new StackNode(new_dataOutput, userName2);
		userName2 = new_node;
	}

	public double outof() {
		double userName2_dataOutput = userName2.dataOutput;
		userName2 = userName2.userNum;
		return userName2_dataOutput;
	}

	public RPN(String arithmetic_equation) {
		userName2 = null;
		this.arithmetic_equation = arithmetic_equation;
	}

	public double get() {
		for (int i = 0; i < arithmetic_equation.length(); i++) {
			// if it's a digit
			if (Character.isDigit(arithmetic_equation.charAt(i))) {
				double number;
				
				// convert to double and add to the stack
				number = Double.parseDouble(String.valueOf(arithmetic_equation.charAt(i)));
				into(number);

				//else if its a char
			} else if (arithmetic_equation.charAt(i) == '+') {
				into(outof() + outof());
			} else if (arithmetic_equation.charAt(i) == '-') {
				into(outof() - outof());
			} else if (arithmetic_equation.charAt(i) == '*') {
				into(outof() * outof());
			} else if (arithmetic_equation.charAt(i) == '/') {
				into(outof() / outof());
			} else if (arithmetic_equation.charAt(i) == '^') {
				into(Math.pow(outof(), outof()));
			} else if (arithmetic_equation.charAt(i) != ' ') {
				throw new IllegalArgumentException();
			}
		}
		
		//returns outof() method
		return outof();
	}

	/* main method */
	public static void main(String args[]) {
		//while this condition is true it will keep executing
		while (true) {
			//Scanner object allows user input
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter RPN expression or \"quit\".");
			String input = scan.nextLine();
			
			//if the input is quit then program will stop running
			if (input.equals("quit")) {
				break;
				//else it taking in the input on RPN object and prints
			} else {
				RPN calc = new RPN(input);
				System.out.printf("Answer is %s", calc.get());
			}
		}
	}
}
