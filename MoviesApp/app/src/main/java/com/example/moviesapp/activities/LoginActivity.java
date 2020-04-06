package com.example.moviesapp.activities;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;

import com.example.moviesapp.Interactor.LoginInteractor;
import com.example.moviesapp.R;
import com.example.moviesapp.common.BaseActivity;
import com.example.moviesapp.custom_control.MyanProgressDialog;
import com.example.moviesapp.mvp.presenter.LoginPresenterImpl;
import com.example.moviesapp.mvp.view.LoginView;
import com.example.moviesapp.util.ServiceHelper;
import com.example.moviesapp.util.SharePreferenceHelper;
import butterknife.BindView;

public class LoginActivity extends BaseActivity implements LoginView {

    @BindView(R.id.et_user_name_id)
    EditText etUserName;

    @BindView(R.id.et_password_id)
    EditText etPassword;

    @BindView(R.id.btn_login_id)
    Button btnLogin;

    private LoginPresenterImpl loginPresenter;

    private MyanProgressDialog mDialog;

    private ServiceHelper.ApiService mService;

    private SharePreferenceHelper sharePreferenceHelper;

    private static final String TAG = "LoginActivity";


    @Override
    protected int getLayoutResource() {
        return R.layout.login_activity;
    }

    @Override
    protected void setUpContents(Bundle savedInstanceState) {
        setupToolbar(false);
        setupToolbarText(getString(R.string.login));
        init();

    }

    private void init() {
        mService=ServiceHelper.getClient(this);

        mDialog=new MyanProgressDialog(this);

        sharePreferenceHelper=new SharePreferenceHelper(this);

        loginPresenter=new LoginPresenterImpl(new LoginInteractor(this.mService));

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.onClickLogin(etUserName.getText().toString(), etPassword.getText().toString());
            }
        });

        loginPresenter.onAttachView(this);
        loginPresenter.onUIReady();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.onTerminate();
    }

    @Override
    public void saveLoginData(String sessionId) {
        Log.e(TAG, "saveLoginData: " + sessionId );
        sharePreferenceHelper.setLogin(sessionId);

    }

    @Override
    public void onLoginComplete() {
        this.startActivity(MainActivity.getMainActivityIntent(this));
        this.finish();
    }

    @Override
    public void checkLogin() {
        if (sharePreferenceHelper.isLogin()) {
            onLoginComplete();
        }
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public void showLoading() {
        if (!isFinishing()) {
            mDialog.showDialog();
        }
    }

    @Override
    public void hideLoading() {
        if (!isFinishing()) {
            mDialog.hideDialog();
        }
    }

    @Override
    public void showToastMsg(String msg) {
        this.hideLoading();
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialogMsg(String title, String msg) {
        this.hideLoading();
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(getString(R.string.ok), null).show();
    }
}
