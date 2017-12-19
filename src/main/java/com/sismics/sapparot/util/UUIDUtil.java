package com.sismics.sapparot.util;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * @author jtremeaux
 */
public class UUIDUtil {
    public static UUID randomUUID(String seed) {
        SecureRandom ng = new SecureRandom(seed.getBytes());

        byte[] randomBytes = new byte[16];
        ng.nextBytes(randomBytes);
        randomBytes[6]  &= 0x0f;  /* clear version        */
        randomBytes[6]  |= 0x40;  /* set to version 4     */
        randomBytes[8]  &= 0x3f;  /* clear variant        */
        randomBytes[8]  |= 0x80;  /* set to IETF variant  */
        long high = ((randomBytes[8] & 0xFFL) << 56) |
                ((randomBytes[9] & 0xFFL) << 48) |
                ((randomBytes[10] & 0xFFL) << 40) |
                ((randomBytes[11] & 0xFFL) << 32) |
                ((randomBytes[12] & 0xFFL) << 24) |
                ((randomBytes[13] & 0xFFL) << 16) |
                ((randomBytes[14] & 0xFFL) <<  8) |
                ((randomBytes[15] & 0xFFL) <<  0) ;
        long low = ((randomBytes[0] & 0xFFL) << 56) |
                ((randomBytes[1] & 0xFFL) << 48) |
                ((randomBytes[2] & 0xFFL) << 40) |
                ((randomBytes[3] & 0xFFL) << 32) |
                ((randomBytes[4] & 0xFFL) << 24) |
                ((randomBytes[5] & 0xFFL) << 16) |
                ((randomBytes[6] & 0xFFL) <<  8) |
                ((randomBytes[7] & 0xFFL) <<  0) ;
        return new UUID(high, low);
    }

}
