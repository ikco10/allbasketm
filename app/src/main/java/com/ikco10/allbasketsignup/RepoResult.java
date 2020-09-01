package com.ikco10.allbasketsignup;

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