package jmeteora.system.systemd.data.sections.impl;

import jmeteora.system.systemd.data.sections.Section;

public class SocketSection extends Section {
	@Override
	public SECTIONNAME getName() {
		return SECTIONNAME.SOCKET;
	}

}
