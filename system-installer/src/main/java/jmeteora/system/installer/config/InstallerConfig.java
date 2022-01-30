package jmeteora.system.installer.config;

import static jmeteora.system.apidata.DataSets.Chars.ALPHA;
import static jmeteora.system.apidata.DataSets.Chars.ALPHA_CAPS;
import static jmeteora.system.apidata.DataSets.Chars.SPECIAL_CHARS;
import static jmeteora.system.apidata.DataSets.VERSIONTYPE.MAIN;

import java.security.SecureRandom;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jmeteora.system.apidata.DataSets;
import jmeteora.system.installer.init.InstallerInit;
import lombok.Getter;
import lombok.Setter;

public class InstallerConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger("InstallerConfig");

	private @Getter @Setter String dbHost = "127.0.0.1";
	private @Getter @Setter int dbPort = 5432;

	private @Getter @Setter String dbName = "jmeteoraSystem";
	private @Getter @Setter String dbUserName = "jmeteora";
	private @Getter @Setter String dbUserPassword = InstallerInit.generatePassword(gen(30, 60),
			ALPHA_CAPS + ALPHA + SPECIAL_CHARS);

	private @Getter @Setter DataSets.VERSIONTYPE version = MAIN;

	private @Getter @Setter boolean prepare;

	private int gen(int min, int max) {
		SecureRandom sr = new SecureRandom();
		int val = min + sr.nextInt() * ((max - min) + 1);
		LOGGER.info("from [{}] to [{}] val:[{}]", min, max, val);
		return val;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
