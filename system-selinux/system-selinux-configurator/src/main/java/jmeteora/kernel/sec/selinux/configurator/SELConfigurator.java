package jmeteora.kernel.sec.selinux.configurator;

import static javax.swing.SwingConstants.TOP;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.epoll.EpollDomainSocketChannel;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.unix.DomainSocketAddress;
import io.netty.channel.unix.DomainSocketChannel;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import jmeteora.kernel.sec.selinux.libs.Summary;

public class SELConfigurator extends JFrame {

	public class SELConfiguratorHandler extends ChannelDuplexHandler {
		private static final Logger LOGGER = LoggerFactory.getLogger("SELConfiguratorHandler");

		@Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception {
			LOGGER.info("channelActive {}", ctx);
		}

		@Override
		public void channelInactive(ChannelHandlerContext ctx) throws Exception {
			LOGGER.info("channelInactive {}", ctx);
		}

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			LOGGER.info("channelRead {}", msg);
		}
	}

	private static final long serialVersionUID = -3292032944259907307L;

	private static final String DEV_SHM_JMETEORA_SYSTEM_SELINUX_SERVER = "/dev/shm/jmeteora/system/selinux/server";

	public static void main(String[] args) {
		SELConfigurator selConfigurator = new SELConfigurator();
		selConfigurator.init();
		selConfigurator.connect();
	}

	private ChannelFuture channelFuture;

	public SELConfigurator() {
		setTitle("SELinux configurator");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane(TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JTabbedPane tPState = new JTabbedPane(TOP);
		tabbedPane.addTab("Summary", null, tPState, null);

		JTabbedPane tPUsers = new JTabbedPane(TOP);
		tabbedPane.addTab("Users", null, tPUsers, null);

		JTabbedPane tPRoles = new JTabbedPane(TOP);
		tabbedPane.addTab("Roles", null, tPRoles, null);

		JTabbedPane tPRules = new JTabbedPane(TOP);
		tabbedPane.addTab("Rules", null, tPRules, null);

		JTabbedPane tPLogs = new JTabbedPane(TOP);
		tabbedPane.addTab("Logs", null, tPLogs, null);

		setSize();
	}

	private ChannelFuture connect() {
		final Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(new EpollEventLoopGroup()).channel(EpollDomainSocketChannel.class);
		bootstrap.handler(new ChannelInitializer<DomainSocketChannel>() {

			@Override
			protected void initChannel(DomainSocketChannel ch) throws Exception {
				ChannelPipeline p = ch.pipeline();

				ClassLoader a0 = Summary.class.getClassLoader();
				ClassResolver a1 = ClassResolvers.softCachingConcurrentResolver(a0);
				p.addLast(new ObjectDecoder(Integer.MAX_VALUE, a1));
				p.addLast(new ObjectEncoder());

				p.addLast(new SELConfiguratorHandler());
			}

		});
		channelFuture = bootstrap.connect(new DomainSocketAddress(DEV_SHM_JMETEORA_SYSTEM_SELINUX_SERVER));
		return channelFuture;
	}

	private void init() {

		setVisible(true);
	}

	private void setSize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int md = 6;
		int w = screenSize.width - screenSize.width / md;
		int h = screenSize.height - screenSize.height / md;
		setSize(w, h);
	}

}
