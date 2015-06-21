package com.ay27.WirelessDebug;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class IP_Utilities {
	private static Pattern VALID_IPV4_PATTERN = null;
	private static Pattern VALID_IPV6_PATTERN = null;
	private static final String ipv4Pattern = "(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])";
	private static final String ipv6Pattern = "([0-9a-f]{1,4}:){7}([0-9a-f]){1,4}";

	static {
		try {
			VALID_IPV4_PATTERN = Pattern.compile(ipv4Pattern,
					Pattern.CASE_INSENSITIVE);
			VALID_IPV6_PATTERN = Pattern.compile(ipv6Pattern,
					Pattern.CASE_INSENSITIVE);
		} catch (PatternSyntaxException e) {
		}
	}

	public static boolean isIpAddress(String ipAddress) {
		Matcher m1 = IP_Utilities.VALID_IPV4_PATTERN.matcher(ipAddress);
		if (m1.matches()) {
			return true;
		}
		Matcher m2 = IP_Utilities.VALID_IPV6_PATTERN.matcher(ipAddress);
		return m2.matches();
	}

	public static boolean isIpv4Address(String ipAddress) {
		Matcher m1 = IP_Utilities.VALID_IPV4_PATTERN.matcher(ipAddress);
		return m1.matches();
	}

	public static boolean isIpv6Address(String ipAddress) {
		Matcher m1 = IP_Utilities.VALID_IPV6_PATTERN.matcher(ipAddress);
		return m1.matches();
	}

	public static InetAddress getLocalIpAddress(boolean removeIPv6) {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
                if (!intf.getName().toLowerCase().contains("wlan"))
                    continue;
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()
							&& !inetAddress.isAnyLocalAddress()
							&& (!removeIPv6 || isIpv4Address(inetAddress
									.getHostAddress())))
						return inetAddress;
				}
			}
		} catch (Exception exception) {
            exception.printStackTrace();
        }
		return null;
	}

}
