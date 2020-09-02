package com.ikco10.allbasketsignup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.annotations.SerializedName;
import com.ikco10.allbasketsignup.Utils.ClearEditText;
import com.ikco10.allbasketsignup.Utils.OnSingleClickListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressLint("ValidFragment")
public class FragmentMemberSignup2 extends Fragment implements PassMemberSignupAddr {

    private ClearEditText mCel, mTel, mAddr;
    private TextView mCelOverLap, mTelOverLap;

    FragmentMemberSignup2() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_membersignup2, container, false);

        mCel = view.findViewById(R.id.cel);
        mCelOverLap = view.findViewById(R.id.celOverlap);
        mTel = view.findViewById(R.id.membersearch_tel);
        mTelOverLap = view.findViewById(R.id.telOverlap);
        mAddr = view.findViewById(R.id.membersearch_addr);
        Button addrBt = view.findViewById(R.id.addrsearch);

        mAddr.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        mAddr.setRawInputType(InputType.TYPE_CLASS_TEXT);

        addrBt.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    if (getParentFragment() != null) {
                        if (getView() != null)
                            ((DialogMemberSignup) getParentFragment()).mView = getView().findFocus();
                        ((DialogMemberSignup) getParentFragment()).setCurrentItem(2);
                        ((DialogMemberSignup) getParentFragment()).mNext.setVisibility(View.INVISIBLE);
                        ((DialogMemberSignup) getParentFragment()).mTitle.setText("주소 검색");
                        if (getActivity() != null) {
                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            if (imm != null)
                                imm.showSoftInput(view, 0);
                        }
                    }
                }, 300);
            }
        });

        mCel.addTextChangedListener(new TextWatcher() {
            int cursorPosition = 0;

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mCel.removeTextChangedListener(this);
                mCel.setError(null);
                mCelOverLap.setText("");

                try {
                    cursorPosition = mCel.getSelectionStart();
                    if (editable.length() > 0 && mCel.getText() != null) {
                        StringBuilder tempIp = new StringBuilder();
                        StringBuilder newIp = new StringBuilder();
                        String strIp = mCel.getText().toString();
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
                            if (mCel.getText().toString().substring(0, 2).equals("02")) {
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
                            } else if (mCel.getText().toString().substring(0, 1).equals("1")) {
                                if (count == 4 || count == 8) {
                                    newIp.append("-");
                                    newIp.append(tempIp.charAt(count));
                                    cursorPosition++;
                                } else {
                                    newIp.append(tempIp.charAt(count));
                                }
                            } else if (mCel.getText().toString().substring(0, 1).equals("0")) {
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
                        mCel.setText(newIp.toString());
                        mCel.setSelection(Math.min(newIp.length(), cursorPosition));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mCel.addTextChangedListener(this);

                if (cursorPosition > 12 && mCel.getText() != null) {
                    check(mCel.getText().toString(), "0");
                }
            }
        });

        mTel.addTextChangedListener(new TextWatcher() {
            int cursorPosition = 0;

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mTel.removeTextChangedListener(this);
                mTel.setError(null);
                mTelOverLap.setText("");

                try {
                    cursorPosition = mTel.getSelectionStart();
                    if (editable.length() > 0 && mTel.getText() != null) {
                        StringBuilder tempIp = new StringBuilder();
                        StringBuilder newIp = new StringBuilder();
                        String strIp = mTel.getText().toString();
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
                            if (mTel.getText().toString().substring(0, 2).equals("02")) {
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
                            } else if (mTel.getText().toString().substring(0, 1).equals("1")) {
                                if (count == 4 || count == 8) {
                                    newIp.append("-");
                                    newIp.append(tempIp.charAt(count));
                                    cursorPosition++;
                                } else {
                                    newIp.append(tempIp.charAt(count));
                                }
                            } else if (mTel.getText().toString().substring(0, 1).equals("0")) {
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
                        mTel.setText(newIp.toString());
                        mTel.setSelection(Math.min(newIp.length(), cursorPosition));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mTel.addTextChangedListener(this);

                if (cursorPosition > 11 && mTel.getText() != null) {
                    check(mTel.getText().toString(), "1");
                }

            }
        });

        return view;
    }

    private void check(String num, final String type) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://59.12.186.68:55282/allbasket/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        Call<Result> call;

        call = retrofitService.memcheck(num, type);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(@NonNull Call<Result> call, @NonNull Response<Result> response) {
                Result result = response.body();

                if (type.equals("0") && result != null && result.getResult().equals("exist")) {
                    mCel.setError("");
                    mCelOverLap.setText("등록된 휴대폰 번호입니다");
                } else if (type.equals("1") && result != null && result.getResult().equals("exist")) {
                    mTel.setError("");
                    mTelOverLap.setText("등록된 전화 번호입니다");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "네트워크 오류", Toast.LENGTH_SHORT).show();
                call.cancel();
            }

        });
    }

    public static class Result {
        @SerializedName("result")
        public String result;

        @SerializedName("name")
        public String name;

        public String getResult() {
            return result;
        }

        public String getName() {
            return name;
        }

    }

    @Override
    public void passAddr(String addr) {
        if (getParentFragment() != null) {
            ((DialogMemberSignup) getParentFragment()).mTitle.setText("회원 가입");
            ((DialogMemberSignup) getParentFragment()).mNext.setText("등록");
            ((DialogMemberSignup) getParentFragment()).mNext.setVisibility(View.VISIBLE);
        }
        mAddr.setText(addr);
        mAddr.requestFocus();
        if (mAddr.getText() != null) {
            mAddr.setSelection(mAddr.getText().toString().length());
        }
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (getActivity() != null) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.showSoftInput(mAddr, 0);
                }
            }
        }, 350);
    }
}
