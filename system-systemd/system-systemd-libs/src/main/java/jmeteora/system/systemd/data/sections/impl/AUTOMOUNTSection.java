package jmeteora.system.systemd.data.sections.impl;

import jmeteora.system.systemd.data.sections.Section;

public class AUTOMOUNTSection extends Section {
	@Override
	public SECTIONNAME getName() {
		return SECTIONNAME.AUTOMOUNT;
	}
}
