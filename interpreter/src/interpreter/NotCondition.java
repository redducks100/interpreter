package interpreter;

import interpreter.Condition.Function;

public class NotCondition implements Function {

	@Override
	public boolean execute(Variable v, int number) {
		return v.Value != number;
	}
}
