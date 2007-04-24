/*
 * Copyright (c) 2007 Tom Parker <thpr@users.sourceforge.net>
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA
 */
package plugin.lsttokens;

import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;

import pcgen.core.PCTemplate;
import pcgen.persistence.PersistenceLayerException;
import pcgen.persistence.lst.GlobalLstToken;
import pcgen.persistence.lst.LstObjectFileLoader;
import pcgen.persistence.lst.PCTemplateLoader;
import plugin.lsttokens.testsupport.AbstractGlobalTokenTestCase;
import plugin.lsttokens.testsupport.TokenRegistration;
import plugin.pretokens.parser.PreClassParser;
import plugin.pretokens.parser.PreRaceParser;
import plugin.pretokens.writer.PreClassWriter;
import plugin.pretokens.writer.PreRaceWriter;

public class CompanionListLstTest extends AbstractGlobalTokenTestCase
{

	static GlobalLstToken token = new CompanionListLst();
	static PCTemplateLoader loader = new PCTemplateLoader();

	PreClassParser preclass = new PreClassParser();
	PreClassWriter preclasswriter = new PreClassWriter();
	PreRaceParser prerace = new PreRaceParser();
	PreRaceWriter preracewriter = new PreRaceWriter();

	@Override
	@Before
	public void setUp() throws PersistenceLayerException, URISyntaxException
	{
		super.setUp();
		TokenRegistration.register(preclass);
		TokenRegistration.register(preclasswriter);
		TokenRegistration.register(prerace);
		TokenRegistration.register(preracewriter);
	}

	@Override
	public LstObjectFileLoader<PCTemplate> getLoader()
	{
		return loader;
	}

	@Override
	public Class<PCTemplate> getCDOMClass()
	{
		return PCTemplate.class;
	}

	@Override
	public GlobalLstToken getToken()
	{
		return token;
	}

	@Test
	public void testInvalidEmpty() throws PersistenceLayerException
	{
		assertFalse(token.parse(primaryContext, primaryProf, ""));
	}

	@Test
	public void testInvalidListNameOnly() throws PersistenceLayerException
	{
		assertFalse(token.parse(primaryContext, primaryProf, "Familiar"));
	}

	@Test
	public void testInvalidListNameBarOnly() throws PersistenceLayerException
	{
		assertFalse(token.parse(primaryContext, primaryProf, "Familiar|"));
	}

	@Test
	public void testInvalidEmptyListName() throws PersistenceLayerException
	{
		assertFalse(token.parse(primaryContext, primaryProf, "|Lion"));
	}

	@Test
	public void testInvalidTypeRaceBarOnly() throws PersistenceLayerException
	{
		assertFalse(token.parse(primaryContext, primaryProf, "Familiar|Lion|"));
	}

	@Test
	public void testInvalidRaceCommaStarting() throws PersistenceLayerException
	{
		assertFalse(token.parse(primaryContext, primaryProf, "Familiar|,Lion"));
	}

	@Test
	public void testInvalidRaceCommaEnding() throws PersistenceLayerException
	{
		assertFalse(token.parse(primaryContext, primaryProf, "Familiar|Lion,"));
	}

	@Test
	public void testInvalidRaceDoubleComma() throws PersistenceLayerException
	{
		assertFalse(token.parse(primaryContext, primaryProf,
			"Familiar|Lion,,Tiger"));
	}

	@Test
	public void testInvalidRacePipe() throws PersistenceLayerException
	{
		assertFalse(token.parse(primaryContext, primaryProf,
			"Familiar|Lion|Tiger"));
	}

	@Test
	public void testInvalidSpellEmbeddedPre() throws PersistenceLayerException
	{
		assertFalse(token.parse(primaryContext, primaryProf,
			"Familiar|Lion|PRERACE:1,Human|Tiger"));
	}

	@Test
	public void testInvalidNonSensicalAnyLast()
		throws PersistenceLayerException
	{
		assertFalse(token.parse(primaryContext, primaryProf,
			"Familiar|Tiger,Any"));
	}

	@Test
	public void testInvalidNonSensicalAnyFirst()
		throws PersistenceLayerException
	{
		assertFalse(token.parse(primaryContext, primaryProf,
			"Familiar|Any,Lion"));
	}

	@Test
	public void testInvalidEmbeddedFA() throws PersistenceLayerException
	{
		assertFalse(token.parse(primaryContext, primaryProf,
			"Familiar|FOLLOWERADJUSTMENT:-4|Lion"));
	}

	@Test
	public void testInvalidOnlyFOLLOWERADJUSTMENT()
		throws PersistenceLayerException
	{
		boolean parse =
				token.parse(primaryContext, primaryProf,
					"Familiar|FOLLOWERADJUSTMENT:-3");
		if (parse)
		{
			assertFalse(primaryContext.ref.validate());
		}
	}

	@Test
	public void testInvalidMultipleFOLLOWERADJUSTMENT()
		throws PersistenceLayerException
	{
		assertFalse(token.parse(primaryContext, primaryProf,
			"Familiar|Lion|FOLLOWERADJUSTMENT:-2|FOLLOWERADJUSTMENT:-3"));
	}

	@Test
	public void testInvalidOnlyFOLLOWERADJUSTMENTBar()
		throws PersistenceLayerException
	{
		assertFalse(token.parse(primaryContext, primaryProf,
			"Familiar|FOLLOWERADJUSTMENT:-3|"));
	}

	@Test
	public void testInvalidEmptyTimes() throws PersistenceLayerException
	{
		assertFalse(token.parse(primaryContext, primaryProf, "Familiar||Lion"));
	}

	@Test
	public void testInvalidBadFA() throws PersistenceLayerException
	{
		assertFalse(token.parse(primaryContext, primaryProf,
			"Familiar|Lion|FOLLOWERADJUSTMENT:"));
	}

	@Test
	public void testInvalidFANaN() throws PersistenceLayerException
	{
		assertFalse(token.parse(primaryContext, primaryProf,
			"Familiar|Lion|FOLLOWERADJUSTMENT:-T"));
	}

	@Test
	public void testInvalidFADecimal() throws PersistenceLayerException
	{
		assertFalse(token.parse(primaryContext, primaryProf,
			"Familiar|Lion|FOLLOWERADJUSTMENT:-4.5"));
	}

	@Test
	public void testInvalidOnlyPre() throws PersistenceLayerException
	{
		try
		{
			boolean parse =
					token.parse(primaryContext, primaryProf,
						"Familiar|FOLLOWERADJUSTMENT:-3|PRERACE:1,Human");
			if (parse)
			{
				assertFalse(primaryContext.ref.validate());
			}
		}
		catch (IllegalArgumentException iae)
		{
			// This is ok too
		}
	}

	@Test
	public void testRoundRobinJustRace() throws PersistenceLayerException
	{
		runRoundRobin("Familiar|Lion");
	}

	@Test
	public void testRoundRobinTwoRace() throws PersistenceLayerException
	{
		runRoundRobin("Familiar|Lion,Tiger");
	}

	@Test
	public void testRoundRobinFA() throws PersistenceLayerException
	{
		runRoundRobin("Familiar|Lion|FOLLOWERADJUSTMENT:-4");
	}

	@Test
	public void testRoundRobinTwoFA() throws PersistenceLayerException
	{
		runRoundRobin("Familiar|Lion|FOLLOWERADJUSTMENT:-4",
			"Familiar|Tiger|FOLLOWERADJUSTMENT:-5");
	}

	@Test
	public void testRoundRobinTwoType() throws PersistenceLayerException
	{
		runRoundRobin("Companion|Lion|FOLLOWERADJUSTMENT:-5",
			"Familiar|Tiger|FOLLOWERADJUSTMENT:-5");
	}

	@Test
	public void testRoundRobinComplex() throws PersistenceLayerException
	{
		runRoundRobin("Familiar|Lion,Tiger|FOLLOWERADJUSTMENT:-3|!PRECLASS:1,Cleric=1|PRERACE:1,Human");
	}

	@Test
	public void testRoundRobinTwoPRE() throws PersistenceLayerException
	{
		runRoundRobin("Familiar|Lion|FOLLOWERADJUSTMENT:-5",
			"Familiar|Tiger|FOLLOWERADJUSTMENT:-5|PRERACE:1,Human");
	}
}