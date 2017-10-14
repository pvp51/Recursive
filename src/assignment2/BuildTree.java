package assignment2;

import java.util.Scanner;

public class BuildTree {
	private static int pos=0;
	private static char token;
	private static String input;

	public static char getToken() {	
		if(pos < input.length()){
			token = input.charAt(pos);
			pos++;
			return token;
		}
		else{
			token = '#';
			return token;
		}
	}

	/*<expression> ::= <term> + <expression> | <term> - <expression> | <term>
    <term> ::= <factor> * <term> | <factor> / <term> | <factor>
    <factor> ::= <digit> | ( <expression> )
    <digit> ::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 */
	public static void main(String[] args) {
		Double result = 0.0;
				
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);		
		System.out.println("Enter the input expression: ");
		input = scan.nextLine();
		System.out.println("The input expression: "+input);		
		getToken();		
		TreeType finalTree = new TreeType();

		finalTree = expression();
		result = solveExpression(finalTree);
		System.out.println(result);
	}

	private static Double solveExpression(TreeType finalTree) {

		if(finalTree!=null){
			if(finalTree.getLeft()!=null && finalTree.getRight()!=null){
				double rightChild = solveExpression(finalTree.getRight());
				double leftChild = solveExpression(finalTree.getLeft());				
				if(finalTree.getData() == '+' ){				
					return leftChild + rightChild;
				}else if(finalTree.getData() == '-' ){
					return leftChild - rightChild;
				}else if(finalTree.getData() == '*'){
					return leftChild * rightChild;
				}else if(finalTree.getData() == '/'){
					return leftChild / rightChild;
				}
				return 0.0d;
			}
			else if(Character.isDigit(finalTree.getData())){
				return (double) Character.digit(finalTree.getData(), 10);
			}
		}
		return 0.0;
	}

	private static TreeType expression(){
		TreeType termTree = new TreeType();
		termTree = term();
		TreeType expressionTree = new TreeType();

		if(token == '#'){
			return expressionTree = termTree;
		}
		else if(token == '+' || token == '-'){	
			char temp = token;
			TreeType expression2Tree = new TreeType();
			getToken();
			expression2Tree = expression();
			expressionTree.setData(temp);
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
		if(token == '#'){
			return termTree = factorTree;
		}
		else if(token == '*' || token == '/'){		
			char temp = token;
			TreeType term2Tree = new TreeType();
			getToken();
			term2Tree = term();
			termTree.setData(temp);
			termTree.setLeft(factorTree);
			termTree.setRight(term2Tree);				
		}
		else{
			termTree = factorTree;
		}

		return termTree;		
	}

	private static TreeType factor(){
		TreeType factorTree= new TreeType();

		if(Character.isDigit(token)){	
			TreeType digitTree = new TreeType();
			digitTree = digit();
			if(token == '#'){
				factorTree = digitTree ;
				return factorTree;
			}
			factorTree = digitTree;				
		}
		else if(token == '('){			
			TreeType factor2Tree = new TreeType();
			getToken();
			factor2Tree = expression();
			if(token == ')')
				getToken();
			factorTree = factor2Tree;
		}
		else{

		}
		return factorTree;		
	}

	private static TreeType digit(){
		TreeType digitTree = new TreeType();

		digitTree.setData(token);
		getToken();
		digitTree.setLeft(null);
		digitTree.setRight(null);
		return digitTree;		
	}
}
