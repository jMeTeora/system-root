package jmeteora.system.apiutils.apps.prog.impl;

import jmeteora.system.apiutils.DistributionUtils;
import jmeteora.system.apiutils.DistributionUtils.DistributionType;
import jmeteora.system.apiutils.apps.prog.Programm;

public class PAM extends Programm {
	@Override
	public String getInstallNameString() throws Exception {
		DistributionType type = DistributionUtils.getType();
		if (type == DistributionType.ARCH) {
			String name = "pambase pam";
			return new SELinuxProg().resolveNameForSELinuxIfExistForArch(name);
		} else {
			throw new UnsupportedOperationException(
					String.format("getInstallNameString for (%s.java:1) ", getClass().getSimpleName()));
		}

	}

}
