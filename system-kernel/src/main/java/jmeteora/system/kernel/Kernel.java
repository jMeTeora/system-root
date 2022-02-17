package jmeteora.system.kernel;

import java.io.IOException;

import jmeteora.kernel.sec.selinux.server.SELinuxServer;
import jmeteora.system.kernel.kernelservice.ProcessHandler;
import jmeteora.system.kernel.kernelservice.Watchdog;

public class Kernel extends Thread {
	Watchdog watchdog = new Watchdog();
	ProcessHandler processHandler = new ProcessHandler();

	boolean active = true;

	private final SELinuxServer seLinuxServer = new SELinuxServer();

	public void init() throws InterruptedException, IOException {
		watchdog.register(this);
		watchdog.register(processHandler);

		// systemd-journalctl
		// audit
		// SELinux
		seLinuxServer.bind();

	}

	@Override
	public void run() {
		try {
			Thread.currentThread().setName("Kernel");
			processHandler.start();
			watchdog.start();
			seLinuxServer.start();
			do {

				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} while (active);
			watchdog.unRegister(this);
			watchdog.unRegisterAll();
			watchdog.stopAll();
			watchdog.stopSelf();
			watchdog.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
