package jmeteora.system.kernel.init;

import java.io.IOException;

import jmeteora.system.kernel.Kernel;

public class KernelInit {

	public static void main(String[] args) throws InterruptedException, IOException {
		Kernel kernel = new Kernel();
		kernel.init();
		kernel.start();
	}
}
