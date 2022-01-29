package jmeteora.system.apiutils.repo;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jmeteora.system.apidata.Paths;
import jmeteora.system.apidata.languagepack.LanguagePack;
import jmeteora.system.apidata.languagepack.LanguagePackKeys;
import jmeteora.system.apidata.systemd.data.ServicesDemoManual;
import jmeteora.system.apiutils.ServicesOwn;
import jmeteora.system.apiutils.os.ExecutorMaster;
import jmeteora.system.apiutils.os.OsUtils;
import jmeteora.system.apiutils.repo.git.GitHelper;
import jmeteora.system.apiutils.repo.maven.MavenHelper;

public class RepoUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger("RepoUtils");

	public static void downloadAndBuild() throws Exception {
		GitHelper.updateRepo();
		boolean builded = MavenHelper.buildRepo(Paths.repoDir);
		if (builded) {
			// maven

			// move files
			LOGGER.info("Обновляем файл программы обновления");
			RepoFiles.movedExecsJarsToPersDir();
			// move files

			// services
			File servicesDir = ServicesOwn.findServicesDir();

			String updaterServiceFileName = "jmeteora-system-updater";

			String msg = LanguagePack.getString(LanguagePackKeys.FOUNDPATHTOSERVICE);
			LOGGER.info("{} {}", msg, servicesDir);

			File servicesFile = new File(servicesDir, updaterServiceFileName + ".service");

			if (!servicesFile.exists() && servicesFile.createNewFile()) {
				LOGGER.info("file {} created", servicesFile);
			}
			Files.writeString(servicesFile.toPath(), ServicesDemoManual.UPDATERSERVICEDATA,
					StandardOpenOption.TRUNCATE_EXISTING);
			LOGGER.info("Записано");
			// services

			// shell
			LOGGER.info("Получаем shell...");
			String shell = OsUtils.getSystemShell();
			LOGGER.info("shell получен:[{}]", shell);
			// shell

			// restart
			LOGGER.info("перезапускаем в systemd...");
			String cmd1 = "systemctl daemon-reload";
			String cmd2 = "systemctl enable " + updaterServiceFileName;
			String cmd3 = "systemctl restart " + updaterServiceFileName;
			new ExecutorMaster().parrentCommand(shell).dir(Paths.repoDir).command(cmd1).call();
			new ExecutorMaster().parrentCommand(shell).dir(Paths.repoDir).command(cmd2).call();
			new ExecutorMaster().parrentCommand(shell).dir(Paths.repoDir).command(cmd3).call();
			LOGGER.info("перезапустили");
			// restart
		}

	}

	public static void fullCase() throws Exception {

		// git
		String msg1 = LanguagePack.getString(LanguagePackKeys.FOUNDPATHTOSERVICE);
		LOGGER.info(msg1);
		boolean stat = GitHelper.updateRepo();
		LOGGER.info(LanguagePack.getString(LanguagePackKeys.GITREPOUPDATED));
		// git
		if (stat) {
			// maven
			boolean builded = MavenHelper.buildRepo(Paths.repoDir);
			if (builded) {
				// maven

				// move files
				LOGGER.info(LanguagePack.getString(LanguagePackKeys.UPDATINGUPDATERFILE));
				RepoFiles.movedExecsJarsToPersDir();
				// move files

				// services
				File servicesDir = ServicesOwn.findServicesDir();

				String updaterServiceFileName = "jmeteora-system-updater";
				LOGGER.info("Найден путь до сервисов {}", servicesDir);
				Files.writeString(new File(servicesDir, updaterServiceFileName + ".service").toPath(),
						ServicesDemoManual.UPDATERSERVICEDATA, StandardOpenOption.TRUNCATE_EXISTING);
				LOGGER.info("Записано");
				// services

				// shell
				LOGGER.info("Получаем shell...");
				String shell = OsUtils.getSystemShell();
				LOGGER.info("shell получен:[{}]", shell);
				// shell

				// restart
				LOGGER.info("перезапускаем в systemd...");
				String cmd1 = "systemctl daemon-reload";
				String cmd2 = "systemctl enable " + updaterServiceFileName;
				String cmd3 = "systemctl restart " + updaterServiceFileName;
				new ExecutorMaster().parrentCommand(shell).dir(Paths.repoDir).command(cmd1).call();
				new ExecutorMaster().parrentCommand(shell).dir(Paths.repoDir).command(cmd2).call();
				new ExecutorMaster().parrentCommand(shell).dir(Paths.repoDir).command(cmd3).call();
				LOGGER.info("перезапустили");
				// restart
			}
		}
	}

	private RepoUtils() {
	}

}
