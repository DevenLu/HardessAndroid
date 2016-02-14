package com.moduth.hardess.http;



import com.moduth.hardess.domain.model.MdthAnalysisModel;
import com.moduth.hardess.domain.model.Repository;

import java.util.List;
import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Abner on 16/1/14.
 * Email nimengbo@gmail.com
 * github https://github.com/nimengbo
 * 接口API的定义类
 */
public interface MdthApi {

    /**
     * 请求shopping接口用的
     * @param options
     * @return
     */
    @FormUrlEncoded
    @POST ("xxx.xxx")
    Observable<MdthAnalysisModel> testPost(@FieldMap Map<String, String> options);


    @GET("users/{username}/repos")
    Observable<List<Repository>> getRepositoriesByUserName(@Path("username") String userName );
}
