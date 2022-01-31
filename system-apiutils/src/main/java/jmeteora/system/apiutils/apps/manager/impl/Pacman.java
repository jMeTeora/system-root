package jmeteora.system.apiutils.apps.manager.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import jmeteora.system.apiutils.apps.manager.ProgrammManager;
import jmeteora.system.apiutils.apps.prog.Programm;
import jmeteora.system.apiutils.os.OsUtils;

public class Pacman extends ProgrammManager {
	@Override
	public String getPath() throws FileNotFoundException {
		return OsUtils.findProgInSys("pacman");
	}

	@Override
	public String getInstallCmd() {
		return " -Syy --needed --noconfirm ";
	}

	@Override
	public boolean update(Programm programm) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<String> resolveDB() throws Exception {
		ArrayList<String> data = new ArrayList<>();
		Process a = Runtime.getRuntime().exec(getPath() + " -Ssq");
		BufferedReader reader = new BufferedReader(a.inputReader());
		String line;
		while ((line = reader.readLine()) != null) {
			data.add(line);
		}
		return data;
	}

}
