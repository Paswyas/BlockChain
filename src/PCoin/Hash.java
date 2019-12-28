package PCoin;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    public static void main(String[] args) {
        System.out.println(getSHA256("witcher"));
        proofOfWork();
    }

    public static String getSHA256(String s) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(s.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return encodeStr;
    }

    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    public static void proofOfWork() {
        int x = 1;
        while (true) {
            if (!getSHA256("archer" + x).substring(0, 5).equals("00000")) {
                x++;
            } else {
                System.out.println(getSHA256("x"));
                System.out.println(x);
                break;
            }
        }
    }

}
