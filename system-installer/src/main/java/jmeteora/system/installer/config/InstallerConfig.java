package jmeteora.system.installer.config;

import static jmeteora.system.apidata.DataSets.Chars.ALPHA;
import static jmeteora.system.apidata.DataSets.Chars.ALPHA_CAPS;
import static jmeteora.system.apidata.DataSets.Chars.SPECIAL_CHARS;
import static jmeteora.system.apidata.DataSets.VERSIONTYPE.MAIN;

import java.security.SecureRandom;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import jmeteora.system.apidata.DataSets;
import jmeteora.system.installer.init.InstallerInit;
import lombok.Getter;
import lombok.Setter;

public class InstallerConfig {
	private static final SecureRandom secureRandomObj = new SecureRandom();

	private @Getter @Setter String dbHost = "127.0.0.1";
	private @Getter @Setter int dbPort = 5432;

	private @Getter @Setter String dbName = "jmeteoraSystem";
	private @Getter @Setter String dbUserName = "jmeteora";
	private @Getter @Setter String dbUserPassword = InstallerInit.generatePassword(randomIntInRange(30, 60),
			ALPHA_CAPS + ALPHA + SPECIAL_CHARS);

	private @Getter @Setter DataSets.VERSIONTYPE version = MAIN;

	private @Getter @Setter boolean allBranches;
	private @Getter @Setter boolean prepare;

	public static int randomIntInRange(int min, int max) {
		return secureRandomObj.nextInt((max - min) + 1) + min;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
