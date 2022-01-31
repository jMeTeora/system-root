package jmeteora.system.apiutils.apps.prog.impl;

import java.io.IOException;

import jmeteora.system.apiutils.DistributionUtils;
import jmeteora.system.apiutils.DistributionUtils.DistributionType;
import jmeteora.system.apiutils.apps.manager.ProgrammManagerResolver;
import jmeteora.system.apiutils.apps.prog.Programm;

public class Netstat extends Programm {

	@Override
	public boolean install() throws Exception {
		return ProgrammManagerResolver.resolve().install(this);
	}

	@Override
	public String getInstallNameString() throws IOException {
		DistributionType type = DistributionUtils.getType();
		if (type == DistributionType.ARCH) {
			return "net-tools";
		}
		throw new UnsupportedOperationException(
				String.format("Programm.%s.getInstallNameString():%s", getClass().getSimpleName(), type));
	}

}
