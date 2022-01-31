package jmeteora.system.apiutils.apps.prog.impl;

import java.util.ArrayList;
import java.util.Optional;

import jmeteora.system.apiutils.apps.manager.ProgrammManagerResolver;
import jmeteora.system.apiutils.apps.prog.Programm;

public class SELinuxProg extends Programm {
	@Override
	public String getInstallNameString() throws Exception {
		return "selinux";
	}

	public Optional<String> detectByDB(String baseName, ArrayList<String> db) throws Exception {
		for (String string : db) {
			if (string.contains(baseName) && string.contains(getInstallNameString())) {
				return Optional.of(string);
			}
		}

		return Optional.empty();
	}

	public String resolveNameForSELinuxIfExistForArch(String name) throws Exception {
		String[] basenameAr = name.split(" ");
		StringBuilder pm = new StringBuilder();
		ArrayList<String> db = ProgrammManagerResolver.resolve().resolveDB();

		for (String baseName : basenameAr) {
			Optional<String> selinuxOptional = detectByDB(baseName, db);
			if (selinuxOptional.isPresent()) {
				pm.append(selinuxOptional.get());
				pm.append(' ');
			}
		}
		return pm.toString();
	}

}
