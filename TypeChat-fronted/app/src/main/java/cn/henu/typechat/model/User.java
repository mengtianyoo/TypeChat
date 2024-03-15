package cn.henu.typechat.model;

import java.util.Date;

public class User extends BaseEntity{
    private Long id;
    private String email;
    private String nickname;
    private String avatar;
    private Integer gender;
    private String password;
    private String background;
    private Date birthday;
    private String introduction;
    private Boolean isActivated;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }



}
