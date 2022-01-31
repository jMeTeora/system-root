package jmeteora.system.apiutils.apps.prog.impl;

import java.io.IOException;

import jmeteora.system.apiutils.DistributionUtils;
import jmeteora.system.apiutils.DistributionUtils.DistributionType;
import jmeteora.system.apiutils.apps.manager.ProgrammManagerResolver;
import jmeteora.system.apiutils.apps.prog.Programm;

public class Postgresql extends Programm {

	@Override
	public boolean install() throws Exception {
		return ProgrammManagerResolver.resolve().install(this);
	}

	@Override
	public boolean detect() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update() throws Exception {
		return ProgrammManagerResolver.resolve().update(this);
	}

	@Override
	public String getInstallNameString() throws IOException {
		DistributionType type = DistributionUtils.getType();
		if (type == DistributionType.ARCH) {
			return "postgresql-old-upgrade postgresql-libs postgresql";
		}
		throw new UnsupportedOperationException(
				String.format("Programm.%s.getInstallNameString():%s", getClass().getSimpleName(), type));
	}

}
