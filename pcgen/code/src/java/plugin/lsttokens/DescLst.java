/*
 * Created on Aug 23, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package plugin.lsttokens;

import pcgen.core.PObject;
import pcgen.io.EntityEncoder;
import pcgen.persistence.lst.GlobalLstToken;

/**
 * @author djones4
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DescLst implements GlobalLstToken {

	public String getTokenName() {
		return "DESC";
	}

	public boolean parse(PObject obj, String value, int anInt) {
		obj.setDescription(EntityEncoder.decode(value));
		return true;
	}
}

