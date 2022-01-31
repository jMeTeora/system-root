package jmeteora.system.apiutils.apps.prog;

import java.util.ArrayList;

import org.apache.commons.lang3.SystemUtils;

import jmeteora.system.apiutils.apps.manager.ProgrammManagerResolver;

public abstract class Programm implements ProgrammInterface {
	@Override
	public String getInstallNameString() throws Exception {
		throw new UnsupportedOperationException(
				String.format("Programm.%s.getInstallNameString()", getClass().getSimpleName()));
	}

	@Override
	public boolean install() throws Exception {
		throw new UnsupportedOperationException(String.format("Programm.%s.install()", getClass().getSimpleName()));

	}

	@Override
	public boolean detect() throws Exception {
		try {
			ArrayList<String> db = ProgrammManagerResolver.resolve().resolveDB();
			if (SystemUtils.IS_OS_LINUX) {
				for (String string : db) {
					if (string.equalsIgnoreCase(getInstallNameString())) {
						System.out.println("Programm.detect():" + string);
						return true;
					}
				}
				return false;
			}
		} catch (Exception e) {
			String msg = String.format("Programm.%s.detect()", getClass().getSimpleName());
			throw new UnsupportedOperationException(msg, e);
		}
		return false;
		// throw new UnsupportedOperationException(String.format("Programm.%s.detect()",
		// getClass().getSimpleName()));

	}

	@Override
	public boolean update() throws Exception {
		throw new UnsupportedOperationException(String.format("Programm.%s.update()", getClass().getSimpleName()));
	}
}
