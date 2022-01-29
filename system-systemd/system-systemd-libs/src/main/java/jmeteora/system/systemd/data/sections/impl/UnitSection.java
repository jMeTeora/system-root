package jmeteora.system.systemd.data.sections.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jmeteora.system.systemd.data.sections.Section;

@SuppressFBWarnings(value = { "EI_EXPOSE_REP", "EI_EXPOSE_REP2" })
public class UnitSection extends Section {

	@Override
	public SECTIONNAME getName() {
		return SECTIONNAME.UNIT;
	}
}
