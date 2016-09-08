package ienquire.ie.libfff.model;

import java.io.*;

/**
 * Created by barryoreilly on 13/10/15.
 */
public class Rate implements Serializable {

    private Integer id_rate;
    private Integer id_clip;
    private Integer id_customer;
    private Integer rate;

    private String token;

    public Integer getId_rate() {
        return id_rate;
    }

    public void setId_rate(Integer id_rate) {
        this.id_rate = id_rate;
    }

    public Integer getId_clip() {
        return id_clip;
    }

    public void setId_clip(Integer id_clip) {
        this.id_clip = id_clip;
    }

    public Integer getId_customer() {
        return id_customer;
    }

    public void setId_customer(Integer id_customer) {
        this.id_customer = id_customer;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
