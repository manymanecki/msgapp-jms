package edu.pjatk.tpo.msgappjsm;

public class MainModel {
    private final static MainModel instance = new MainModel();

    public static MainModel getInstance(){
        return instance;
    }

    private String username = "";

    public String getUsername(){
        return username;
    }
}
