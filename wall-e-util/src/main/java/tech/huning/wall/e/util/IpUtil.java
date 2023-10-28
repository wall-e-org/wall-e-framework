package tech.huning.wall.e.util;

import tech.huning.wall.e.specs.exception.SystemException;
import tech.huning.wall.e.specs.model.ResultCode;

import javax.servlet.http.HttpServletRequest;
import java.net.*;
import java.util.Enumeration;
import java.util.List;

/**
 * IP工具类
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class IpUtil {

	/**
	 * 获取请求IP地址
	 * 
	 * request.getRemoteAddr()这种方法在大部分情况下都是有效的。
	 * 但是在通过了Apache,Squid等反向代理软件就不能获取到客户端的真实IP地址了。
	 * 如果使用了反向代理软件，将http://192.168.1.110:2046/ 的URL反向代理 为http://www.xxx.com/
	 * 的URL时，用request.getRemoteAddr()
	 * 方法获取的IP地址是：127.0.0.1或192.168.1.110，而并不是客户端的真实IP。
	 * 经过代理以后，由于在客户端和服务之间增加了中间层，因此服务器无法直接拿到客户端的IP，
	 * 服务器端应用也无法直接通过转发请求的地址返回给客户端。但是在转发请求的HTTP头信息中，
	 * 增加了?x-forwarded-for?信息用以跟踪原有的客户端IP地址和原来客户端请求的服务器地址。
	 * 如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串ip值，真正的
	 * 用户端的真实IP是取X-Forwarded-For中第一个非unknown的有效IP字符串。
	 * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
	 * 192.168.1.100,用户真实IP为:192.168.1.110。
	 * 
	 * @param request 请求
	 * @return ip
	 */
	public static String getRemoteIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		final String[] arr = ip.split(",");
		for (final String str : arr) {
			if (!"unknown".equalsIgnoreCase(str)) {
				ip = str;
				break;
			}
		}
		return ip;
	}

	/**
	 * 获取服务器IP
	 * 
	 * 1.InetAddress.getLocalHost()在Windows下能获取到服务器IP,但Linux下获取到的就是127.0.0.1
	 * 2.java.net.InterfaceAddress是JDK6.0新增的 3.单网卡时会返回其IP,多网卡时会返回第一块网卡的IP
	 * 
	 * @return ip
	 * @throws SystemException 系统异常
	 */
	public static String getServerIP() throws SystemException {
		String serverIP = null;
		try {
			Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
			tag: while (nets.hasMoreElements()) {
				NetworkInterface net = nets.nextElement();
				if (null != net.getHardwareAddress()) {
					List<InterfaceAddress> addressList = net.getInterfaceAddresses();
					for (InterfaceAddress obj : addressList) {
						InetAddress IP = obj.getAddress();
						if (null != IP && IP instanceof Inet4Address && !IP.getHostAddress().equals("127.0.0.1")) {
							serverIP = IP.getHostAddress();
							break tag;
						}
					}
				}
			}
		} catch (SocketException e) {
			throw new SystemException(e, ResultCode.FAILURE);
		}
		return serverIP;
	}
	
}
