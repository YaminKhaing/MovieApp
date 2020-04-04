package com.example.moviesapp.mvp.presenter;

import android.util.Log;

import com.example.moviesapp.Interactor.LoginInteractor;
import com.example.moviesapp.R;
import com.example.moviesapp.model.LoginRequestBody;
import com.example.moviesapp.model.ProfileInfoModel;
import com.example.moviesapp.model.SessionRequestBody;
import com.example.moviesapp.mvp.view.LoginView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public class LoginPresenterImpl extends BasePresenter implements LoginPresenter{

    private LoginView loginView=null;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginInteractor loginInteractor){
        this.loginInteractor=loginInteractor;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        this.loginView=null;
    }

    @Override
    public void onUIReady() {
        this.loginView.checkLogin();
    }

    @Override
    public void onAttachView(LoginView view) {
        this.loginView=view;
    }

    @Override
    public void onClickLogin(String username, String password) {
        this.loginView.showLoading();

        if(username.length()==0){

            this.loginView.showDialogMsg(loginView.context().getResources().getString(R.string.error), loginView.context().getResources().getString(R.string.please_fill_in_username));
        } else if(password.length()==0){
            this.loginView.showDialogMsg(loginView.context().getResources().getString(R.string.error),loginView.context().getResources().getString(R.string.please_fill_in_password));
        } else{
            getRequestToken(username,password);
        }
    }

    private void getRequestToken(final String username, final String password) {

        this.loginInteractor.getRequestToken().subscribe(new Observer<ProfileInfoModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposableOberver(d);
            }

            @Override
            public void onNext(ProfileInfoModel profileInfoModel) {
                if(profileInfoModel != null){
                    if(profileInfoModel.isFailure() || profileInfoModel.getRequest_token().length() == 0){
                        loginView.showDialogMsg(loginView.context().getResources().getString(R.string.error), loginView.context().getResources().getString(R.string.error_retrieving_data));
                    } else{
                        validateLogin(username,password,profileInfoModel.getRequest_token());
                    }
                } else {
                    loginView.showDialogMsg(loginView.context().getResources().getString(R.string.error_connecting),
                            loginView.context().getResources().getString(R.string.please_check_your_internet_connection));
                }
            }

            @Override
            public void onError(Throwable e) {
                loginView.showDialogMsg(loginView.context().getResources().getString(R.string.error_connecting),e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    private static final String TAG="LoginPresenterImpl";

    public void validateLogin(String username, String password, final String requestToken){
        Log.e(TAG, "validateLogin : " + username +" "+password+" "+requestToken);
        this.loginInteractor.getLoginValidate(new LoginRequestBody(username,password,requestToken)).subscribe(new Observer<ProfileInfoModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposableOberver(d);
            }

            @Override
            public void onNext(ProfileInfoModel profileInfoModel) {
                if(profileInfoModel != null){
                    if(profileInfoModel.getStatus_code() == 30){
                        loginView.showDialogMsg(loginView.context().getResources().getString(R.string.error), loginView.context().getResources().getString(R.string.incorrect_username_or_password));
                    } else if(profileInfoModel.isFailure()){
                        loginView.showDialogMsg(loginView.context().getResources().getString(R.string.error), loginView.context().getResources().getString(R.string.error_retrieving_data));
                    } else{
                       getSessionId(requestToken);
                    }
                } else{
                    loginView.showDialogMsg(loginView.context().getResources().getString(R.string.error_connecting), loginView.context().getResources().getString(R.string.please_check_your_internet_connection));
                }
            }

            @Override
            public void onError(Throwable e) {
                HttpException httpException= (HttpException) e;

                if(httpException.code() == 401){
                    loginView.showDialogMsg(loginView.context().getResources().getString(R.string.error), loginView.context().getResources().getString(R.string.incorrect_username_or_password));
                } else {
                    loginView.showDialogMsg(loginView.context().getResources().getString(R.string.error_connecting),e.getLocalizedMessage());
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void getSessionId(String requestToken){
        this.loginInteractor.getSession(new SessionRequestBody(requestToken)).subscribe(new Observer<ProfileInfoModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposableOberver(d);
            }

            @Override
            public void onNext(ProfileInfoModel profileInfoModel) {
                if (profileInfoModel != null) {

                    if (profileInfoModel.isFailure()) {
                        loginView.showDialogMsg(loginView.context().getResources().getString(R.string.error),
                                loginView.context().getResources().getString(R.string.error_retrieving_data));
                    } else {
                        loginView.saveLoginData(profileInfoModel.getSession_id());
                    }

                } else {

                    loginView.showDialogMsg(loginView.context().getResources().getString(R.string.error_connecting),
                            loginView.context().getResources().getString(R.string.please_check_your_internet_connection));
                }
            }

            @Override
            public void onError(Throwable e) {
                loginView.showDialogMsg(loginView.context().getResources().getString(R.string.error_connecting), e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                loginView.onLoginComplete();
                loginView.showToastMsg("login success");
            }
        });
    }

}
