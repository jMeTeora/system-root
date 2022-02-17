package jmeteora.kernel.sec.selinux.server.utils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jmeteora.system.apiutils.os.ExecutorMaster;
import jmeteora.system.apiutils.os.OsUtils;

public class SELinuxUtils {
	static File script = new File("/tmp/jmeteora/scripts/sec/selinux/audit2allowAREV");
	static File scriptOut = new File("/tmp/jmeteora/scripts/sec/selinux/audit2allowAREV_Out");

	private static final Logger LOGGER = LoggerFactory.getLogger("SELinuxUtils");

	public static void audit2allowAREV() throws Exception {
		if (!script.exists()) {
			script.getParentFile().mkdirs();
			Files.createFile(script.toPath());
			Files.writeString(script.toPath(),
					"cat /var/log/audit/audit.log* |audit2allow -rev > " + scriptOut.getAbsolutePath(),
					StandardOpenOption.TRUNCATE_EXISTING);
			Set<PosixFilePermission> perms = Files.getPosixFilePermissions(script.toPath());
			perms.clear();
			perms.add(PosixFilePermission.OWNER_READ);
			perms.add(PosixFilePermission.OWNER_WRITE);
			perms.add(PosixFilePermission.OWNER_EXECUTE);
			Files.setPosixFilePermissions(script.toPath(), perms);
		}
		//

		String shell = OsUtils.getSystemShell();
		String audit2allow = OsUtils.findProgInSys("audit2allow") + "    -reva";

		ExecutorMaster executorMaster = new ExecutorMaster();
		executorMaster.parrentCommand(shell);
		executorMaster.command(script.getAbsolutePath() + " &&exit 0");

		int exitStatus = executorMaster.call();

		System.out.println("SELinuxUtils.audit2allowAREVA():" + exitStatus);

		for (String line : Files.readAllLines(scriptOut.toPath(), StandardCharsets.UTF_8)) {
			System.err.println(line);
		}
		Files.deleteIfExists(scriptOut.toPath());
		//
		Runtime.getRuntime().exit(0);
	}

}
