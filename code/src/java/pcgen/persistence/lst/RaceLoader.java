/*
 * RaceLoader.java
 * Copyright 2001 (C) Bryan McRoberts <merton_monk@yahoo.com>
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
 * Created on February 22, 2002, 10:29 PM
 *
 * Current Ver: $Revision$
 * Last Editor: $Author$
 * Last Edited: $Date$
 *
 */
package pcgen.persistence.lst;

import java.util.Map;
import java.util.StringTokenizer;

import pcgen.core.Constants;
import pcgen.core.Globals;
import pcgen.core.PObject;
import pcgen.core.Race;
import pcgen.persistence.LoadContext;
import pcgen.persistence.PersistenceLayerException;
import pcgen.persistence.SystemLoader;
import pcgen.util.Logging;

/**
 *
 * @author  David Rice <david-pcgen@jcuz.com>
 * @version $Revision$
 */
public final class RaceLoader extends LstObjectFileLoader<Race>
{
	/** Creates a new instance of RaceLoader */
	public RaceLoader()
	{
		super();
	}

	/**
	 * @see pcgen.persistence.lst.LstObjectFileLoader#parseLine(pcgen.core.PObject, java.lang.String, pcgen.persistence.lst.CampaignSourceEntry)
	 */
	@Override
	public void parseLine(Race race, String lstLine, CampaignSourceEntry source)
		throws PersistenceLayerException
	{

		final StringTokenizer colToken =
				new StringTokenizer(lstLine, SystemLoader.TAB_DELIM);
		Map<String, LstToken> tokenMap =
				TokenStore.inst().getTokenMap(RaceLstToken.class);
		while (colToken.hasMoreTokens())
		{
			final String colString = colToken.nextToken().trim();
			final int idxColon = colString.indexOf(':');
			String key = "";
			try
			{
				key = colString.substring(0, idxColon);
			}
			catch (Exception e)
			{
				// TODO Handle Exception
			}
			RaceLstToken token = (RaceLstToken) tokenMap.get(key);

			if (colString.startsWith("CHOOSE:LANGAUTO:"))
			{
				race.setChooseLanguageAutos(colString.substring(16));
			}
			else if (token != null)
			{
				final String value = colString.substring(idxColon + 1).trim();
				LstUtils.deprecationCheck(token, race, value);
				if (!token.parse(race, value))
				{
					Logging.errorPrint("Error parsing race "
						+ race.getDisplayName() + ':' + source.getURI() + ':'
						+ colString + "\"");
				}
			}
			else if (PObjectLoader.parseTag(race, colString))
			{
				continue;
			}
			else
			{
				Logging.errorPrint("Illegal race tag '" + colString + "' in "
					+ source.getURI());
			}
		}

		if ((race.getLevelAdjustment(null) != 0) && (race.getCR() == 0))
		{
			race.setCR(race.getLevelAdjustment(null));
		}

		if (race.getRaceType().equals(Constants.s_NONE))
		{
			/** TODO Uncomment this once the data is updated. */
			//			logError("Race " + race.getName() + " has no race type.");
		}

		completeObject(source, race);
	}

	/**
	 * @see pcgen.persistence.lst.LstObjectFileLoader#getObjectKeyed(java.lang.String)
	 */
	@Override
	protected Race getObjectKeyed(String aKey)
	{
		return Globals.getRaceKeyed(aKey);
	}

	/**
	 * @see pcgen.persistence.lst.LstObjectFileLoader#performForget(pcgen.core.PObject)
	 */
	@Override
	protected void performForget(final Race objToForget)
	{
		Globals.removeRaceKeyed(objToForget.getKeyName());
	}

	/**
	 * @see pcgen.persistence.lst.LstObjectFileLoader#addGlobalObject(pcgen.core.PObject)
	 */
	@Override
	protected void addGlobalObject(final PObject pObj)
	{
		Globals.addRace((Race) pObj);
	}


	@Override
	public void parseToken(LoadContext context, Race race, String key, String value, CampaignSourceEntry source) throws PersistenceLayerException {
		RaceLstToken token = TokenStore.inst().getToken(RaceLstToken.class,
				key);
		
		/*
		 * WARNING Doesn't handle:
		 * 
			else if (colString.startsWith("CHOOSE:LANGAUTO:"))
			{
				race.setChooseLanguageAutos(colString.substring(16));
			}
		 */

		if (token == null) {
			if (!PObjectLoader.parseTag(context, race, key, value)) {
				Logging.errorPrint("Illegal race Token '" + key + "' for "
						+ race.getDisplayName() + " in " + source.getURI()
						+ " of " + source.getCampaign() + ".");
			}
		} else {
			LstUtils.deprecationCheck(token, race, value);
			if (!token.parse(context, race, value))
			{
				Logging.errorPrint("Error parsing token " + key + " in race "
						+ race.getDisplayName() + ':' + source.getURI() + ':'
						+ value + "\"");
			}
		}
	}

	@Override
	public Class<Race> getLoadClass() {
		return Race.class;
	}
}
