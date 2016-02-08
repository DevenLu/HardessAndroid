package com.moduth.hardess.http;

import com.google.gson.Gson;
import com.moduth.hardess.http.Interceptor.HeadInterceptor;
import com.moduth.hardess.modle.MdthAnalysisModel;
import com.moduth.hardess.utils.HttpUtils;

import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Abner on 16/1/14.
 * Email nimengbo@gmail.com
 * github https://github.com/nimengbo
 * 请求的单例类
 */
public class MdthRequest {

    private static int SUCCESS_CODE = 0;

    private MdthApi mMdthHttpApi;   //HTTP请求

    private Gson gson;

    private static MdthRequest mInstance;


    public MdthApi getMdthHttpApi() {
        return mMdthHttpApi;
    }


    public static MdthRequest getInstance() {
        if (mInstance == null) {
            synchronized (MdthRequest.class) {
                if (mInstance == null) {
                    mInstance = new MdthRequest();
                }
            }
        }
        return mInstance;
    }

    public MdthRequest() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient())
                .build();
        mMdthHttpApi = retrofit.create(MdthApi.class);
        gson = new Gson();
    }


    /**
     * 创建OKHttpClient
     *
     * @return
     */
    private OkHttpClient okHttpClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        return new OkHttpClient.Builder().addInterceptor(logging).addInterceptor(new HeadInterceptor()).build();
    }

    /**
     * 请求shopping接口的
     *
     * @param params     传入的参数
     * @param methodName 方法名
     * @param tClass     需要解析的类型
     * @return
     */
    public <T> Observable<T> retrofitPost(Map<String, String> params, final String methodName, final Class<T> tClass) {
        return HttpUtils.initParams(params, methodName)
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<Map<String, String>, Observable<MdthAnalysisModel>>() {
                    @Override
                    public Observable<MdthAnalysisModel> call(Map<String, String> stringStringMap) {
                        return mMdthHttpApi.testPost(stringStringMap);
                    }
                })
                .flatMap(new Func1<MdthAnalysisModel, Observable<T>>() {
                    @Override
                    public Observable<T> call(MdthAnalysisModel response) {
                        if (response.code == SUCCESS_CODE) {
                            T data = gson.fromJson(response.data.toString(), tClass);
                            return Observable.just(data);
                        } else {
                            return Observable.error(new Exception("haha"));
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });


    }


}
