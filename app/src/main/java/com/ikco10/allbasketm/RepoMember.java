package com.ikco10.allbasketm;

import com.google.gson.annotations.SerializedName;

class RepoMember {

    @SerializedName("RT")
    private String mResult;

    @SerializedName("CD")
    private String mCode;

    @SerializedName("BC")
    private String mBcode;

    @SerializedName("SX")
    private String mSex;

    @SerializedName("NM")
    private String mName;

    @SerializedName("CL")
    private String mCel;

    @SerializedName("TL")
    private String mTel;

    @SerializedName("AD")
    private String mAddr;

    @SerializedName("ZP")
    private String mZip;

    @SerializedName("EM")
    private String mEmail;

    @SerializedName("AL")
    private String mApply;

    @SerializedName("CI")
    private String mCashInfo;

    @SerializedName("CT")
    private String mCashType;

    public String getResult() {
        return mResult;
    }

    public String getCode() {
        return mCode;
    }

    public String getBcode() {
        return mBcode;
    }

    public String getSex() {
        return mSex;
    }

    public String getName() {
        return mName;
    }

    public String getTel() {
        return mTel;
    }

    public String getCel() {
        return mCel;
    }

    public String getAddr() {
        return mAddr;
    }

    public String getZip() {
        return mZip;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getApply() {
        return mApply;
    }

    public String getCashinfo() {
        return mCashInfo;
    }

    public String getCashtype() {
        return mCashType;
    }
}