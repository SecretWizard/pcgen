/*
 * Copyright 2007 (C) Tom Parker <thpr@users.sourceforge.net>
 * 
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package pcgen.cdom.inst;

import pcgen.cdom.base.CDOMObject;

public final class Aggregator extends CDOMObject
{

	private final Object aggregatorOwner;
	private final Object aggregatorSource;
	private final String aggSourceToken;

	public Aggregator(Object owner, Object source, String sourceToken)
	{
		aggregatorOwner = owner;
		aggregatorSource = source;
		aggSourceToken = sourceToken;
	}

	// No additional Functionality :)

	@Override
	public int hashCode()
	{
		return aggregatorOwner.hashCode() ^ aggregatorSource.hashCode()
			^ aggSourceToken.hashCode();
	}

	@Override
	public boolean equals(Object o)
	{
		if (o == this)
		{
			return true;
		}
		if (!(o instanceof Aggregator))
		{
			return false;
		}
		Aggregator other = (Aggregator) o;
		return other.aggSourceToken.equals(aggSourceToken)
			&& other.aggregatorOwner.equals(aggregatorOwner)
			&& other.aggregatorSource.equals(aggregatorSource)
			&& isCDOMEqual(other) && equalsPrereqObject(other);
	}

	@Override
	protected CDOMObject getRawReplacement()
	{
		return new Aggregator(aggregatorOwner, aggregatorSource, aggSourceToken);
	}
}
