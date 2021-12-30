package com.gmail.creativegeeksuresh.zyncky.service.util;

public class AppConstants {

        public static final String APP_NAME = "Zyncky";
        public static final String APP_PREFIX = "ZKY";
        public static final String APP_DESC = "Default app created to use the functionalities";
        public static final String UNDERSCORE = "_";
        public static final String ROLE_PREFIX = "ROLE_";
        public static final String PRIVATE_KEY_PATH = "security/keys/jwt_privatekey.der";
        public static final String PUBLIC_KEY_PATH = "security/keys/jwt_publickey.der";

        public static enum MfaType {
                NONE, GOOGLE_AUTH, OTP, FINGERPRINT
        }

        public static enum UserRole {
                ADMIN, USER, MFA, ANONYMOUS
        }
}
