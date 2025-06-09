package com.example.labSystem.utils;

import jakarta.servlet.http.HttpServletRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;


public class IpUtil {
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        // ipAddress = this.getRequest().getRemoteAddr();
        System.out.println("用户ip：" + ipAddress);
        return ipAddress;
    }


    public static boolean isInNetworkIpv4(String ipStr, String networkStr) {
        try {
            // 解析输入的IP地址和网络段
            InetAddress ip = InetAddress.getByName(ipStr);
            String[] parts = networkStr.split("/");
            InetAddress networkIp = InetAddress.getByName(parts[0]);
            int prefix = Integer.parseInt(parts[1]);

            // 只支持IPv4
            if (ip instanceof java.net.Inet4Address && networkIp instanceof java.net.Inet4Address) {
                byte[] ipBytes = ip.getAddress();
                byte[] networkBytes = networkIp.getAddress();

                // 将掩码转换为32位整数
                int mask = -1 << (32 - prefix); // 左移得到前缀掩码

                // 将字节数组转为整数
                int ipInt = byteArrayToInt(ipBytes);
                int networkInt = byteArrayToInt(networkBytes);

                // 应用掩码进行比较
                return (ipInt & mask) == (networkInt & mask);
            } else {
                throw new IllegalArgumentException("Only IPv4 is supported.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static int byteArrayToInt(byte[] bytes) {
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        return bb.getInt();
    }
}
///**
// * 获取IP方法
// *
// * @author dcdz
// */
//public class IpUtil {
//    private static final Logger log = LoggerFactory.getLogger(IpUtil.class);
//
//    public static String getIpAddr(HttpServletRequest request) {
//        if (request == null) {
//            return null;
//        }
//
//        String ip = null;
//
//        // X-Forwarded-For：Squid 服务代理
//        String ipAddresses = request.getHeader("X-Forwarded-For");
//        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
//            // Proxy-Client-IP：apache 服务代理
//            ipAddresses = request.getHeader("Proxy-Client-IP");
//        }
//        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
//            // WL-Proxy-Client-IP：weblogic 服务代理
//            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
//            // HTTP_CLIENT_IP：有些代理服务器
//            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
//        }
//        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
//            // X-Real-IP：nginx服务代理
//            ipAddresses = request.getHeader("X-Real-IP");
//        }
//
//        // 有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
//        if (ipAddresses != null && ipAddresses.length() != 0) {
//            ip = ipAddresses.split(",")[0];
//        }
//
//        // 还是不能获取到，最后再通过request.getRemoteAddr();获取
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
//            ip = request.getRemoteAddr();
//        }
//        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
//    }
//
//    public static boolean internalIp(String ip) {
//        byte[] addr = textToNumericFormatV4(ip);
//        return internalIp(addr) || "127.0.0.1".equals(ip);
//    }
//
//    private static boolean internalIp(byte[] addr) {
//        if (addr == null || addr.length < 2) {
//            return true;
//        }
//        final byte b0 = addr[0];
//        final byte b1 = addr[1];
//        // 10.x.x.x/8
//        final byte SECTION_1 = 0x0A;
//        // 172.16.x.x/12
//        final byte SECTION_2 = (byte) 0xAC;
//        final byte SECTION_3 = (byte) 0x10;
//        final byte SECTION_4 = (byte) 0x1F;
//        // 192.168.x.x/16
//        final byte SECTION_5 = (byte) 0xC0;
//        final byte SECTION_6 = (byte) 0xA8;
//        switch (b0) {
//            case SECTION_1:
//                return true;
//            case SECTION_2:
//                if (b1 >= SECTION_3 && b1 <= SECTION_4) {
//                    return true;
//                }
//            case SECTION_5:
//                switch (b1) {
//                    case SECTION_6:
//                        return true;
//                }
//            default:
//                return false;
//        }
//    }
//
//    /**
//     * 将IPv4地址转换成字节
//     *
//     * @param text IPv4地址
//     * @return byte 字节
//     */
//    public static byte[] textToNumericFormatV4(String text) {
//        if (text.length() == 0) {
//            return null;
//        }
//
//        byte[] bytes = new byte[4];
//        String[] elements = text.split("\\.", -1);
//        try {
//            long l;
//            int i;
//            switch (elements.length) {
//                case 1:
//                    l = Long.parseLong(elements[0]);
//                    if ((l < 0L) || (l > 4294967295L)) {
//                        return null;
//                    }
//                    bytes[0] = (byte) (int) (l >> 24 & 0xFF);
//                    bytes[1] = (byte) (int) ((l & 0xFFFFFF) >> 16 & 0xFF);
//                    bytes[2] = (byte) (int) ((l & 0xFFFF) >> 8 & 0xFF);
//                    bytes[3] = (byte) (int) (l & 0xFF);
//                    break;
//                case 2:
//                    l = Integer.parseInt(elements[0]);
//                    if ((l < 0L) || (l > 255L)) {
//                        return null;
//                    }
//                    bytes[0] = (byte) (int) (l & 0xFF);
//                    l = Integer.parseInt(elements[1]);
//                    if ((l < 0L) || (l > 16777215L)) {
//                        return null;
//                    }
//                    bytes[1] = (byte) (int) (l >> 16 & 0xFF);
//                    bytes[2] = (byte) (int) ((l & 0xFFFF) >> 8 & 0xFF);
//                    bytes[3] = (byte) (int) (l & 0xFF);
//                    break;
//                case 3:
//                    for (i = 0; i < 2; ++i) {
//                        l = Integer.parseInt(elements[i]);
//                        if ((l < 0L) || (l > 255L)) {
//                            return null;
//                        }
//                        bytes[i] = (byte) (int) (l & 0xFF);
//                    }
//                    l = Integer.parseInt(elements[2]);
//                    if ((l < 0L) || (l > 65535L)) {
//                        return null;
//                    }
//                    bytes[2] = (byte) (int) (l >> 8 & 0xFF);
//                    bytes[3] = (byte) (int) (l & 0xFF);
//                    break;
//                case 4:
//                    for (i = 0; i < 4; ++i) {
//                        l = Integer.parseInt(elements[i]);
//                        if ((l < 0L) || (l > 255L)) {
//                            return null;
//                        }
//                        bytes[i] = (byte) (int) (l & 0xFF);
//                    }
//                    break;
//                default:
//                    return null;
//            }
//        } catch (NumberFormatException e) {
//            return null;
//        }
//        return bytes;
//    }
//
//    /**
//     * 获取本地IP地址
//     *
//     * @throws SocketException
//     */
//    public static String getHostIp() {
//        if (isWindowsOS()) {
//            return getWindowsHostIp();
//        } else {
//            return getLinuxHostIp();
//        }
//    }
//
//    /**
//     * 获取Linux下的IP地址
//     *
//     * @return IP地址
//     * @throws SocketException
//     */
//    private static String getLinuxHostIp() {
//        String ip = "";
//        try {
//            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
//                NetworkInterface intf = en.nextElement();
//                String name = intf.getName();
//                if (!name.contains("docker") && !name.contains("lo")) {
//                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
//                        InetAddress inetAddress = enumIpAddr.nextElement();
//                        if (!inetAddress.isLoopbackAddress()) {
//                            String ipaddress = inetAddress.getHostAddress().toString();
//                            if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
//                                ip = ipaddress;
//                                System.out.println(ipaddress);
//                            }
//                        }
//                    }
//                }
//            }
//        } catch (SocketException ex) {
//            log.error("获取Linux IP地址异常");
//            ip = "127.0.0.1";
//            ex.printStackTrace();
//        }
//        return ip;
//    }
//
//    /**
//     * 判断操作系统是否是Windows
//     *
//     * @return
//     */
//    public static boolean isWindowsOS() {
//        boolean isWindowsOS = false;
//        String osName = System.getProperty("os.name");
//        if (osName.toLowerCase().indexOf("windows") > -1) {
//            isWindowsOS = true;
//        }
//        return isWindowsOS;
//    }
//
//    public static String getWindowsHostIp() {
//        try {
//            return InetAddress.getLocalHost().getHostAddress();
//        } catch (UnknownHostException e) {
//        }
//        return "127.0.0.1";
//    }
//
//    public static String getHostName() {
//        try {
//            return InetAddress.getLocalHost().getHostName();
//        } catch (UnknownHostException e) {
//        }
//        return "未知";
//    }
//}