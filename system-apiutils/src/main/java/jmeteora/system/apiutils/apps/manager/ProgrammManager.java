package jmeteora.system.apiutils.apps.manager;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.SystemUtils;
import org.slf4j.LoggerFactory;
import org.tukaani.xz.UnsupportedOptionsException;

import jmeteora.system.apiutils.DistributionUtils;
import jmeteora.system.apiutils.apps.prog.Programm;
import jmeteora.system.apiutils.os.ExecutorMaster;
import jmeteora.system.apiutils.os.ExecutorMasterOutputListener;
import lombok.Getter;

public class ProgrammManager {
	static {
		try {
			getInstance();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ProgrammManager() {

	}

	private @Getter Bin bin = new Bin();

	class Bin {

		public String getpath() {
			// TODO Auto-generated method stub
			return null;
		}

		public Object getInstallCmd() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	static ProgrammManager instance;

	public static ProgrammManager getInstance() throws IOException {
		if (instance == null) {
			if (SystemUtils.IS_OS_WINDOWS) {
				// TODO
			} else if (SystemUtils.IS_OS_LINUX) {
				DistributionUtils.getType();

			} else {
				throw new UnsupportedOptionsException("Unsupported OS");
			}
			instance = new ProgrammManager();
		}
		return instance;
	}

	public boolean install(List<Programm> dependencies) throws Exception {
		for (Programm programm : dependencies) {
			if (!install(programm)) {
				return false;
			}
		}
		return true;
	}

	public boolean install(Programm programm) throws Exception {
		ExecutorMaster executorMaster = new ExecutorMaster();
		executorMaster.setOutputListener(new ExecutorMasterOutputListener() {

			@Override
			public void startedProcess(Long pid) {
			}

			@Override
			public void appendOutput(String line) throws Exception {
				LoggerFactory.getLogger("installer").info("out {}", line);
			}

			@Override
			public void appendInput(String line) {
				LoggerFactory.getLogger("installer").info("install {}", line);
			}
		});
		executorMaster.parrentCommand("bash");
		executorMaster.command(
				String.format("echo '%s %s %s'", bin.getpath(), bin.getInstallCmd(), programm.getInstallNameString()));
		executorMaster.call();

		return true;
	}

}
