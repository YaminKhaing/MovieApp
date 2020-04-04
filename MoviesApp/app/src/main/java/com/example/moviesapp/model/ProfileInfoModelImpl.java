package com.example.moviesapp.model;

import com.example.moviesapp.util.ServiceHelper;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.moviesapp.util.AppConstant.DEVELOPER_KEY;

public class ProfileInfoModelImpl implements IProfileModel{
    @Override
    public Observable<ProfileInfoModel> getRequestTokenFromApi(ServiceHelper.ApiService service) {
        return service.getRequestToken(DEVELOPER_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<ProfileInfoModel> getLoginValidteFromApi(ServiceHelper.ApiService service, LoginRequestBody requestBody) {
        return service.getLoginValidate(DEVELOPER_KEY,requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<ProfileInfoModel> getSessionByRequestTokenFromApi(ServiceHelper.ApiService service, SessionRequestBody sessionRequestBody) {
        return service.getSession(DEVELOPER_KEY,sessionRequestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
