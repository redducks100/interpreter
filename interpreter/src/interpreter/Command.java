package interpreter;

public class Command {
	
	public String name;
	private Function command;
	
	public Command(String name, Function command)
	{
		this.name = name;
		this.command = command;
	}
	
	public Object Execute(Variable var)
	{
		return command.execute(var);
	}
	
	interface Function
	{
		Object execute(Variable v);
	}
}
