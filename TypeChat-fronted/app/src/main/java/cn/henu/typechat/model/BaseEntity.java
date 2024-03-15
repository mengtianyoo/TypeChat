package cn.henu.typechat.model;

import java.util.Date;

public class BaseEntity {
    public Date createTime = new Date();  //创建时间
    public Boolean isDeleted = false;    //逻辑删除标志
    public Date updateTime = new Date();  //更新时间
}
