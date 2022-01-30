package jmeteora.system.systemd.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jmeteora.system.systemd.data.SystemdElement;
import jmeteora.system.systemd.data.sections.impl.InstallSection;
import jmeteora.system.systemd.data.sections.impl.ServiceSection;
import jmeteora.system.systemd.data.sections.impl.UnitSection;
import jmeteora.system.systemd.data.service.Service;

public class ServiceDemoKernel {
	private static final Logger LOGGER = LoggerFactory.getLogger("ServiceDemo");

	public static void main(String[] args) {
		try {
			Service kernelService = new Service();

			UnitSection unitSection = kernelService.getOrCreateUnitSection();
			ServiceSection serviceSection = kernelService.getServiceSection();
			InstallSection installSection = kernelService.getInstallSection();

			// UNIT
			unitSection.appendAfter(new SystemdElement().fileName("syslog.target"));
			unitSection.appendAfter(new SystemdElement().fileName("network.target"));
			unitSection.appendAfter(new SystemdElement().fileName("nss-lookup.target"));

			// SERVICE
			// INSTALL

			String data = kernelService.toServiceFile();
			LOGGER.info("\n{}", data);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
