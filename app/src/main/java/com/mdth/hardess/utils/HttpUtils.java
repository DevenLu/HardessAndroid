package com.mdth.hardess.utils;

import java.util.Map;

import rx.Observable;

/**
 * Created by Abner on 16/1/14.
 * Email nimengbo@gmail.com
 * github https://github.com/nimengbo
 * 初始化参数
 */
public class HttpUtils {


    /**
     * 处理params
     *
     * @param params
     * @return
     */
    public static Observable<Map<String, String>> initParams(final Map<String, String> params, String methodName) {





        return Observable.just(params);
    }
}
