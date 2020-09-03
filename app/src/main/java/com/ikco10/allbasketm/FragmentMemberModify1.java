package com.ikco10.allbasketm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ikco10.allbasketm.Utils.ClearEditText;
import com.ikco10.allbasketm.Utils.RetrofitApi;

import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("ValidFragment")
public class FragmentMemberModify1 extends Fragment {

    private CompositeDisposable mDisposable = new CompositeDisposable();
    private ClearEditText mNameET, mCelET;

    FragmentMemberModify1() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_membermodify1, container, false);

        mNameET = view.findViewById(R.id.fragmentmodify1_nameet);
        mCelET = view.findViewById(R.id.fragmentmodify1_celet);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            mNameET.requestFocus();
            if (getActivity() != null) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.showSoftInput(mNameET, 0);
            }
        }, 800);

        mNameET.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (getParentFragment() != null) {

                    if (mCelET.getText() != null && mCelET.getText().length() > 12) {

                        RetrofitApi retrofitApi = new RetrofitApi();
                        Observable<RepoMember> observable = retrofitApi.getApi().memverify(Objects.requireNonNull(mNameET.getText()).toString(), mCelET.getText().toString());

                        mDisposable.clear();
                        mDisposable.add(observable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(result -> {
                                            if (result.getResult().equals("exist")) {
                                                ((DialogMemberModify) getParentFragment()).mNext.setEnabled(true);
                                                ((DialogMemberModify) getParentFragment()).mNext.setTextColor(getResources().getColor(R.color.colorPrimary));
                                            } else {
                                                ((DialogMemberModify) getParentFragment()).mNext.setEnabled(false);
                                                ((DialogMemberModify) getParentFragment()).mNext.setTextColor(getResources().getColor(R.color.md_grey_400));
                                            }
                                        },
                                        e -> {
                                        },
                                        () -> {
                                        }
                                )
                        );
                    }
                }
            }
        });

        mCelET.addTextChangedListener(new TextWatcher() {
            int cursorPosition = 0;

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mCelET.removeTextChangedListener(this);
                try {
                    cursorPosition = mCelET.getSelectionStart();
                    if (editable.length() > 0 && mCelET.getText() != null) {
                        StringBuilder tempIp = new StringBuilder();
                        StringBuilder newIp = new StringBuilder();
                        String strIp = mCelET.getText().toString();
                        String[] tempParamArr = strIp.split("-");
                        if (tempParamArr.length > 0) {
                            cursorPosition -= (tempParamArr.length - 1);
                            for (String aTempParamArr : tempParamArr) {
                                tempIp.append(aTempParamArr);
                            }
                        } else {
                            tempIp = new StringBuilder(strIp);
                        }
                        for (int count = 0; count < tempIp.length(); count++) {
                            if (mCelET.getText().toString().substring(0, 2).equals("02")) {
                                if (editable.length() < 12) {
                                    if (count == 2 || count == 5) {
                                        newIp.append("-");
                                        newIp.append(tempIp.charAt(count));
                                        cursorPosition++;
                                    } else {
                                        newIp.append(tempIp.charAt(count));
                                    }
                                } else {
                                    if (count == 2 || count == 6) {
                                        newIp.append("-");
                                        newIp.append(tempIp.charAt(count));
                                        cursorPosition++;
                                    } else {
                                        newIp.append(tempIp.charAt(count));
                                    }
                                }
                            } else if (mCelET.getText().toString().substring(0, 1).equals("1")) {
                                if (count == 4 || count == 8) {
                                    newIp.append("-");
                                    newIp.append(tempIp.charAt(count));
                                    cursorPosition++;
                                } else {
                                    newIp.append(tempIp.charAt(count));
                                }
                            } else if (mCelET.getText().toString().substring(0, 1).equals("0")) {
                                if (editable.length() < 13) {
                                    if (count == 3 || count == 6) {
                                        newIp.append("-");
                                        newIp.append(tempIp.charAt(count));
                                        cursorPosition++;
                                    } else {
                                        newIp.append(tempIp.charAt(count));
                                    }
                                } else {
                                    if (count == 3 || count == 7) {
                                        newIp.append("-");
                                        newIp.append(tempIp.charAt(count));
                                        cursorPosition++;
                                    } else {
                                        newIp.append(tempIp.charAt(count));
                                    }
                                }
                            } else {
                                if (count == 3 || count == 8) {
                                    newIp.append("-");
                                    newIp.append(tempIp.charAt(count));
                                    cursorPosition++;
                                } else {
                                    newIp.append(tempIp.charAt(count));
                                }
                            }
                        }
                        mCelET.setText(newIp.toString());
                        mCelET.setSelection(Math.min(newIp.length(), cursorPosition));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mCelET.addTextChangedListener(this);

                if (getParentFragment() != null) {
                    ((DialogMemberModify) getParentFragment()).mNext.setEnabled(false);
                    ((DialogMemberModify) getParentFragment()).mNext.setTextColor(Color.parseColor("#FFFFFF"));
                }

                if (cursorPosition > 12 && mCelET.getText() != null) {

                    RetrofitApi retrofitApi = new RetrofitApi();
                    Observable<RepoMember> observable = retrofitApi.getApi().memverify(Objects.requireNonNull(mNameET.getText()).toString(), mCelET.getText().toString());

                    mDisposable.clear();
                    mDisposable.add(observable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(result -> {
                                        if (result.getResult().equals("exist")) {

                                            ((DialogMemberModify) getParentFragment()).mNext.setEnabled(true);
                                            ((DialogMemberModify) getParentFragment()).mNext.setTextColor(getResources().getColor(R.color.colorPrimary));

                                            Fragment fragment = null;
                                            if (getFragmentManager() != null)
                                                fragment = getFragmentManager().getFragments().get(1);

                                            if (fragment != null) {
                                                ((FragmentMemberModify2) fragment).passMember(result.getCode(),
                                                        result.getBcode(),
                                                        result.getSex(),
                                                        result.getName(),
                                                        result.getCel(),
                                                        result.getTel(),
                                                        result.getAddr(),
                                                        result.getZip(),
                                                        result.getEmail(),
                                                        result.getApply(),
                                                        result.getCashinfo(),
                                                        result.getCashtype()
                                                        );
                                            }

                                        } else {
                                            ((DialogMemberModify) getParentFragment()).mNext.setEnabled(false);
                                            ((DialogMemberModify) getParentFragment()).mNext.setTextColor(getResources().getColor(R.color.md_grey_400));
                                        }
                                    },
                                    e -> {
                                    },
                                    () -> {
                                    }
                            )
                    );
                }
            }
        });

        return view;

    }

    @Override
    public void onPause() {
        super.onPause();
        mDisposable.clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDisposable.dispose();
    }

}
