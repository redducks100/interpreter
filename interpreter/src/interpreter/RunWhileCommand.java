package interpreter;

import interpreter.Command.Function;

public class RunWhileCommand implements Function {

	@Override
	public Object execute(Variable v) {
		return v.Value;
	}
	
}
