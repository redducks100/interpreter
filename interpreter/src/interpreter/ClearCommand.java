package interpreter;

import interpreter.Command.Function;

public class ClearCommand implements Function {

	@Override
	public Object execute(Variable v) {
			v.Value = 0;
			return null;
	}
	
}
