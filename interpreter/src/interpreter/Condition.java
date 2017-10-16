package interpreter;

public class Condition {
	
	public String Name;
	private Function value;
	
	public Condition(String Name, Function value)
	{
		this.Name = Name;
		this.value = value;
	}
	
	public boolean Execute(Variable var, int number)
	{
		return value.execute(var, number);
	}
	
	interface Function {
		boolean execute(Variable v, int number);
	}
}
