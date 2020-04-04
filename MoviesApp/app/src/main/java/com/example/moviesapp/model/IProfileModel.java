package com.example.moviesapp.model;

import com.example.moviesapp.util.ServiceHelper;

import io.reactivex.Observable;

public interface IProfileModel {
    Observable<ProfileInfoModel> getRequestTokenFromApi(ServiceHelper.ApiService service);
    Observable<ProfileInfoModel> getLoginValidteFromApi(ServiceHelper.ApiService service,LoginRequestBody requestBody);
    Observable<ProfileInfoModel> getSessionByRequestTokenFromApi(ServiceHelper.ApiService service, SessionRequestBody sessionRequestBody);
}
