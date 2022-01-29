package jmeteora.system.packagemanager.data;

public interface PackageInterface {
	String getName();

	String getVersion();

	boolean install();

	boolean unInstall();

	boolean update();
}
