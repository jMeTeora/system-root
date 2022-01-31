package jmeteora.system.apiutils.apps.prog.impl;

import jmeteora.system.apiutils.DistributionUtils;
import jmeteora.system.apiutils.DistributionUtils.DistributionType;
import jmeteora.system.apiutils.apps.prog.Programm;

public class Iproute2 extends Programm {

	@Override
	public boolean detect() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getInstallNameString() throws Exception {
		DistributionType type = DistributionUtils.getType();

		if (type == DistributionType.ARCH) {
			String baseName = "iproute2";
			return new SELinuxProg().resolveNameForSELinuxIfExistForArch(baseName);
		}
		return super.getInstallNameString();
	}

}
