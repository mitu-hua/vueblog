package com.mituh.vueblog.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Hzz
 * @date 2020/11/22 14:49
 */

@Data
public class Result implements Serializable {
    private int code;
    private String mess;
    private Object data;

    public Result ok(Object o){
        this.setCode(200);
        this.setMess("调用成功");
        this.setData(o);
        return this;
    }

    public Result fail(String msg){
        this.setCode(400);
        this.setMess(msg);
        return this;
    }

    public Result(){}

    public Result(int code,String mess,Object o){
        this.setCode(code);
        this.setMess(mess);
        this.setData(o);
    }
  /*  public Result(String mess,Object o){
        this.setMess(mess);
        this.setData(o);
    }*/
}
