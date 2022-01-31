package jmeteora.system.apiutils.apps.manager;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

import jmeteora.system.apiutils.apps.prog.Programm;
import jmeteora.system.apiutils.os.ExecutorMaster;
import jmeteora.system.apiutils.os.ExecutorMasterOutputListener;

public class ProgrammManager implements ProgrammManagerInterface {

	private static final String INSTALLER = "installer";

	@Override
	public boolean install(List<Programm> dependencies) throws Exception {
		for (Programm programm : dependencies) {
			if (!install(programm)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean install(Programm programm) throws Exception {
		String mngr = getClass().getSimpleName() + " " + INSTALLER;
		ExecutorMaster executorMaster = new ExecutorMaster();
		executorMaster.setOutputListener(new ExecutorMasterOutputListener() {

			@Override
			public void startedProcess(Long pid) {
				LoggerFactory.getLogger(mngr).info("start installer for {} with pid {}",
						programm.getClass().getSimpleName(), pid);
			}

			@Override
			public void appendOutput(String line) throws Exception {
				LoggerFactory.getLogger(mngr).info("out {}", line);
			}

			@Override
			public void appendInput(String line) {
				LoggerFactory.getLogger(mngr).info("install {}", line);
			}
		});
		executorMaster.parrentCommand("bash");
		executorMaster.command(
				String.format("sudo -u root %s%s%s", getPath(), getInstallCmd(), programm.getInstallNameString()));
		executorMaster.call();

		return true;
	}

	@Override
	public String getInstallCmd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPath() throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Programm programm) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<String> resolveDB() throws Exception {
		throw new UnsupportedOperationException(
				String.format("impl resolveDB for (%s.java:1) ", getClass().getSimpleName()));
	}

}
