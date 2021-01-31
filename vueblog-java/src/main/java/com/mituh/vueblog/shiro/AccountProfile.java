package com.mituh.vueblog.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author
 * @Date 2020/11/25 17:05
 */

@Data
public class AccountProfile implements Serializable {

    private Long id;

    private String username;

    private String avatar;

    private String email;
}
