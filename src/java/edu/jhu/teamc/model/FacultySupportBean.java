package edu.jhu.teamc.model;

/**
 * Created by edwardkim on 7/23/16.
 */
public class FacultySupportBean {

    private String ssn;
    private String first_name;
    private String last_name;
    private String email;
    private String userid;
    private String password;
    private String address;

    public FacultySupportBean(String ssn, String first_name, String last_name, String email,
            String userid, String password, String address) {
        this.ssn = ssn;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.userid = userid;
        this.password = password;
        this.address = address;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSsn() {
        return ssn;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getUserid() {
        return userid;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

}
