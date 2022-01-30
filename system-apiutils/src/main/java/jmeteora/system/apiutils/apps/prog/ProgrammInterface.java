package jmeteora.system.apiutils.apps.prog;

public interface ProgrammInterface {
	boolean install() throws Exception;

	boolean detect() throws Exception;

	boolean update() throws Exception;

}
