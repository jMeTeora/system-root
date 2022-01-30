package jmeteora.system.apiutils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import jmeteora.system.apidata.Paths;

public class DistributionUtils {

	enum DistributionType {
		DEBIAN, ARCH, FEDORA
	}

	public static void getType() throws IOException {
		if (Paths.OSRELEASE.exists()) {
			List<String> data = Files.readAllLines(Paths.OSRELEASE.toPath(), StandardCharsets.UTF_8);
			for (String string : data) {
				System.out.println("DistributionUtils.getType():" + string);
			}
		}
	}
}
