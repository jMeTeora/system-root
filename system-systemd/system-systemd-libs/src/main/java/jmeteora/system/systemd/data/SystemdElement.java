package jmeteora.system.systemd.data;

import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jmeteora.system.apidata.exceptions.ObjectTypeException;
import jmeteora.system.systemd.data.sections.impl.AUTOMOUNTSection;
import jmeteora.system.systemd.data.sections.impl.InstallSection;
import jmeteora.system.systemd.data.sections.impl.MOUNTSection;
import jmeteora.system.systemd.data.sections.impl.PathSection;
import jmeteora.system.systemd.data.sections.impl.ServiceSection;
import jmeteora.system.systemd.data.sections.impl.SliceSection;
import jmeteora.system.systemd.data.sections.impl.SocketSection;
import jmeteora.system.systemd.data.sections.impl.TimerSection;
import jmeteora.system.systemd.data.sections.impl.UnitSection;
import jmeteora.system.systemd.utils.ServiceUtils;
import lombok.Getter;
import lombok.Setter;

@SuppressFBWarnings(value = { "EI_EXPOSE_REP", "EI_EXPOSE_REP2" })
public class SystemdElement {

	public enum CAPs {
		CAP_SYS_ADMIN, CAP_MAC_ADMIN, CAP_AUDIT_CONTROL, CAP_SYS_RAWIO, CAP_CHOWN, CAP_DAC_READ_SEARCH,
		CAP_DAC_OVERRIDE, CAP_FOWNER, CAP_SYS_TTY_CONFIG, CAP_LINUX_IMMUTABLE, CAP_SYS_PTRACE, CAP_SYSLOG,
		CAP_AUDIT_READ, CAP_SETUID, CAP_SETGID, CAP_MAC_OVERRIDE, CAP_SYS_NICE, CAP_SYS_CHROOT, CAP_NET_ADMIN,
		CAP_NET_BIND_SERVICE, CAP_NET_RAW, CAP_SYS_MODULE, CAP_IPC_LOCK, CAP_AUDIT_WRITE, CAP_KILL, CAP_NET_BROADCAST,
		CAP_SETPCAP, CAP_SYS_TIME, CAP_FSETID, CAP_MKNOD, CAP_SETFCAP, CAP_SYS_RESOURCE, TILDE_CAP_SYS_PTRACE,
		CAP_BLOCK_SUSPEND
	}

	public enum ConditionSecurityType {
		SELINUX, APPARMOR, TOMOYO, IMA, SMACK, AUDIT, TPM2, UEFI_SECUREBOOT
	}

	public enum DefaultDependenciesType {
		NO
	}

	public enum DevicePolicyType {
		CLOSED
	}

	public enum ELEMENTTYPE {
		SERVICE, TARGET, TIMER, SOCKET, PATH, SLICE, MOUNT, AUTOMOUNT, CONF, DEVICE
	}

	public enum IOSchedulingClassType {
		BEST_EFFORT, IDLE
	}

	public enum JobTimeoutActionType {
		POWEROFF_FORCE, REBOOT_FORCE
	}

	public enum KeyringModeType {
		INHERIT
	}

	public enum KillModeType {
		MIXED, NONE, PROCESS
	}

	public enum KillSignalType {
		SIGHUP, SIGTERM, SIGINT, SIGQUIT, SIGUSR2
	}

	public enum RestrictNamespacesType {
		CGROUP, IPC, NET, MNT, PID, USER, UTS, TRUE, YES
	}

	public enum NotifyAccessType {
		ALL, MAIN
	}

	public enum OnFailureJobModeType {
		REPLACE_IRREVERSIBLY
	}

	public enum ProtectProcType {
		INVISIBLE
	}

	public enum RestartType {
		ALWAYS, ON_FAILURE, NO, ON_ABORT
	}

	public enum RestrictAddressFamiliesType {
		AF_UNIX, AF_NETLINK, AF_INET, AF_INET6, AF_QIPCRTR, AF_LOCAL, AF_ALG, AF_PACKET
	}

	public enum SECTIONNAME {
		NOTCONFIGURED, SERVICE, INSTALL, PATH, AUTOMOUNT, UNIT, SOCKET, TIMER, MOUNT, SLISE, SLICE
	}

	public enum SuccessActionType {
		REBOOT, REBOOT_FORCE, EXIT_FORCE, POWEROFF_FORCE
	}

	public enum TypeType {
		DBUS, FORKING, NOTIFY, ONESHOT, SIMPLE, BTRFS, TMPFS, BINFMT_MISC, CONFIGFS, FUSECTL, DEBUGFS, IDLE, TRACEFS,
		HUGETLBFS, MQUEUE, EXEC
	}

	// SECTIONs
	private @Setter @Getter UnitSection unitSection;// create-ok
	private @Setter @Getter ServiceSection serviceSection;// create-ok
	private @Setter InstallSection installSection;
	private @Setter PathSection pathSection;
	private @Setter SocketSection socketSection;
	private @Setter TimerSection timerSection;
	private @Setter AUTOMOUNTSection automountSection;
	private @Setter SliceSection sliceSection;
	private @Setter MOUNTSection mountSection;
	// for constr
	private @Getter @Setter SECTIONNAME name = SECTIONNAME.NOTCONFIGURED;
	private @Getter @Setter String fileName;
	private @Getter @Setter ELEMENTTYPE elementType;

	// field files

	private @Getter @Setter Boolean accept;
	private @Getter @Setter Boolean allowIsolate;
	private @Getter @Setter Boolean bindIPv6Only;
	private @Getter @Setter Boolean casscredentials;
	private @Getter @Setter Boolean conditionacpower;
	private @Getter @Setter Boolean conditionfirstboot;
	private @Getter @Setter Boolean cpuaccounting;
	private @Getter @Setter Boolean defaultDependencies;
	private @Getter @Setter Boolean dynamicUser;
	private @Getter @Setter Boolean ignoreOnIsolate;
	private @Getter @Setter Boolean ignoresigpipe;
	private @Getter @Setter Boolean lockPersonality;
	private @Getter @Setter Boolean makedirectory;
	private @Getter @Setter Boolean memoryaccounting;
	private @Getter @Setter Boolean memoryDenyWriteExecute;
	private @Getter @Setter Boolean nonBlocking;
	private @Getter @Setter Boolean noNewPrivileges;
	private @Getter @Setter Boolean passpacketinfo;
	private @Getter @Setter Boolean passsecurity;
	private @Getter @Setter Boolean permissionsstartonly;
	private @Getter @Setter Boolean persistent;
	private @Getter @Setter Boolean privatedevices;
	private @Getter @Setter Boolean privatemounts;
	private @Getter @Setter Boolean privateNetwork;
	private @Getter @Setter Boolean privateTmp;
	private @Getter @Setter Boolean privateusers;
	private @Getter @Setter Boolean protectclock;
	private @Getter @Setter Boolean protectcontrolgroups;
	private @Getter @Setter Boolean protectHostname;
	private @Getter @Setter Boolean protectkernellogs;
	private @Getter @Setter Boolean protectKernelModules;
	private @Getter @Setter Boolean protectkerneltunables;
	private @Getter @Setter Boolean refuseManualStart;
	private @Getter @Setter Boolean refuseManualStop;
	private @Getter @Setter Boolean remainAfterExit;
	private @Getter @Setter Boolean removeipc;
	private @Getter @Setter Boolean removeOnStop;
	private @Getter @Setter Boolean restrictRealtime;
	private @Getter @Setter Boolean restrictsuidsgid;
	private @Getter @Setter Boolean runtimedirectorypreserve;
	private @Getter @Setter Boolean sendsighup;
	private @Getter @Setter Boolean stopWhenUnneeded;
	private @Getter @Setter Boolean ttyreset;
	private @Getter @Setter Boolean ttyVHangup;
	private @Getter @Setter Boolean ttyVTDisallocate;
	private @Getter @Setter Boolean writable;
	private @Getter @Setter ConditionSecurityType conditionSecurity;
	private @Getter @Setter CopyOnWriteArrayList<CAPs> ambientCapabilities = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<CAPs> capabilityBoundingSet = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<CAPs> conditionCapability = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<RestrictAddressFamiliesType> restrictAddressFamilies = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<RestrictNamespacesType> RestrictNamespaces = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<String> ConditionControlGroupController = new CopyOnWriteArrayList<>();

	private @Getter @Setter CopyOnWriteArrayList<String> ConditionKernelCommandLine = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<String> ListenDatagram = new CopyOnWriteArrayList<>();

	private @Getter @Setter CopyOnWriteArrayList<String> ConditionDirectoryNotEmpty = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<String> ConditionNeedsUpdate = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<String> ConditionPathExistsGlob = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<String> conditionPathExists = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<String> deviceAllow = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<String> documentation = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<String> environmentFile = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<String> environment = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<String> execReload = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<String> execStart = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<String> execStartPre = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<String> ListenFIFO = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<String> listenStream = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<String> loadcredential = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<String> readWriteDirectories = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<String> readwritepaths = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<String> systemcallfilter = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<SystemdElement> after = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<SystemdElement> also = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<SystemdElement> before = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<SystemdElement> conflicts = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<SystemdElement> requiredBy = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<SystemdElement> requires = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<SystemdElement> service = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<SystemdElement> Sockets = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<SystemdElement> wantedBy = new CopyOnWriteArrayList<>();
	private @Getter @Setter CopyOnWriteArrayList<SystemdElement> wants = new CopyOnWriteArrayList<>();
	private @Getter @Setter DevicePolicyType devicePolicy;
	private @Getter @Setter Integer FileDescriptorStoreMax;
	private @Getter @Setter Integer IOSchedulingPriority;
	private @Getter @Setter Integer MaxConnections;
	private @Getter @Setter Integer Nice;
	private @Getter @Setter Integer RestartForceExitStatus;
	private @Getter @Setter Integer RestartSec;
	private @Getter @Setter Integer RuntimeDirectoryMode;
	private @Getter @Setter Integer StartLimitBurst;
	private @Getter @Setter Integer StartLimitIntervalSec;
	private @Getter @Setter IOSchedulingClassType iOschedulingclass;
	private @Getter @Setter KeyringModeType keyringMode;
	private @Getter @Setter KillModeType killMode;
	private @Getter @Setter KillSignalType killSignal;
	private @Getter @Setter KillSignalType RestartKillSignal;
	private @Getter @Setter NotifyAccessType notifyAccess;
	private @Getter @Setter ProtectProcType protectproc;
	private @Getter @Setter RestartType restart;
	private @Getter @Setter String accuracysec;
	private @Getter @Setter String assertPathExists;
	private @Getter @Setter String busName;
	private @Getter @Setter String conditionDirectoryNotEmpty;
	private @Getter @Setter String ConditionFileIsExecutable;
	private @Getter @Setter String ConditionFileNotEmpty;
	private @Getter @Setter String conditionNeedsUpdate;
	private @Getter @Setter String ConditionPathIsMountPoint;
	private @Getter @Setter String conditionPathIsReadWrite;
	private @Getter @Setter String conditionPathIsSymbolicLink;
	private @Getter @Setter String conditionVirtualization;
	private @Getter @Setter String CPUSchedulingPolicy;
	private @Getter @Setter String DefaultInstance;
	private @Getter @Setter String delegate;
	private @Getter @Setter String description;
	private @Getter @Setter String DirectoryNotEmpty;
	private @Getter @Setter String ExecStartPost;
	private @Getter @Setter String execStop;
	private @Getter @Setter String execStopPost;
	private @Getter @Setter String memorymin;
	private @Getter @Setter String socketuser;

	private @Getter @Setter String memorylow;
	private @Getter @Setter String socketgroup;
	private @Getter @Setter String group;
	private @Getter @Setter String iPAddressDeny;
	private @Getter @Setter String JobTimeoutAction;// poweroff-force reboot-force
	private @Getter @Setter String JobTimeoutSec;
	private @Getter @Setter String limitCORE;
	private @Getter @Setter String limitMEMLOCK;
	private @Getter @Setter String limitNOFILE;
	private @Getter @Setter String listennetlink;
	private @Getter @Setter String listensequentialpacket;
	private @Getter @Setter String listenspecial;
	private @Getter @Setter String LogsDirectory;
	private @Getter @Setter String LogsDirectoryMode;
	private @Getter @Setter String onBootSec;
	private @Getter @Setter String oncalendar;
	private @Getter @Setter String OnFailureJobMode;
	private @Getter @Setter String onunitactivesec;
	private @Getter @Setter String oOMScoreAdjust;
	private @Getter @Setter String options;
	private @Getter @Setter String pAMName;
	private @Getter @Setter String PathExists;
	private @Getter @Setter String pIDFile;
	private @Getter @Setter String ProcSubset;
	private @Getter @Setter String protectHome;
	private @Getter @Setter String protectSystem;
	private @Getter @Setter String RandomizedDelaySec;
	private @Getter @Setter String readOnlyDirectories;
	private @Getter @Setter String receiveBuffer;
	private @Getter @Setter Boolean PassCredentials;
	private @Getter @Setter String requiresMountsFor;
	private @Getter @Setter String RestartPreventExitStatus;
	private @Getter @Setter String runtimeDirectory;
	private @Getter @Setter String RuntimeMaxSec;
	private @Getter @Setter String SendBuffer;
	private @Getter @Setter String slice;
	private @Getter @Setter String socketMode;
	private @Getter @Setter String StandardError;
	private @Getter @Setter String standardinput;
	private @Getter @Setter String standardOutput;
	private @Getter @Setter String stateDirectory;
	private @Getter @Setter String SuccessExitStatus;
	private @Getter @Setter String SupplementaryGroups;
	private @Getter @Setter String Symlinks;
	private @Getter @Setter String SyslogIdentifier;
	private @Getter @Setter String systemcallarchitectures;// native
	private @Getter @Setter String systemcallerrornumber;// EPERM
	private @Getter @Setter String tasksMax;
	private @Getter @Setter String timeoutSec;
	private @Getter @Setter String timeoutStartSec;
	private @Getter @Setter String timeoutStopSec;
	private @Getter @Setter String Timestamping;
	private @Getter @Setter String ttyPath;
	private @Getter @Setter String UMask;
	private @Getter @Setter String UnsetEnvironment;
	private @Getter @Setter String user;
	private @Getter @Setter String utmpIdentifier;
	private @Getter @Setter String watchdogSec;
	private @Getter @Setter String what;
	private @Getter @Setter String where;
	private @Getter @Setter String workingDirectory;
	private @Getter @Setter SuccessActionType successAction;
	private @Getter @Setter SystemdElement alias;
	private @Getter @Setter SystemdElement bindsTo;
	private @Getter @Setter SystemdElement OnFailure;
	private @Getter @Setter SystemdElement partof;
	private @Getter @Setter TypeType type;

	// field files

	public SystemdElement fileName(String name) throws IllegalArgumentException, ObjectTypeException {
		elementType = ServiceUtils.getElementType(name);
		fileName = name;
		return this;
	}

	public AUTOMOUNTSection getAutomountSection() {
		if (automountSection == null) {
			automountSection = new AUTOMOUNTSection();
		}
		return automountSection;
	}

	public InstallSection getInstallSection() {
		if (installSection == null) {
			installSection = new InstallSection();
		}
		return installSection;
	}

	public MOUNTSection getMountSection() {
		if (mountSection == null) {
			mountSection = new MOUNTSection();
		}
		return mountSection;
	}

	public PathSection getOrCreatePathSection() {
		if (pathSection == null) {
			pathSection = new PathSection();
		}
		return pathSection;
	}

	public ServiceSection getOrCreateServiceSection() {
		if (serviceSection == null) {
			serviceSection = new ServiceSection();
		}
		return serviceSection;
	}

	public SliceSection getOrCreateSliceSection() {
		if (sliceSection == null) {
			sliceSection = new SliceSection();
		}
		return sliceSection;
	}

	public SocketSection getOrCreateSocketSection() {
		if (socketSection == null) {
			socketSection = new SocketSection();
		}
		return socketSection;
	}

	public TimerSection getOrCreateTimerSection() {
		if (timerSection == null) {
			timerSection = new TimerSection();
		}
		return timerSection;
	}

	public UnitSection getOrCreateUnitSection() {
		if (unitSection == null) {
			unitSection = new UnitSection();
		}
		return unitSection;
	}

	public String retName() {
		return "[" + getName() + "]";
	}

	public String toServiceFile() {
		StringBuilder builder = new StringBuilder();
		builder.append(retName()).append('\n');

		return builder.toString();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
