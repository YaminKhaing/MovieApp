package com.example.moviesapp.mvp.presenter;

import com.example.moviesapp.mvp.view.LoginView;

public interface LoginPresenter {
    void onUIReady();
    void onAttachView(LoginView view);

    void onClickLogin(String username,String password);
}
