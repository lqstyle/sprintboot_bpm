package com.kpmg.bpm.util;

import java.util.UUID;

/**
 * @author lucasliang
 * @version 0.0.1-SNAPSHOT
 * @description: 获取随机id
 * @date 09/06/2018 9:56 上午
 */
public class UUIDUtils {
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.replace("-", "");
    }
}
