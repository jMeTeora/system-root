package jmeteora.system.apiutils.apps.prog.impl;

import jmeteora.system.apiutils.apps.manager.ProgrammManager;
import jmeteora.system.apiutils.apps.prog.Programm;

public class Netstat extends Programm {

	@Override
	public boolean install() throws Exception {
		return ProgrammManager.getInstance().install(this);
	}

	@Override
	public boolean detect() {
		return false;
	}

	@Override
	public boolean update() {
		return false;
	}

}
