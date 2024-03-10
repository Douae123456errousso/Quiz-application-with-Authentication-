package model;

public class UserScore{

    private String name;
    private String email;
    private int Score;
    private String Lang;




    public int getScore() {
        return Score;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }

    public String getLang() {
        return Lang;
    }

    public void setLang(int Score) {
        this.Lang = Lang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserScore(String email, String name,int Score,String Lang) {
        this.email = email;
        this.Score = Score;
        this.name = name;
        this.Lang = Lang;
    }
}

