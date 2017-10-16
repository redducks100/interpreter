package interpreter;

import interpreter.Command.Function;

public class DecreaseCommand implements Function {

	@Override
	public Object execute(Variable v) {
		v.changeValue(-1);
		return null;
	}
	
}
