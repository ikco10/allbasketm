package com.ikco10.allbasketm;

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
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ikco10.allbasketm.Utils.ClearEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressLint("ValidFragment")
public class FragmentMemberModify2 extends Fragment implements PassMemberSignupAddr, PassMemberModify {

    private ClearEditText mNameET, mCelET, mTelET, mAddrET, mEmailET, mCashInfoET;
    private RadioButton mMaleRD, mFemaleRD;
    private CheckBox mCashTypeCB, mSmsCB;

    FragmentMemberModify2() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_membermodify2, container, false);

        mNameET = view.findViewById(R.id.membersearch_name);
        mSmsCB = view.findViewById(R.id.sms);
        mCelET = view.findViewById(R.id.cel);
        mTelET = view.findViewById(R.id.membersearch_tel);
        mAddrET = view.findViewById(R.id.membersearch_addr);
        mEmailET = view.findViewById(R.id.email);
        mMaleRD = view.findViewById(R.id.fragmentmodify2_male);
        mFemaleRD = view.findViewById(R.id.fragmentmodify2_female);
        mCashInfoET = view.findViewById(R.id.cashinfo);
        mCashTypeCB = view.findViewById(R.id.cashtype);
        Button addrBt = view.findViewById(R.id.addrsearch);

        addrBt.setOnClickListener(view1 -> {
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                if (getParentFragment() != null) {
                    if (getView() != null)
                        ((DialogMemberModify) getParentFragment()).mView = getView().findFocus();
                    ((DialogMemberModify) getParentFragment()).setCurrentItem(2);
                    ((DialogMemberModify) getParentFragment()).mNext.setVisibility(View.INVISIBLE);
                    ((DialogMemberModify) getParentFragment()).mTitle.setText("주소 검색");
                    if (getActivity() != null) {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (imm != null)
                            imm.showSoftInput(view1, 0);
                    }
                }
            }, 200);
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

                if (cursorPosition > 12 && mCelET.getText() != null) {
                    check(mCelET.getText().toString(), "0");
                }
            }
        });

        mTelET.addTextChangedListener(new TextWatcher() {
            int cursorPosition = 0;

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mTelET.removeTextChangedListener(this);
                try {
                    cursorPosition = mTelET.getSelectionStart();
                    if (editable.length() > 0 && mTelET.getText() != null) {
                        StringBuilder tempIp = new StringBuilder();
                        StringBuilder newIp = new StringBuilder();
                        String strIp = mTelET.getText().toString();
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
                            if (mTelET.getText().toString().substring(0, 2).equals("02")) {
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
                            } else if (mTelET.getText().toString().substring(0, 1).equals("1")) {
                                if (count == 4 || count == 8) {
                                    newIp.append("-");
                                    newIp.append(tempIp.charAt(count));
                                    cursorPosition++;
                                } else {
                                    newIp.append(tempIp.charAt(count));
                                }
                            } else if (mTelET.getText().toString().substring(0, 1).equals("0")) {
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
                        mTelET.setText(newIp.toString());
                        mTelET.setSelection(Math.min(newIp.length(), cursorPosition));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mTelET.addTextChangedListener(this);

                if (cursorPosition > 11 && mTelET.getText() != null) {
                    check(mTelET.getText().toString(), "1");
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

        Call<FragmentMemberSignup2.Result> call;

        call = retrofitService.memcheck(num, type);
        call.enqueue(new Callback<FragmentMemberSignup2.Result>() {
            @Override
            public void onResponse(@NonNull Call<FragmentMemberSignup2.Result> call, @NonNull Response<FragmentMemberSignup2.Result> response) {
                FragmentMemberSignup2.Result result = response.body();

                if (type.equals("0") && result != null && result.getResult().equals("exist")) {
                    mCelET.setError("등록된 휴대폰 번호입니다 (" + result.getName() + "님)");
                } else if (type.equals("1") && result != null && result.getResult().equals("exist")) {
                    mTelET.setError("등록된 전화 번호입니다 (" + result.getName() + "님)");
                }
            }

            @Override
            public void onFailure(@NonNull Call<FragmentMemberSignup2.Result> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "네트워크 오류", Toast.LENGTH_SHORT).show();
                call.cancel();
            }

        });
    }

    @Override
    public void passAddr(String addr) {
        if (getParentFragment() != null) {
            ((DialogMemberModify) getParentFragment()).mTitle.setText("회원 정보");
            ((DialogMemberModify) getParentFragment()).mNext.setText("수정");
            ((DialogMemberModify) getParentFragment()).mNext.setVisibility(View.VISIBLE);
        }
        mAddrET.setText(addr);
        mAddrET.requestFocus();
        if (mAddrET.getText() != null) {
            mAddrET.setSelection(mAddrET.getText().toString().length());
        }
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (getActivity() != null) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.showSoftInput(mAddrET, 0);
            }
        }, 350);

    }

    @Override
    public void passMember(String code, String bcode, String sex, String name, String cel, String tel, String addr, String zip, String email, String apply, String cashinfo, String cashtype) {

        if (getParentFragment() != null) {
            ((DialogMemberModify) getParentFragment()).mBcode = bcode;
            ((DialogMemberModify) getParentFragment()).mApply = apply;
        }

        mNameET.setText(name);
        mCelET.setText(cel);

        if (!cel.equals(tel))
            mTelET.setText(tel);

        if (zip.length() > 0) {
            StringBuilder stringBuilder = new StringBuilder("(");
            stringBuilder.append(zip);
            stringBuilder.append(") ");
            stringBuilder.append(addr);
            mAddrET.setText(stringBuilder);
        } else {
            mAddrET.setText(addr);
        }

        mEmailET.setText(email);

        if (sex.equals("1")) {
            mMaleRD.setChecked(true);
            mFemaleRD.setChecked(false);
            mMaleRD.jumpDrawablesToCurrentState();
            mFemaleRD.jumpDrawablesToCurrentState();
        } else {
            mMaleRD.setChecked(false);
            mFemaleRD.setChecked(true);
            mMaleRD.jumpDrawablesToCurrentState();
            mFemaleRD.jumpDrawablesToCurrentState();
        }

        mCashInfoET.setText(cashinfo);
        if (cashtype.equals("1")) {
            mCashTypeCB.setChecked(true);
            mCashTypeCB.jumpDrawablesToCurrentState();
        } else {
            mCashTypeCB.setChecked(false);
            mCashTypeCB.jumpDrawablesToCurrentState();
        }

        String applytemp = String.format("%17s", Integer.toBinaryString(Integer.parseInt(apply))).replace(' ', '0');

        if (applytemp.substring(4, 5).equals("1")) {
            mSmsCB.setChecked(false);
            mSmsCB.jumpDrawablesToCurrentState();
        } else {
            mSmsCB.setChecked(true);
            mSmsCB.jumpDrawablesToCurrentState();
        }

        mAddrET.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        mAddrET.setRawInputType(InputType.TYPE_CLASS_TEXT);

    }

}
