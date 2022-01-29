package jmeteora.system.systemd.data.service;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jmeteora.system.systemd.data.SystemdElement;

@SuppressFBWarnings(value = { "EI_EXPOSE_REP", "EI_EXPOSE_REP2" })
public class Service extends SystemdElement {

	@Override
	public String toServiceFile() {
		StringBuilder result = new StringBuilder();
		if (getUnitSection() != null) {
			result.append(getUnitSection().toServiceFile()).append('\n');
		}
		if (getServiceSection() != null) {
			result.append(getServiceSection().toServiceFile()).append('\n');
		}
		if (getInstallSection() != null) {
			result.append(getInstallSection().toServiceFile()).append('\n');
		}
		return result.toString();
	}

}
