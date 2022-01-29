package jmeteora.system.apiutils;

import java.io.File;
import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jmeteora.system.apidata.Paths;

public class ServicesOwn {

	public static final Logger LOGGER = LoggerFactory.getLogger("ServicesOwn");

	public static File findServicesDir() throws FileNotFoundException {
		if (Paths.systemdDir1.exists()) {
			return Paths.systemdDir1;
		} else if (Paths.systemdDir2.exists()) {
			return Paths.systemdDir2;
		} else {
			throw new FileNotFoundException("Не найден путь до сервисов");
		}
	}
}
