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
package pcgen.persistence.lst;

import pcgen.base.lang.UnreachableError;
import pcgen.base.util.Logging;
import pcgen.core.prereq.Prerequisite;
import pcgen.persistence.PersistenceLayerException;
import pcgen.persistence.lst.prereq.PreParserFactory;

public abstract class AbstractToken
{
	private final PreParserFactory PRE_PARSER;

	protected AbstractToken()
	{
		try
		{
			PRE_PARSER = PreParserFactory.getInstance();
		}
		catch (PersistenceLayerException ple)
		{
			Logging.errorPrint("Error Initializing PreParserFactory");
			Logging.errorPrint("  " + ple.getMessage(), ple);
			throw new UnreachableError();
		}
	}

	protected Prerequisite getPrerequisite(String token)
	{
		/*
		 * CONSIDER Need to add a Key, Value method to getPrerequisite and to
		 * .parse in the PRE_PARSER
		 */
		try
		{
			return PRE_PARSER.parse(token);
		}
		catch (PersistenceLayerException ple)
		{
			Logging.errorPrint("Error parsing Prerequisite in "
				+ getTokenName() + ": " + token);
			Logging.errorPrint("  " + ple.getMessage(), ple);
		}
		return null;
	}

	protected boolean hasIllegalSeparator(char separator, String value)
	{
		if (value.charAt(0) == separator)
		{
			Logging.errorPrint(getTokenName()
				+ " arguments may not start with " + separator + " : " + value);
			return true;
		}
		if (value.charAt(value.length() - 1) == separator)
		{
			Logging.errorPrint(getTokenName() + " arguments may not end with "
				+ separator + " : " + value);
			return true;
		}
		if (value.indexOf(String.valueOf(new char[]{separator, separator})) != -1)
		{
			Logging.errorPrint(getTokenName()
				+ " arguments uses double separator " + separator + separator
				+ " : " + value);
			return true;
		}
		return false;
	}

	protected boolean isEmpty(String value)
	{
		if (value == null)
		{
			Logging.errorPrint(getTokenName() + " may not have null argument");
			return true;
		}
		if (value.length() == 0)
		{
			Logging.errorPrint(getTokenName() + " may not have empty argument");
			return true;
		}
		return false;
	}

	protected abstract String getTokenName();

}
