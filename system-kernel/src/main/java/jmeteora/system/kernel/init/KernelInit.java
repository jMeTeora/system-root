package jmeteora.system.kernel.init;

import jmeteora.system.kernel.Kernel;

public class KernelInit {

	public static void main(String[] args) {
		Kernel kernel = new Kernel();
		kernel.init();
		kernel.start();
	}
}
