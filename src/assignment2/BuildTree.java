package assignment2;

import java.util.Scanner;

public class BuildTree {
	private static int position=0;
	private static Object token;
	private static String input;

	public static Object getToken() {	
		if(position < input.length()){

			if(Character.isDigit(input.charAt(position))){
				StringBuilder sb = new StringBuilder();
				for (int i = position; i < input.length(); i++) {
					if (input.substring(i, i + 1).matches("[0-9,.]")) {
						sb.append(input.charAt(i));
						continue;
					}
					position = i;
					return token=sb.toString();
				}
				position = input.length();
				return token=sb.toString();

			}
			else{
				token = input.charAt(position);
				position++;
				return token;
			}			
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
				if(finalTree.getData().equals('+') ){				
					return leftChild + rightChild;
				}else if(finalTree.getData().equals('-')){
					return leftChild - rightChild;
				}else if(finalTree.getData().equals('*')){
					return leftChild * rightChild;
				}else if(finalTree.getData().equals('/')){
					return leftChild / rightChild;
				}
				return 0.0d;
			}
			//else if(finalTree.getData().toString().startsWith("[0-9]")){
			else{
				return Double.parseDouble((String) finalTree.getData());
			}
		}
		return 0.0;
	}

	private static TreeType expression(){
		TreeType termTree = new TreeType();
		termTree = term();
		TreeType expressionTree = new TreeType();

		if(token.equals('#')){
			return expressionTree = termTree;
		}
		else if(token.equals('+') || token.equals('-')){	
			Object temp = token;
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
		if(token.equals('#')){
			return termTree = factorTree;
		}
		else if(token.equals('*') || token.equals('/')){		
			Object temp = token;
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

		if(token.toString().startsWith("[0-9]")){	
			TreeType digitTree = new TreeType();
			digitTree = digit();
			if(token.equals('#')){
				factorTree = digitTree ;
				return factorTree;
			}
			factorTree = digitTree;				
		}
		else if(token.equals('(')){			
			TreeType factor2Tree = new TreeType();
			getToken();
			factor2Tree = expression();
			if(token.equals(')'))
				getToken();
			factorTree = factor2Tree;
		}
		else{
			TreeType digitTree = new TreeType();
			digitTree = digit();
			if(token.equals('#')){
				factorTree = digitTree ;
				return factorTree;
			}
			factorTree = digitTree;

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
