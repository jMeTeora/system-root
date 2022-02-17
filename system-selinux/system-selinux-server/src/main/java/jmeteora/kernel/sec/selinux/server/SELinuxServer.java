package jmeteora.kernel.sec.selinux.server;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.epoll.EpollDomainSocketChannel;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerDomainSocketChannel;
import io.netty.channel.unix.DomainSocketAddress;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import jmeteora.kernel.sec.selinux.libs.Summary;
import jmeteora.kernel.sec.selinux.server.utils.SELinuxUtils;
import jmeteora.system.apidata.Paths;

public class SELinuxServer extends Thread {
	boolean active = true;

	@Override
	public void run() {
		while (active) {
			try {
				SELinuxUtils.audit2allowAREV();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	class SELinuxServerHandler extends ChannelDuplexHandler {
		private static final Logger LOGGER = LoggerFactory.getLogger("SELinuxServerHandler");

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

	/**
	 * demo only!!!
	 * 
	 * @param args
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		SELinuxServer server = new SELinuxServer();
		server.bind();
		server.start();
	}

	final ServerBootstrap bootstrap = new ServerBootstrap();
	final EpollEventLoopGroup group = new EpollEventLoopGroup();
	private ChannelFuture future;

	public void bind() throws InterruptedException, IOException {
		bootstrap
				//
				.group(new EpollEventLoopGroup(), new EpollEventLoopGroup())
				//
				.channel(EpollServerDomainSocketChannel.class)
				//
				.childHandler(new ChannelInitializer<EpollDomainSocketChannel>() {

					@Override
					protected void initChannel(EpollDomainSocketChannel ch) throws Exception {
						ChannelPipeline p = ch.pipeline();

						ClassLoader a0 = Summary.class.getClassLoader();
						ClassResolver a1 = ClassResolvers.softCachingConcurrentResolver(a0);
						p.addLast(new ObjectDecoder(Integer.MAX_VALUE, a1));
						p.addLast(new ObjectEncoder());

						p.addLast(new SELinuxServerHandler());
					}
				});

		File socket = new File(Paths.DEV_SHM_JMETEORA_SYSTEM_SELINUX_SERVER);
		if (!socket.getParentFile().exists()) {
			socket.getParentFile().mkdirs();
		}
		if (!socket.exists()) {
			socket.createNewFile();
		}
		String input = socket.getAbsolutePath();
		future = bootstrap.bind(new DomainSocketAddress(input)).sync();
	}

}
