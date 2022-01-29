package jmeteora.system.systemd.data.sections.impl;

import jmeteora.system.systemd.data.sections.Section;

public class TimerSection extends Section {
	@Override
	public SECTIONNAME getName() {
		return SECTIONNAME.TIMER;
	}

}
