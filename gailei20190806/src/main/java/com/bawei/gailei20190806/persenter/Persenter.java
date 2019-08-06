package com.bawei.gailei20190806.persenter;

import android.util.Log;

import com.bawei.gailei20190806.utils.HttpUtils;

/**
 * author: 盖磊
 * data: 2019/8/6 10:10:22
 * function:
 */
public class Persenter {
    private IView iView;

    public Persenter(IView iView) {
        this.iView = iView;
    }

    public void getData(final String path){
        HttpUtils.getInstance().getJson(path, new HttpUtils.CallBackT() {
            @Override
            public void onSuccess(Object obj) {
                if (iView != null){
                    iView.onSuccess(obj);
                }
            }

            @Override
            public void onError(String msg) {
                if (iView != null){
                    iView.onError(msg);
                }
            }
        });
    }

    public void onDesctory(){
        if (iView != null){
            iView = null;
        }
    }
}
