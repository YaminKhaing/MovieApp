package com.example.moviesapp.mvp.view;

public interface LoginView extends BaseView{

    void saveLoginData(String sessionId);
    void onLoginComplete();
    void checkLogin();

}
