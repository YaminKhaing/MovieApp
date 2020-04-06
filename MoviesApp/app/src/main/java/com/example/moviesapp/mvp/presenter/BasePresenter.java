package com.example.moviesapp.mvp.presenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void onTerminate() {
        dispose();
    }

    void addDisposableOberver(Disposable disposable) {
        this.compositeDisposable.add(disposable);
    }

    private void dispose() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

}
