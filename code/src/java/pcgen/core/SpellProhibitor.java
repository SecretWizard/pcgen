/*
 * SpellProhibitor.java
 * Copyright 2005 (c) Stefan Raderamcher <radermacher@netcologne.de>
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * Created on March 3, 2005, 16:30 AM
 *
 * Current Ver: $Revision$
 * Last Editor: $Author$
 * Last Edited: $Date$
 *
 */
package pcgen.core;

import pcgen.core.prereq.PrereqHandler;
import pcgen.core.prereq.Prerequisite;
import pcgen.core.spell.Spell;
import pcgen.util.enumeration.ProhibitedSpellType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author stefan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SpellProhibitor {

	private ProhibitedSpellType type = null;
	private List<String> valueList = null;
	private List<Prerequisite> prereqList = null;

	public SpellProhibitor()
	{
		//Empty Construtor
	}

	public void addPreReq(final Prerequisite prereq)
	{
		if (prereqList == null)
		{
			prereqList = new ArrayList<Prerequisite>();
		}

		if (!prereqList.contains(prereq))
		{
			prereqList.add(prereq);
		}
	}

	public List<Prerequisite> getPrereqList()
	{
		return prereqList;
	}

	public ProhibitedSpellType getType()
	{
		return type;
	}

	public List<String> getValueList()
	{
		return valueList;
	}

	public void setType(ProhibitedSpellType prohibitedType) 
	{
		type = prohibitedType;
	}

	public void addValue(String value)
	{
		if (valueList == null)
		{
			valueList = new ArrayList<String>();
		}
		valueList.add(value);
	}
	
	public boolean isProhibited(Spell s, PlayerCharacter aPC)
	{
		/*
		 * Note the rule is only "Prohibit Cleric/Druid spells based on
		 * Alignment" - thus this Globals check is only relevant to the
		 * Alignment type
		 */
		if (type.equals(ProhibitedSpellType.ALIGNMENT)
				&& !Globals.checkRule(RuleConstants.PROHIBITSPELLS))
		{
			return false;
		}
		
		if (prereqList != null && !PrereqHandler.passesAll(prereqList, aPC, null))
		{
			return false;
		}
		
		int hits = 0;
		
		for (String typeDesc : type.getCheckList(s))
		{
			for (String prohib : valueList)
			{
				if (prohib.equalsIgnoreCase(typeDesc))
				{
					hits++;
				}
			}
		}
		
		return hits == valueList.size();
	}
}
