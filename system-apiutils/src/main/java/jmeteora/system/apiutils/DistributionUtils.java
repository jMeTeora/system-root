package jmeteora.system.apiutils;

import jmeteora.system.apidata.Paths;

public class DistributionUtils {
	static {
		System.out.println("DistributionUtils.enclosing_method():" + Paths.OSRELEASE);

	}

	enum DistributionType {
		DEBIAN, ARCH, FEDORA
	}

	public static void getType() {
		// TODO Auto-generated method stub

	}
}
