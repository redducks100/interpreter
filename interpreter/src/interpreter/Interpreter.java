package interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Interpreter { 
	private ArrayList<String> source;
	private Loop mainLoop;
	
	public static HashMap<Integer, Loop> loops = new HashMap<Integer,Loop>();
	
	private Condition loopCondition = new Condition("not", new NotCondition());
	
	public static HashMap<String, Command> commands;
	
	public Interpreter(ArrayList<String> source)
	{
		this.source = source;
		commands = new HashMap<String,Command>();
		Command increaseCommand = new Command("incr", new IncreaseCommand());
		Command decreaseCommand = new Command("decr", new DecreaseCommand());
		Command clearCommand = new Command("clear", new ClearCommand());
		Command loopCommand = new Command("while", new RunWhileCommand());
		
		commands.put(increaseCommand.name, increaseCommand);
		commands.put(decreaseCommand.name, decreaseCommand);
		commands.put(clearCommand.name, clearCommand);
		commands.put(loopCommand.name, loopCommand);
		
		mainLoop = new Loop(0,new Variable("mainVariable",1), loopCondition, new HashMap<String, Variable>(),  new ArrayList<Pair<String,Variable>>());
		loops.put(mainLoop.index,mainLoop);
		Compile();
		Run();
	}
	
	public void Compile()
	{
		Loop currentLoop = mainLoop;
		int lastLoopIndex = 0;
		for(String currentLine : source)
		{
			currentLine = currentLine.trim();
			if(currentLine.contains("clear")) {
				String variableName = currentLine.substring(6, currentLine.length()-1);
				if(!currentLoop.Variables.containsKey(variableName))
				{
					currentLoop.Variables.put(variableName, new Variable(variableName,0));
				}
				Variable var = currentLoop.Variables.get(variableName);
				currentLoop.Commands.add(new Pair<String, Variable>("clear",var));
			}
			else if(currentLine.contains("incr"))
			{
				String variableName = currentLine.substring(5, currentLine.length()-1);
				if(!currentLoop.Variables.containsKey(variableName))
				{
					currentLoop.Variables.put(variableName, new Variable(variableName,0));
				}
				Variable var = currentLoop.Variables.get(variableName);
				currentLoop.Commands.add(new Pair<String, Variable>("incr",var));
			}
			else if(currentLine.contains("decr"))
			{
				String variableName = currentLine.substring(5, currentLine.length()-1);
				if(!currentLoop.Variables.containsKey(variableName))
				{
					currentLoop.Variables.put(variableName, new Variable(variableName,0));
				}
				Variable var = currentLoop.Variables.get(variableName);
				currentLoop.Commands.add(new Pair<String, Variable>("decr",var));
			}
			else if(currentLine.contains("while"))
			{
				String variableName = currentLine.substring(6, currentLine.length()-10);
				String loopNumberS = currentLine.substring(10 + variableName.length(), currentLine.length()-3).trim();
				int loopNumber = Integer.parseInt(loopNumberS);
				if(!currentLoop.Variables.containsKey(variableName))
				{
					currentLoop.Variables.put(variableName, new Variable(variableName,0));
				}
				Variable var = currentLoop.Variables.get(variableName);
				Loop newLoop = new Loop(lastLoopIndex+1,var, loopCondition, currentLoop.Variables, new ArrayList<Pair<String,Variable>>());
				newLoop.loopNumber = loopNumber;
				loops.put(newLoop.index, newLoop);
				currentLoop.Commands.add(new Pair<String, Variable>("while", new Variable("loopIndex",newLoop.index)));
				currentLoop = newLoop;
				lastLoopIndex++;
			}
			else if(currentLine.contains("end;"))
			{
				currentLoop.compiled = true;
				currentLoop = loops.get(currentLoop.index-1);
				while(currentLoop.compiled == true)
				{
					currentLoop = loops.get(currentLoop.index-1);
				}
			}
		}
		mainLoop.Commands.add(new Pair<String,Variable>("decr",mainLoop.loopVariable));
		System.out.println("Compiled");
	}
	
	public void Run()
	{
		mainLoop.Execute();
		System.out.println("Done");
		for(Variable var : mainLoop.Variables.values())
		{
			System.out.println(var.Name + "-> " + var.Value);
		}
	}
}
