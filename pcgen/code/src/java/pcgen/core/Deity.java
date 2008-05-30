/*
 * Deity.java
 * Copyright 2001 (C) Bryan McRoberts (merton_monk@yahoo.com)
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
 * Created on April 21, 2001, 2:15 PM
 *
 * Current Ver: $Revision$
 * Last Editor: $Author$
 * Last Edited: $Date$
 */
package pcgen.core;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import pcgen.base.lang.StringUtil;
import pcgen.cdom.base.CDOMReference;
import pcgen.cdom.base.Constants;
import pcgen.cdom.enumeration.ListKey;
import pcgen.cdom.enumeration.ObjectKey;
import pcgen.cdom.enumeration.StringKey;
import pcgen.cdom.list.DomainList;
import pcgen.cdom.reference.CDOMDirectSingleRef;
import pcgen.core.prereq.PrereqHandler;
import pcgen.core.utils.MessageType;
import pcgen.core.utils.ShowMessageDelegate;

/**
 * <code>Deity</code>.
 *
 * @author Bryan McRoberts <merton_monk@users.sourceforge.net>
 * @version $Revision$
 */
public final class Deity extends PObject
{
	public static final CDOMReference<DomainList> DOMAINLIST = CDOMDirectSingleRef
			.getRef(new DomainList());

	/**
	 * Check whether this deity can be selected by a character with the
	 * given classes, alignment, race and gender.
	 *
	 * @param classList a list of PCClass objects.
	 * @param anAlignment 0 through 8 inclusive
	 * @param pc
	 * @return <code>true</code> means the deity can be a selected by a
	 * character with the given properties; <code>false</code> means the
	 * character cannot.
	 */
	public boolean canBeSelectedBy(final List<PCClass> classList, final int anAlignment, final PlayerCharacter pc)
	{
		boolean result;

		try
		{
			result = acceptableClass(classList.iterator())
							 && PrereqHandler.passesAll( getPreReqList(), pc, this);
		}
		catch (NumberFormatException nfe)
		{
			result = false;
		}

		return result;
	}

	/**
	 * Clones a Deity object
	 *
	 * @return A clone of the Deity object.
	 */
	@Override
	public Deity clone()
	{
		try
		{
			return (Deity) super.clone();
		}
		catch (CloneNotSupportedException exc)
		{
			ShowMessageDelegate.showMessageDialog(exc.getMessage(), Constants.s_APPNAME, MessageType.ERROR);
			return null;
		}
	}

	/**
	 * @return this deity's alignment
	 */
	public String getAlignment()
	{
		PCAlignment align = get(ObjectKey.ALIGNMENT);
		return align == null ? "" : align.getKeyName();
	}

	/**
	 * @return the name of the holy item of this deity
	 */
	public String getHolyItem()
	{
		String characteristic = stringChar.get(StringKey.HOLY_ITEM);
		return characteristic == null ? Constants.s_NONE : characteristic;
	}

	/**
	 * This method gets the text used in outputting source files (.pcc files)
	 * Made public on 10 Dec 2002 by sage_sam to match PObject method
	 * @return String containing properly formatted pcc text for this deity.
	 */
	@Override
	public String getPCCText()
	{
		final StringBuffer txt = new StringBuffer(200);
		txt.append(getDisplayName());
		txt.append("\t");
		txt.append(StringUtil.joinToStringBuffer(Globals.getContext().unparse(
				this), "\t"));
		txt.append("\t");
		txt.append(super.getPCCText(false));
		return txt.toString();
	}

	/**
	 * @param aDomain
	 * @return true if the deity has the passed-in domain
	 */
	public boolean hasDomain(final Domain aDomain)
	{
		if (aDomain == null)
		{
			return false;
		}
		Collection<CDOMReference<Domain>> domains = getListMods(Deity.DOMAINLIST);
		if (domains == null)
		{
			return false;
		}
		for (CDOMReference<Domain> ref : domains)
		{
			if (ref.contains(aDomain))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * This method determines whether any of the classes that a character
	 * has is acceptable to this deity.
	 * @param classList Iterator pointing to the Collection of classes the
	 * character has
	 * @return boolean
	 */
	private boolean acceptableClass(final Iterator<PCClass> classList)
	{
		boolean flag = (!classList.hasNext());

		while (classList.hasNext() && !flag)
		{
			final PCClass aClass = classList.next();
			List<CDOMReference<Deity>> deityList = aClass.getListFor(ListKey.DEITY);
			if (deityList == null)
			{
				flag = true;
			}
			else
			{
				for ( CDOMReference<Deity> deity : deityList )
				{
					if (deity.contains(this))
					{
						flag = true;
					}
				}
			}
		}

		return flag;
	}
}
