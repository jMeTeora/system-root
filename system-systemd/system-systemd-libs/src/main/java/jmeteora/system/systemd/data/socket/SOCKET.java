package jmeteora.system.systemd.data.socket;

import jmeteora.system.systemd.data.SystemdElement;

public class SOCKET extends SystemdElement {
	@Override
	public SECTIONNAME getName() {
		return SECTIONNAME.SOCKET;
	}
}
