/*
 * Created on Sep 2, 2005
 *
 */
package plugin.lsttokens;

import pcgen.core.PObject;
import pcgen.persistence.lst.GlobalLstToken;

/**
 * @author djones4
 *
 */
public class NameispiLst implements GlobalLstToken {

	public String getTokenName() {
		return "NAMEISPI";
	}

	public boolean parse(PObject obj, String value, int anInt) {
		obj.setNameIsPI(value.startsWith("Y"));
		return true;
	}
}

