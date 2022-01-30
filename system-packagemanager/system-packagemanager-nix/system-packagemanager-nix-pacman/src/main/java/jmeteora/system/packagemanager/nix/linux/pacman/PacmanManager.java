package jmeteora.system.packagemanager.nix.linux.pacman;

import jmeteora.system.packagemanager.data.ProgrammImpl;

public class PacmanManager {
	private static PacmanManager manager;

	public static PacmanManager getInstance() {
		if (manager == null) {
			manager = new PacmanManager();
		}
		return manager;
	}

	private PacmanManager() {
	}

	public void install(ProgrammImpl programm) {

	}
}
