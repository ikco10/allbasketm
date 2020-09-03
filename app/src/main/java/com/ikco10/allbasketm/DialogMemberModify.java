package com.ikco10.allbasketm;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.airbnb.lottie.LottieAnimationView;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ikco10.allbasketm.Utils.ClearEditText;
import com.ikco10.allbasketm.Utils.ViewPagerNonSwipe;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressLint("ValidFragment")
public class DialogMemberModify extends DialogFragment {

    private ViewPagerNonSwipe mViewPager;
    private int page = 0;
    private ImageButton mPrevious;
    View mView = null;
    TextView mTitle;
    Button mNext;
    String mBcode, mApply;

    DialogMemberModify() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        if (dialog.getWindow() != null) {
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1)
                setWhiteNavigationBar(dialog);
        }

        return new Dialog(Objects.requireNonNull(getActivity()), getTheme()) {
            @Override
            public void onBackPressed() {
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    if (page == 0) {
                        dismiss();
                    } else if (mViewPager.getCurrentItem() == 2) {
                        mView.requestFocus();
                        page = 1;
                        mViewPager.setCurrentItem(page);
                        mTitle.setText("회원 정보");
                        mNext.setText("변경");
                        mNext.setVisibility(View.VISIBLE);
                    } else if (page == 1 && mTitle.getText().toString().equals("회원 정보")) {
                        page--;
                        mViewPager.setCurrentItem(page);
                        mTitle.setText("회원 확인");
                        mNext.setText("다음");
                        mNext.setVisibility(View.VISIBLE);
                        mPrevious.setImageResource(R.drawable.ic_close);
                        Handler handler1 = new Handler();
                        handler1.postDelayed(() -> {
                            if (getActivity() != null) {
                                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                if (imm != null)
                                    imm.showSoftInput(mViewPager.getChildAt(0).findViewById(R.id.cel), 0);
                            }
                        }, 350);
                    } else {
                        dismiss();
                    }
                }, 300);
            }
        };
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.customDialog);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_membermodify, container, false);

        mTitle = view.findViewById(R.id.modify_title);
        mPrevious = view.findViewById(R.id.modify_back);
        mNext = view.findViewById(R.id.modify_next);

        super.onCreate(savedInstanceState);

        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimationFade;
        }

        DialogAdapter adapter = new DialogAdapter(getChildFragmentManager());
        adapter.addFragment("확인", new FragmentMemberModify1());
        adapter.addFragment("입력", new FragmentMemberModify2());
        adapter.addFragment("주소", new FragmentMemberModify3());
        mViewPager = view.findViewById(R.id.modify_viewPager);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(page);

        mPrevious.setOnClickListener(v -> {
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                if (page == 0) {
                    dismiss();
                } else if (mViewPager.getCurrentItem() == 2) {
                    page = 1;
                    mView.requestFocus();
                    mViewPager.setCurrentItem(page);
                    mTitle.setText("회원 정보");
                    mNext.setText("변경");
                    mNext.setVisibility(View.VISIBLE);
                } else if (page == 1) {
                    page--;
                    mViewPager.setCurrentItem(page);
                    mTitle.setText("회원 확인");
                    mNext.setText("다음");
                    mNext.setVisibility(View.VISIBLE);
                    mPrevious.setImageResource(R.drawable.ic_close);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(() -> {
                        if (getActivity() != null) {
                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            if (imm != null)
                                imm.showSoftInput(mViewPager.getChildAt(0).findViewById(R.id.cel), 0);
                        }
                    }, 350);
                }
            }, 300);
        });

        mNext.setOnClickListener(v -> {
            if (page == 0) {
                if (getActivity() != null) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null && getView() != null)
                        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                }

                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    page++;
                    mViewPager.setCurrentItem(page);
                    mTitle.setText("회원 정보");
                    mNext.setText("변경");
                    mPrevious.setImageResource(R.drawable.ic_back);
                }, 350);
            } else {
                page = 1;
                if (getActivity() != null) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null && getView() != null)
                        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                }

                Handler handler = new Handler();
                handler.postDelayed(() -> {

                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.MONTH, 0);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.KOREA);
                    String date = dateFormat.format(calendar.getTime());
                    String sexTemp, celTemp = "", telTemp = "", key2 = "-1", key4 = "-1";
                    String addrTemp = "", zipTemp = "", emailTemp = "", applyTemp, cashtypetemp;

                    RadioButton male = mViewPager.getChildAt(1).findViewById(R.id.fragmentmodify2_male);
                    CheckBox sms = mViewPager.getChildAt(1).findViewById(R.id.sms);
                    CheckBox cashtype = mViewPager.getChildAt(1).findViewById(R.id.cashtype);
                    ClearEditText name = mViewPager.getChildAt(1).findViewById(R.id.membersearch_name);
                    ClearEditText tel = mViewPager.getChildAt(1).findViewById(R.id.membersearch_tel);
                    ClearEditText cel = mViewPager.getChildAt(1).findViewById(R.id.cel);
                    ClearEditText addr = mViewPager.getChildAt(1).findViewById(R.id.membersearch_addr);
                    ClearEditText email = mViewPager.getChildAt(1).findViewById(R.id.email);
                    ClearEditText cashinfo = mViewPager.getChildAt(1).findViewById(R.id.cashinfo);
                    ScrollView scrollView = mViewPager.getChildAt(1).findViewById(R.id.scrollview);
                    LinearLayout welcome = mViewPager.getChildAt(1).findViewById(R.id.welcome);
                    TextView welcomeName = mViewPager.getChildAt(1).findViewById(R.id.welcomename);
                    TextView confirm = mViewPager.getChildAt(1).findViewById(R.id.confirm);
                    String apply = String.format("%17s", Integer.toBinaryString(Integer.parseInt(mApply))).replace(' ', '0');

                    confirm.setOnClickListener(v1 -> {
                        Handler handler12 = new Handler();
                        handler12.postDelayed(this::dismiss, 500);
                    });

                    if (male.isChecked()) {
                        sexTemp = "1";
                    } else {
                        sexTemp = "0";
                    }

                    if (sms.isChecked()) {
                        apply = apply.substring(0, 4) + "0" + apply.substring(5);
                    } else {
                        apply = apply.substring(0, 4) + "1" + apply.substring(5);
                    }
                    applyTemp = Integer.toString(Integer.parseInt(apply, 2));

                    if (cashtype.isChecked()) {
                        cashtypetemp = "1";
                    } else {
                        cashtypetemp = "0";
                    }

                    if (name.getText() != null && name.getText().toString().isEmpty()) {
                        name.setError("이름를 입력하세요");
                    }
                    if (cel.getText() != null && cel.getText().toString().isEmpty()) {
                        cel.setError("휴대폰 번호를 입력하세요");
                    }
                    if (addr.getText() != null && addr.getText().toString().isEmpty()) {
                        addr.setError("주소를 입력하세요");
                    }

                    if (!name.getText().toString().isEmpty() && !cel.getText().toString().isEmpty() && !addr.getText().toString().isEmpty()) {
                        if (cel.getText() != null)
                            celTemp = cel.getText().toString();
                        if (tel.getText() != null)
                            telTemp = tel.getText().toString();
                        int ok1 = 0, ok2 = 0;

                        if (celTemp.length() > 0) {
                            if (!celTemp.substring(0, 1).equals("0") || celTemp.length() < 12) {
                                cel.setError("휴대폰 번호를 올바르게 입력하세요");
                                ok1 = -1;
                            } else if (celTemp.substring(0, 3).equals("010") && celTemp.length() < 13) {
                                cel.setError("휴대폰 번호를 올바르게 입력하세요");
                                ok1 = -1;
                            } else if (!celTemp.substring(0, 2).equals("01")) {
                                cel.setError("휴대폰 번호를 올바르게 입력하세요");
                                ok1 = -1;
                            }
                        }

                        if (telTemp.length() > 0) {
                            if (!telTemp.substring(0, 1).equals("0") || telTemp.length() < 2) {
                                tel.setError("지역 번호를 입력하세요");
                                ok2 = -1;
                            } else if (telTemp.substring(1, 2).equals("0") || telTemp.substring(1, 2).equals("1") || telTemp.substring(1, 2).equals("7") || telTemp.substring(1, 2).equals("8") || telTemp.substring(1, 2).equals("9")) {
                                tel.setError("전화 번호를 올바르게 입력하세요");
                                ok2 = -1;
                            } else if (telTemp.length() < 11) {
                                tel.setError("전화 번호를 올바르게 입력하세요");
                                ok2 = -1;
                            }
                        }

                        if (ok1 > -1 && ok2 > -1) {
                            if (telTemp.equals(""))
                                telTemp = celTemp;

                            if (telTemp.replaceAll("-", "").length() > 6) {
                                key2 = encryptKey(telTemp.replaceAll("-", "").substring(telTemp.replaceAll("-", "").length() - 6));
                                telTemp = encryptTel(telTemp);
                            }
                            if (celTemp.replaceAll("-", "").length() > 6) {
                                key4 = encryptKey(celTemp.replaceAll("-", "").substring(celTemp.replaceAll("-", "").length() - 6));
                                celTemp = encryptTel(celTemp);
                            }

                            if (addr.getText() != null && addr.getText().length() > 0) {
                                if (addr.getText().toString().substring(0, 1).equals("(")) {
                                    addrTemp = addr.getText().toString().substring(8);
                                    zipTemp = addr.getText().toString().substring(1, 6);
                                } else {
                                    addrTemp = addr.getText().toString();
                                }
                            }

                            if (email.getText() != null && email.getText().length() > 0)
                                emailTemp = email.getText().toString();

                            if (cel.getError() == null) {

                                HashMap<String, String> data = new HashMap<>();
                                data.put("code", mBcode);
                                data.put("name", name.getText().toString());
                                data.put("sex", sexTemp);
                                data.put("apply", applyTemp);
                                data.put("tel", telTemp);
                                data.put("cel", celTemp);
                                data.put("key2", key2);
                                data.put("key4", key4);
                                data.put("addr", addrTemp);
                                data.put("zip", zipTemp);
                                data.put("email", emailTemp);
                                data.put("date", date);
                                if (cashinfo.getText() != null) {
                                    data.put("info", cashinfo.getText().toString());
                                }
                                data.put("type", cashtypetemp);

                                JSONObject json = new JSONObject(data);
                                JsonParser jsonParser = new JsonParser();

                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl("http://59.12.186.68:55282/allbasket/")
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();
                                RetrofitService retrofitService = retrofit.create(RetrofitService.class);

                                retrofitService.memmodify((JsonObject) jsonParser.parse(json.toString())).enqueue(new Callback<RepoResult>() {
                                    @Override
                                    public void onResponse(@NonNull Call<RepoResult> call, @NonNull Response<RepoResult> response) {
                                    }

                                    @Override
                                    public void onFailure(@NonNull Call<RepoResult> call, @NonNull Throwable t) {
                                        Toast.makeText(getContext(), "Error\n", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                scrollView.setVisibility(View.GONE);
                                welcome.setVisibility(View.VISIBLE);
                                LottieAnimationView lottie = view.findViewById(R.id.lottie);
                                lottie.setAnimation("check.json");
                                lottie.playAnimation();
                                mTitle.setText("변경 완료");
                                mNext.setVisibility(View.INVISIBLE);
                                mPrevious.setVisibility(View.INVISIBLE);
                                StringBuilder stringBuilder = new StringBuilder("회원 정보를 변경하였습니다");
                                welcomeName.setText(stringBuilder);

                            } else {
                                mViewPager.getChildAt(1).findViewById(R.id.cel).requestFocus();
                            }
                        }
                    }
                }, 600);

            }
        });

        return view;
    }

    static class DialogAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> mFragmentCollection = new ArrayList<>();
        private List<String> mTitleCollection = new ArrayList<>();

        DialogAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        void addFragment(String title, Fragment fragment) {
            mTitleCollection.add(title);
            mFragmentCollection.add(fragment);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleCollection.get(position);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragmentCollection.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentCollection.size();
        }

    }

    void setCurrentItem(int item) {
        mViewPager.setCurrentItem(item, true);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setWhiteNavigationBar(@NonNull Dialog dialog) {
        Window window = dialog.getWindow();
        if (window != null) {
            DisplayMetrics metrics = new DisplayMetrics();
            window.getWindowManager().getDefaultDisplay().getMetrics(metrics);

            GradientDrawable dimDrawable = new GradientDrawable();

            GradientDrawable navigationBarDrawable = new GradientDrawable();
            navigationBarDrawable.setShape(GradientDrawable.RECTANGLE);
            navigationBarDrawable.setColor(Color.WHITE);

            Drawable[] layers = {dimDrawable, navigationBarDrawable};

            LayerDrawable windowBackground = new LayerDrawable(layers);
            windowBackground.setLayerInsetTop(1, metrics.heightPixels);

            window.setBackgroundDrawable(windowBackground);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mView != null && page != 0) {
            mView.requestFocus();
            mView.postDelayed(() -> {
                if (getActivity() != null) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.showSoftInput(mView, 0);
                    }
                }
            }, 200);
        }
    }

    private String encryptKey(String mTel) {
        Random random = new Random();
        int randomNum = random.nextInt((59 - 1) + 1) + 1;
        int aa, bb, cc;
        int result;

        aa = Integer.parseInt(mTel.substring(mTel.length() - 6, mTel.length() - 4));
        bb = Integer.parseInt(mTel.substring(mTel.length() - 4, mTel.length() - 2));
        cc = Integer.parseInt(mTel.substring(mTel.length() - 2));

        cc = (cc % 38) + 12 + randomNum;
        bb = (bb % 37) + 13 + randomNum;
        aa = aa ^ randomNum ^ cc ^ bb;

        result = Integer.parseInt(String.format(Locale.getDefault(), "%02x", randomNum) + String.format(Locale.getDefault(), "%02x", cc) + String.format(Locale.getDefault(), "%02x", bb) + String.format(Locale.getDefault(), "%02x", aa), 16);

        return String.valueOf(result);
    }

    private String encryptTel(String mTel) {
        String left, right;
        right = mTel.substring(mTel.length() - 4);
        left = mTel.substring(0, mTel.length() - 7);
        return left + "xx-" + right;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getDialog() != null && getDialog().getWindow() != null) {
            mView = getDialog().getWindow().getCurrentFocus();
            if (getActivity() != null) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null && mView != null)
                    imm.hideSoftInputFromWindow(mView.getWindowToken(), 0);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Dialog dialog = getDialog();
        if (dialog != null && getRetainInstance())
            dialog.setDismissMessage(null);

        super.onDestroyView();
    }

}
