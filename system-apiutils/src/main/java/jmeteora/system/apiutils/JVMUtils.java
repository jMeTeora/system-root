package jmeteora.system.apiutils;

public class JVMUtils {

	public static String getFullStackTraceAsString(boolean end, int appendTabPre, Throwable throwable) {
		StringBuilder ret = new StringBuilder();
		StackTraceElement[] st = throwable.getStackTrace();

		if (appendTabPre >= 0) {
			ret.append('\n');
			ret.append("\t".repeat(appendTabPre));
		}
		if (!end) {
			ret.append(String.format("MSG:[%s] st: ", throwable.getMessage()));
		}
		for (int i = st.length - 1; i >= 0; i--) {
			StackTraceElement stackTraceElement = st[i];
			ret.append(stackTraceElement.getClassName());
			ret.append('.');
			ret.append(stackTraceElement.getMethodName());
			ret.append('(');
			ret.append(stackTraceElement.getFileName());
			ret.append(':');
			ret.append(stackTraceElement.getLineNumber());
			ret.append(')');
			if (i != 0) {
				ret.append(" -> ");
			}
		}
		if (end) {
			ret.append(String.format(" MSG:[%s]", throwable.getMessage()));
		}
		if (throwable.getCause() != null) {
			ret.append(getFullStackTraceAsString(end, appendTabPre + 1, throwable.getCause()));
		}
		return ret.toString();
	}

}
