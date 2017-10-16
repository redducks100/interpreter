package interpreter;

public class Variable {
	
	public String Name;
	public int Value;
	
	public Variable(String Name, int Value)
	{
		this.Name = Name;
		this.Value = Value;
	}
	
	public void changeValue(int value)
	{
		Value += value;
	}
	
}
