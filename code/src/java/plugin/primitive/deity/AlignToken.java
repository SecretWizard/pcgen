package plugin.primitive.deity;

import pcgen.cdom.base.CDOMSimpleSingleRef;
import pcgen.cdom.enumeration.ObjectKey;
import pcgen.core.Alignment;
import pcgen.core.Deity;
import pcgen.core.PlayerCharacter;
import pcgen.persistence.LoadContext;
import pcgen.persistence.lst.PrimitiveToken;

public class AlignToken implements PrimitiveToken<Deity>
{

	private CDOMSimpleSingleRef<Alignment> ref;

	public void initialize(LoadContext context, String value)
	{
		ref = context.ref.getCDOMReference(Alignment.class, value);
	}

	public String getTokenName()
	{
		return "ALIGN";
	}

	public Class<Deity> getReferenceClass()
	{
		return Deity.class;
	}

	public String getLSTformat()
	{
		return ref.getLSTformat();
	}

	public boolean allow(PlayerCharacter pc, Deity deity)
	{
		return ref.resolvesTo().equals(deity.get(ObjectKey.ALIGNMENT));
	}

}
