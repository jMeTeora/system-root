package jmeteora.system.apiutils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import jmeteora.system.apidata.Paths;

public class DistributionUtils {
	public enum DistributionType {
		DEBIAN, ARCH, FEDORA
	}

	public static DistributionType getType() throws IOException {

		String id = null;
		if (Paths.OSRELEASE.exists()) {
			List<String> data = Files.readAllLines(Paths.OSRELEASE.toPath(), StandardCharsets.UTF_8);

			for (String string : data) {
				String[] pair = string.split("=");
				String key = pair[0];
				String value = pair[1];
				if (key.equalsIgnoreCase("id")) {
					id = value;
					try {
						return DistributionType.valueOf(value.toUpperCase());
					} catch (Exception e) {
						throw new UnsupportedOperationException(String.format(
								"Unsupported OS with /etc/os-release ID=%s (not exist in DistributionUtils.DistributionType)",
								id), e);
					}
				}
			}
		}
		throw new UnsupportedOperationException(String.format("Unsupported OS with /etc/os-release ID=%s", id));
	}
}
