package pcgen.cdom.formula;

import pcgen.base.formula.Formula;
import pcgen.base.formula.Resolver;
import pcgen.core.SizeAdjustment;

public class FormulaSizeResolver implements Resolver<SizeAdjustment>
{

	private final Formula size;

	public FormulaSizeResolver(Formula s)
	{
		size = s;
	}

	public SizeAdjustment resolve()
	{
		//TODO Need to define how this will happen
		return null;
	}

	public String toLSTFormat()
	{
		return size.toString();
	}

}
