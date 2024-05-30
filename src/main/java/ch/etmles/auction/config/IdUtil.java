package ch.etmles.auction.config;

import java.util.Base64;

public class IdUtil {

    public static String encodeId(Long id) {
        return Base64.getUrlEncoder().encodeToString(id.toString().getBytes());
    }

    public static Long decodeId(String encodedId) {
        return Long.parseLong(new String(Base64.getUrlDecoder().decode(encodedId)));
    }
}
