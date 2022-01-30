package jmeteora.system.apiutils.apps.prog.impl;

import java.io.IOException;

import jmeteora.system.apiutils.DistributionUtils;
import jmeteora.system.apiutils.apps.prog.Programm;

public class Postgresql extends Programm {
	public Postgresql() throws IOException {
		DistributionUtils.getType();
	}

	@Override
	public boolean install() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean detect() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
