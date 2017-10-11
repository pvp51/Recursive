package assignment2;

public class Property {
	
	private int pos=-1;
	private String input;

	public int getPos() {
		return getInput().charAt(getPos());
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public int getToken() {
		return pos;
	}
	public void setToken() {
		this.pos++;
	}
	
	

}
