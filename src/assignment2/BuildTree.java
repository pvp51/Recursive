package assignment2;

import java.util.Scanner;

public class BuildTree {
	private static int pos=0;
	private static char token;
	private static String input;
	
	public static char getToken() {	
		token = input.charAt(pos);
		pos++;
		return token;
		
	}

	/*<expression> ::= <term> + <expression> | <term> - <expression> | <term>
    <term> ::= <factor> * <term> | <factor> / <term> | <factor>
    <factor> ::= <digit> | ( <expression> )
    <digit> ::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 */
	public static void main(String[] args) {
		
		input = "7-5-3";		
		getToken();		
		TreeType finalTree = new TreeType();
		
		/*String output;		
		Scanner scan = new Scanner(System.in);		
		System.out.println("Enter the input expression: ");
		input = scan.nextLine();
		System.out.println("The input expression: "+input);*/
		
		finalTree = expression();
		System.out.println(finalTree.toString());
		

	}
	
	private static TreeType expression(){
		TreeType termTree = new TreeType();
		termTree = term();
		TreeType expressionTree = new TreeType();
		
		if(token == '+' ){			
			TreeType expression2Tree = new TreeType();
			getToken();
			expression2Tree = expression();
			expressionTree.setData('+');
			expressionTree.setLeft(termTree);
			expressionTree.setRight(expression2Tree);				
		}
		else if(token == '-'){			
			TreeType expression2Tree = new TreeType();
			getToken();
			expression2Tree = expression();
			expressionTree.setData('-');
			expressionTree.setLeft(termTree);
			expressionTree.setRight(expression2Tree);				
		}
		else{
			expressionTree = termTree;
		}
		return expressionTree;		
	}
	
	private static TreeType term(){
		TreeType factorTree = new TreeType();
		factorTree = factor();
		TreeType termTree = new TreeType();
		if(token == '*'){			
			TreeType term2Tree = new TreeType();
			getToken();
			term2Tree = term();
			termTree.setData('*');
			termTree.setLeft(factorTree);
			termTree.setRight(term2Tree);				
		}
		else if(token == '/'){			
			TreeType term2Tree = new TreeType();
			getToken();
			term2Tree = term();
			termTree.setData('/');
			termTree.setLeft(factorTree);
			termTree.setRight(term2Tree);				
		}
		else{
			termTree = factorTree;
		}
		
		return termTree;		
	}
	
	private static TreeType factor(){
		TreeType digitTree = new TreeType();
		digitTree = digit();
		TreeType factorTree= new TreeType();

		
		if(Character.isDigit(token)){			
			//getToken();
			factorTree = digitTree;				
		}
		
		return factorTree;		
	}
	
	private static TreeType digit(){
		TreeType digitTree = new TreeType();
		
		digitTree.setData(token);
		//getToken();
		digitTree.setLeft(null);
		digitTree.setRight(null);
		return digitTree;		
	}
}
