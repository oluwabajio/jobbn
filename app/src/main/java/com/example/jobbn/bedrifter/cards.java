package com.example.jobbn.bedrifter;

import java.io.Serializable;

/**
 * Created by manel on 9/5/2017.
 */

public class cards implements Serializable {
    private String key;
    private String name;

    public cards(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getUserId() {
        return key;
    }

    public void setUserID(String userID) {
        this.key = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}