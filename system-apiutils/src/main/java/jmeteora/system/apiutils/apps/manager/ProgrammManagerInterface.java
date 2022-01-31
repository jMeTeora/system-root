package jmeteora.system.apiutils.apps.manager;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import jmeteora.system.apiutils.apps.prog.Programm;

public interface ProgrammManagerInterface {
//	ProgrammManager getInstance() throws IOException;

	String getInstallCmd();

	String getPath() throws FileNotFoundException;

	boolean install(Programm programm) throws Exception;

	boolean install(List<Programm> programms) throws Exception;

	boolean update(Programm programm) throws Exception;

	ArrayList<String> resolveDB() throws Exception;
}
