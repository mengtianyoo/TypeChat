package cn.henu.typechat.mode;

public class User {
    private String email;
    private String nickname;
    private String gender;
    private String password;
    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)

    }

    public User(String email, String nickname, String gender, String password) {
        this.email = email;
        this.nickname = nickname;
        this.gender = gender;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
