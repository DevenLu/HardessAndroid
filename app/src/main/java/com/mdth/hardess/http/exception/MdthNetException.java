package com.mdth.hardess.http.exception;

import com.mdth.hardess.modle.MdthAnalysisModel;

/**
 * Created by Abner on 16/1/27.
 * Email nimengbo@gmail.com
 * github https://github.com/nimengbo
 * 请求异常类
 */
public class MdthNetException extends Exception {
    private static final long serialVersionUID = -5578896338931274963L;

    private MdthAnalysisModel response;       //服务器返回的错误信息

    public MdthNetException() {
    }

    public MdthNetException(MdthAnalysisModel response) {
        super(response.message);
        this.response = response;
    }
}
