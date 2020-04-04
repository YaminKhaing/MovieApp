package com.example.moviesapp.Interactor;

import com.example.moviesapp.model.LoginRequestBody;
import com.example.moviesapp.model.ProfileInfoModel;
import com.example.moviesapp.model.ProfileInfoModelImpl;
import com.example.moviesapp.model.SessionRequestBody;
import com.example.moviesapp.util.ServiceHelper;

import io.reactivex.Observable;

public class LoginInteractor {

    private ServiceHelper.ApiService mService;
    private ProfileInfoModelImpl profileInfoModelImpl;

    public LoginInteractor(ServiceHelper.ApiService apiService){
        this.mService=apiService;
        this.profileInfoModelImpl=new ProfileInfoModelImpl();
    }

    public Observable<ProfileInfoModel> getRequestToken(){
        return profileInfoModelImpl.getRequestTokenFromApi(mService);

    }

    public Observable<ProfileInfoModel> getLoginValidate(LoginRequestBody requestBody){
        return profileInfoModelImpl.getLoginValidteFromApi(mService,requestBody);
    }

    public Observable<ProfileInfoModel> getSession(SessionRequestBody sessionRequestBody){
        return profileInfoModelImpl.getSessionByRequestTokenFromApi(mService,sessionRequestBody);
    }
}
