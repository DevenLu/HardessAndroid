package com.moduth.hardess.http;

import com.moduth.hardess.domain.model.MdthAnalysisModel;

import java.util.Map;

import rx.Observable;

/**
 * Created by Abner on 16/1/14.
 * Email nimengbo@gmail.com
 * github https://github.com/nimengbo
 * 初始化参数
 */
class HttpUtils {

    public static String API_GET_REPROSITORY_BY_USER = "get_reprository_by_user";

    /**
     * 处理params
     *
     * @param params
     * @return
     */
    public static Observable<Map<String, String>> initParams(final Map<String, String> params, String methodName) {


        return Observable.just(params);
    }


    public static Observable<MdthAnalysisModel> requestApi(MdthApi mdthApi, String methodName, Map<String, String> params) {
        if (API_GET_REPROSITORY_BY_USER.equals(methodName)) {
            return mdthApi.testPost(params);
        } else {
            return null;
        }
    }
}
