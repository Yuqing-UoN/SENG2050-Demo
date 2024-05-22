package org.seng2050.lab08;

public class Quiz {

    private String answer1 = "";
    private String answer2 = "";
    private String answer3 = "";
    private String answer4 = "";

    public String getAnswer1() {
        return this.answer1;
    }
    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }
    public String getAnswer2() {
        return this.answer2;
    }
    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }
    public String getAnswer3() {
        return this.answer3;
    }
    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }
    public String getAnswer4() {
        return this.answer4;
    }
    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public int getScore() {
        int score = 0;
        if ("Yuqing".equalsIgnoreCase(this.answer1)) {
            score++;
        }
        if ("SENG2050".equalsIgnoreCase(this.answer2)) {
            score++;
        }
        if ("UON".equalsIgnoreCase(this.answer3)) {
            score++;
        }
        if ("Programming".equalsIgnoreCase(this.answer4)) {
            score++;
        }
        return score;
    }
}
 