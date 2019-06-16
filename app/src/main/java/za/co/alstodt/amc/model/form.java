package za.co.alstodt.amc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class form {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("feedback")
    @Expose
    private String feedback;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("comments")
    @Expose
    private String comments;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = comments;
    }


}
