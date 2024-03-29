package app.web.zyncky.util;

import java.security.SecureRandom;
import java.util.Base64;

import app.web.zyncky.constant.AppConstants;

public class CommonUtils {

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    public static String generateToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    public static String formatRole(String roleName) {
        return AppConstants.ROLE_PREFIX + roleName;
    }

}
