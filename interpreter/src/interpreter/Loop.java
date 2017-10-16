package interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Loop 
{
	public int index;
	public ArrayList<Pair<String, Variable>> Commands;
	public HashMap<String,Variable> Variables;
	public Variable loopVariable;
	public int loopNumber;
	private Condition condition;
	
	public boolean compiled = false;
	
	public Loop(int index, Variable loopVariable, Condition condition, HashMap<String, Variable> Variables, ArrayList<Pair<String, Variable>> Commands)
	{
		this.index = index;
		this.loopVariable = loopVariable;
		this.condition = condition;
		this.Variables = Variables;
		this.Commands = Commands;
	}
	
	public void Execute()
	{
		while(condition.Execute(loopVariable, loopNumber))
		{
			for(int i=0; i < Commands.size();i++)
			{
				String currentCommandName = Commands.get(i).first;
				Variable currentVariable = Commands.get(i).second;
				
				Command currentCommand = Interpreter.commands.get(currentCommandName);
				
				if(currentCommand.name == "while")
				{
					Integer index = (Integer) currentCommand.Execute(currentVariable);
					Loop nextLoop = Interpreter.loops.get(index);
					nextLoop.Execute();
				}
				else
				{
					currentCommand.Execute(currentVariable);
				}
			}
		}
	}
}
