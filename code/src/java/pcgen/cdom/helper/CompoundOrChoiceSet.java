/*
 * Copyright 2006 (C) Tom Parker <thpr@sourceforge.net>
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
 * 
 * Created on October 29, 2006.
 * 
 * Current Ver: $Revision: 1111 $ Last Editor: $Author: boomer70 $ Last Edited:
 * $Date: 2006-06-22 21:22:44 -0400 (Thu, 22 Jun 2006) $
 */
package pcgen.cdom.helper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import pcgen.cdom.base.Constants;
import pcgen.cdom.base.PrereqObject;
import pcgen.cdom.base.ReferenceUtilities;
import pcgen.core.PlayerCharacter;

public class CompoundOrChoiceSet<T extends PrereqObject> implements
		PrimitiveChoiceSet<T>
{

	private final Set<PrimitiveChoiceSet<T>> set =
			new HashSet<PrimitiveChoiceSet<T>>();

	public CompoundOrChoiceSet(Collection<PrimitiveChoiceSet<T>> coll)
	{
		if (coll == null)
		{
			throw new IllegalArgumentException();
		}
		set.addAll(coll);
	}

	public Set<T> getSet(PlayerCharacter pc)
	{
		Set<T> returnSet = new HashSet<T>();
		for (PrimitiveChoiceSet<T> cs : set)
		{
			returnSet.addAll(cs.getSet(pc));
		}
		return returnSet;
	}

	public String getLSTformat()
	{
		return ReferenceUtilities.joinLstFormat(set, Constants.PIPE);
	}

	public Class<T> getChoiceClass()
	{
		return set == null ? null : set.iterator().next().getChoiceClass();
	}
}
