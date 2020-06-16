import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateIPAddress {
    private static final Pattern HEXADECIMAL_PATTERN = Pattern.compile("\\p{XDigit}+");
    public static void main(String[] args) {
        String ipv4 = "12..33.4";
        //String ipv6 = "2001:0db8:85a3:0:0:8A2E:0370:7334:";
        System.out.println(validIPAddress(ipv4));
    }

    public static String validIPAddress(String IP) {
        if(IP.length() == 0 || IP.charAt(IP.length()-1) == ':' || IP.charAt(IP.length()-1) == '.' || IP.contains("-"))
         return "Neither";
        String[] ipv4 = IP.split("\\.");
        if(ipv4.length == 4) {
            for (int i=0; i<ipv4.length; i++) {
                if(ipv4[i].length() == 0 || ipv4[i].length() != 1 && ipv4[i].charAt(0) == '0') {
                    return "Neither";
                }
                try {
                    int val = Integer.parseInt(ipv4[i]);
                    if(val > 255 || val < 0 ) {
                        return "Neither";
                    }    
                } catch (Exception e) {
                    return "Neither";
                }
            }
            return "IPv4";
        } else {
            String[] ipv6 = IP.split(":");
            if (ipv6.length == 8) {
                for (int i = 0; i < ipv6.length; i++) {
                    if(ipv6[i].length() == 0 || ! isHexadecimal(ipv6[i])
                        || ipv6[i].length() > 4) return "Neither";
                }
                return "IPv6";
            } else {
                return "Neither";
            }
        }
    }

    private static boolean isHexadecimal(String input) {
        final Matcher matcher = HEXADECIMAL_PATTERN.matcher(input);
        return matcher.matches();
    }
}