package com.mituh.vueblog.utils;

import com.mituh.vueblog.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * @author
 * @date 2020/11/26 17:13
 */
public class ShiroUtils {
    public static AccountProfile getProfile(){
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
