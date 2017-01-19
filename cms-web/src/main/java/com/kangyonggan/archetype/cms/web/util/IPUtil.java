package com.kangyonggan.archetype.cms.web.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author kangyonggan
 * @since 16/6/29
 */
public class IPUtil {
    
    public static String getServerHost(HttpServletRequest request) {
        String path = "http://" + request.getServerName();
        int port = request.getServerPort();
        if (port != 80) {
            path += ":" + request.getServerPort();
        }

        return path;
    }
}
