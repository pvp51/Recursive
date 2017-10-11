package assignment2;

import java.util.Scanner;

public class BuildTree {

    /*<expression> ::= <term> + <expression> | <term> - <expression> | <term>
    <term> ::= <factor> * <term> | <factor> / <term> | <factor>
    <factor> ::= <digit> | ( <expression> )
    <digit> ::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 */
	public static void main(String[] args) {
		Property prop = new Property();
		String input = "7-5-3";
		prop.setInput(input);
		
		/*String output;		
		Scanner scan = new Scanner(System.in);		
		System.out.println("Enter the input expression: ");
		input = scan.nextLine();
		System.out.println("The input expression: "+input);*/
		
		expression(prop);
		

	}
	
	private static TreeType expression(Property prop){
		TreeType termtree = null;
		term(termtree);
		
		if(prop.getToken() == '+'){
			
		}
		
		return null;		
	}
	
	private static TreeType term(TreeType tree){
		TreeType termtree;
		
		return null;		
	}
}
