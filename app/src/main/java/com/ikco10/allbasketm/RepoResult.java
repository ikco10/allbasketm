package com.ikco10.allbasketm;

import com.google.gson.annotations.SerializedName;

class RepoResult {

    @SerializedName("result")
    private String mResult;

    public RepoResult(String result) {
        mResult = result;
    }

    public String getResult() {
        return mResult;
    }

}