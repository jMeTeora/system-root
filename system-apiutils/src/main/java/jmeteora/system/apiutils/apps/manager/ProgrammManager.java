package jmeteora.system.apiutils.apps.manager;

import java.util.List;

import org.apache.commons.lang3.SystemUtils;
import org.tukaani.xz.UnsupportedOptionsException;

import jmeteora.system.apiutils.DistributionUtils;
import jmeteora.system.apiutils.apps.prog.Programm;

public class ProgrammManager {
	private ProgrammManager() {
	}

	public static ProgrammManager getInstance() throws UnsupportedOptionsException {
		if (SystemUtils.IS_OS_WINDOWS) {

		} else if (SystemUtils.IS_OS_LINUX) {
			DistributionUtils.getType();

		} else {
			throw new UnsupportedOptionsException("Unsupported OS");
		}

		return new ProgrammManager();
	}

	public void install(List<Programm> dependencies) {

	}
}
