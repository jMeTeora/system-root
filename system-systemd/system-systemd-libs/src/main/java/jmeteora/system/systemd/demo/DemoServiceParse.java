package jmeteora.system.systemd.demo;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jmeteora.system.apiutils.ServicesOwn;
import jmeteora.system.systemd.data.SystemdElement;
import jmeteora.system.systemd.utils.ServiceUtils;

public class DemoServiceParse {
	private static File logFile = new File("demo_debug_log.txt");

	interface DemoServiceParseListener {
		void appendDir(File dir) throws Exception;

		void appendFile(File file) throws Exception;
	}

	interface FindFileListner {

		boolean checkFile(String what, File where);

		File findInDir(String what, File where);
	}

	static boolean withCatch = true;
	static DemoServiceParseListener listener = new DemoServiceParseListener() {

		@Override
		public void appendDir(File dir) throws Exception {
			if (dir == null) {
				throw new IllegalArgumentException("Проверть входные данные (каталог)");
			}
			File[] files = dir.listFiles();
			if (files == null) {
				throw new IllegalArgumentException(String.format("Ваш каталог %s пуст ", dir));
			}

			for (File file : files) {
				if (file.isFile()) {
					if (withCatch) {
						try {
							listener.appendFile(file);
						} catch (Exception e) {
							// System.err.println(JVMUtils.getFullStackTraceAsString(true, 0, e) + '\n');
							e.printStackTrace();
							if (!logFile.exists()) {
								logFile.createNewFile();
							}
							Files.writeString(logFile.toPath(), (e.getCause().toString() + "   " + e.toString() + '\n'),
									StandardOpenOption.APPEND);
						}
					} else {
						listener.appendFile(file);
					}
				} else if (file.isDirectory()) {
					listener.appendDir(file);
				}
			}
		}

		@Override
		public void appendFile(File file) throws Exception {
			elements.add(ServiceUtils.parseFile(file));
		}

	};

	static CopyOnWriteArrayList<SystemdElement> elements = new CopyOnWriteArrayList<>();
	private static final Logger LOGGER = LoggerFactory.getLogger("DemoServiceParse");

	static FindFileListner findFileListner = new FindFileListner() {

		@Override
		public boolean checkFile(String what, File where) {
			return where.getName().equalsIgnoreCase(what);
		}

		@Override
		public File findInDir(String what, File where) {
			if (where.isFile() && checkFile(what, where)) {
				return where;
			}
			if (where.isDirectory()) {
				File[] list = where.listFiles();
				if (list != null) {
					for (File file : list) {
						File test = findInDir(what, file);
						if (test != null) {
							return test;
						}
					}
				}
			}
			return null;
		}

	};

	public static void main(String[] args) {
		try {
			File serviceDir = ServicesOwn.findServicesDir();
			boolean fullParseOrTor = true;
			if (fullParseOrTor) {
				listener.appendDir(serviceDir);
			} else {
				File torServiceFile = new File("/etc/systemd/system/multi-user.target.wants/tor.service");

				LOGGER.info("{}", torServiceFile);
				listener.appendFile(torServiceFile);
			}

			boolean deb = false;
			if (deb) {
				for (SystemdElement element : elements) {
					LOGGER.info("-------------------------{}-------------------------------", element.getFileName());
					LOGGER.info("{} \n{}", element.getClass(), element.toServiceFile());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
