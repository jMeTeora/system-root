package jmeteora.system.apiutils.apps.manager;

import java.io.IOException;

import org.apache.commons.lang3.SystemUtils;
import org.tukaani.xz.UnsupportedOptionsException;

import jmeteora.system.apiutils.DistributionUtils;
import jmeteora.system.apiutils.DistributionUtils.DistributionType;
import jmeteora.system.apiutils.apps.manager.impl.APT;
import jmeteora.system.apiutils.apps.manager.impl.Pacman;

public final class ProgrammManagerResolver {
	private ProgrammManagerResolver() {
	}

	static ProgrammManager instance = null;

	public static ProgrammManager resolve() throws IOException {
		if (instance == null) {
			if (SystemUtils.IS_OS_LINUX) {
				DistributionType distType = DistributionUtils.getType();
				if (distType == DistributionType.DEBIAN) {
					instance = new APT();
					return instance;
				} else if (distType == DistributionType.ARCH) {
					instance = new Pacman();
					return instance;
				}
			} else {
				throw new UnsupportedOptionsException(String.format("Unsupported OS:%s", SystemUtils.OS_NAME));
			}
			instance = new ProgrammManager();
		}
		return instance;
	}
}
