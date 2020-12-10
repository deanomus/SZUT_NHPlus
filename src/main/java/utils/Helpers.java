package utils;

public class Helpers {

    /**
     * Generate md5 hash from given text
     * Source: https://stackoverflow.com/questions/415953/how-can-i-generate-an-md5-hash
     *
     * @param string
     * @return
     */
    public static String md5hash(String string) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(string.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Boolean loggedIn = false;

}
