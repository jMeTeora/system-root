package jmeteora.system.systemd.data.slice;

import jmeteora.system.apidata.exceptions.ObjectTypeException;
import jmeteora.system.systemd.data.SystemdElement;
import jmeteora.system.systemd.utils.ServiceUtils;

public class SLICE extends SystemdElement {
	@Override
	public SLICE fileName(String name) throws IllegalArgumentException, ObjectTypeException {
		if (!ServiceUtils.getElementType(name).equals(ELEMENTTYPE.SLICE)) {
			throw new ObjectTypeException(
					String.format("Не удалось создать объект типа ELEMENTTYPE.SLICE для [%s]", name));
		}
		setElementType(ELEMENTTYPE.SLICE);
		setFileName(name);
		return this;
	}

}
