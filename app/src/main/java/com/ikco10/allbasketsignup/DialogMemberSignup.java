package com.ikco10.allbasketsignup;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ikco10.allbasketsignup.Utils.ClearEditText;
import com.ikco10.allbasketsignup.Utils.OnSingleClickListener;
import com.ikco10.allbasketsignup.Utils.RetrofitApi;
import com.ikco10.allbasketsignup.Utils.ViewPagerNonSwipe;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("ValidFragment")
public class DialogMemberSignup extends DialogFragment {

    private CompositeDisposable mDisposable = new CompositeDisposable();
    private ViewPagerNonSwipe mViewPager;
    private int page = 0;
    private ImageButton mPrevious;
    View mView = null;
    TextView mTitle;
    Button mNext;

    DialogMemberSignup() {
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
                if (mTitle.getText().toString().equals("회원 가입")) {
                    if (getActivity() != null) {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (imm != null && getView() != null)
                            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                    }
                }
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    if (page == 0) {
                        dismiss();
                    } else if (mViewPager.getCurrentItem() == 2) {
                        mView.requestFocus();
                        page = 1;
                        mViewPager.setCurrentItem(page);
                        mTitle.setText("회원 가입");
                        mNext.setText("등록");
                        mNext.setVisibility(View.VISIBLE);
                    } else if (page == 1 && mTitle.getText().toString().equals("회원 가입")) {
                        page--;
                        mViewPager.setCurrentItem(page);
                        mTitle.setText("약관 동의");
                        mNext.setText("다음");
                        mNext.setVisibility(View.VISIBLE);
                        mPrevious.setImageResource(R.drawable.ic_close);
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

        View view = inflater.inflate(R.layout.dialog_membersignup, container, false);

        mTitle = view.findViewById(R.id.title);
        mPrevious = view.findViewById(R.id.back);
        mNext = view.findViewById(R.id.next);

        super.onCreate(savedInstanceState);

        if (getDialog() != null && getDialog().getWindow() != null)
            getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimationFade;

        DialogAdapter adapter = new DialogAdapter(getChildFragmentManager());
        adapter.addFragment("약관", new FragmentMemberSignup1());
        adapter.addFragment("가입", new FragmentMemberSignup2());
        adapter.addFragment("주소", new FragmentMemberSignup3());
        mViewPager = view.findViewById(R.id.masterViewPager);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(page);

        mPrevious.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                if (mTitle.getText().toString().equals("정보 입력")) {
                    if (getActivity() != null) {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (imm != null)
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    if (page == 0) {
                        dismiss();
                    } else if (mViewPager.getCurrentItem() == 2) {
                        page = 1;
                        mView.requestFocus();
                        mViewPager.setCurrentItem(page);
                        mTitle.setText("정보 입력");
                        mNext.setText("등록");
                        mNext.setVisibility(View.VISIBLE);
                    } else if (page == 1) {
                        page--;
                        mViewPager.setCurrentItem(page);
                        mTitle.setText("약관 동의");
                        mNext.setText("다음");
                        mNext.setVisibility(View.VISIBLE);
                        mPrevious.setImageResource(R.drawable.ic_close);
                    }
                }, 300);
            }
        });

        mNext.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                if (page == 0) {
                    page++;
                    mViewPager.setCurrentItem(page);
                    mTitle.setText("정보 입력");
                    mNext.setText("등록");
                    mPrevious.setImageResource(R.drawable.ic_back);
                    mViewPager.getChildAt(1).findViewById(R.id.membersearch_name).requestFocus();
                    Handler handler = new Handler();
                    handler.postDelayed(() -> {
                        if (getActivity() != null) {
                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            if (imm != null)
                                imm.showSoftInput(mViewPager.getChildAt(1).findViewById(R.id.membersearch_name), 0);
                        }
                    }, 350);

                } else {
                    page = 1;
                    if (getActivity() != null) {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (imm != null && getView() != null) {
                            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                        }
                    }

                    Handler handler = new Handler();
                    handler.postDelayed(() -> {

                        Calendar calendar = Calendar.getInstance();
                        calendar.add(Calendar.MONTH, 0);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.KOREA);
                        String date = dateFormat.format(calendar.getTime());
                        String sexTemp, smsTemp, celTemp = "", telTemp = "", key2 = "-1", key4 = "-1";
                        String addrTemp = "", zipTemp = "", emailTemp = "";

                        RadioButton male = mViewPager.getChildAt(1).findViewById(R.id.male);
                        CheckBox sms = mViewPager.getChildAt(0).findViewById(R.id.check3);
                        ClearEditText name = mViewPager.getChildAt(1).findViewById(R.id.membersearch_name);
                        ClearEditText tel = mViewPager.getChildAt(1).findViewById(R.id.membersearch_tel);
                        ClearEditText cel = mViewPager.getChildAt(1).findViewById(R.id.cel);
                        ClearEditText addr = mViewPager.getChildAt(1).findViewById(R.id.membersearch_addr);
                        ClearEditText email = mViewPager.getChildAt(1).findViewById(R.id.email);
                        TextView celOverlap = mViewPager.getChildAt(1).findViewById(R.id.celOverlap);
                        TextView telOverlap = mViewPager.getChildAt(1).findViewById(R.id.telOverlap);
                        ScrollView scrollView = mViewPager.getChildAt(1).findViewById(R.id.scrollview);
                        LinearLayout welcome = mViewPager.getChildAt(1).findViewById(R.id.welcome);
                        TextView welcomeName = mViewPager.getChildAt(1).findViewById(R.id.welcomename);
                        TextView confirm = mViewPager.getChildAt(1).findViewById(R.id.confirm);

                        confirm.setOnClickListener(new OnSingleClickListener() {
                            @Override
                            public void onSingleClick(View v) {
                                Handler handler1 = new Handler();
                                handler1.postDelayed(DialogMemberSignup.this::dismiss, 500);
                            }
                        });

                        if (male.isChecked()) {
                            sexTemp = "1";
                        } else {
                            sexTemp = "0";
                        }

                        if (sms.isChecked()) {
                            smsTemp = "0";
                        } else {
                            smsTemp = "4096";
                        }

                        if (name.getText() != null && name.getText().toString().isEmpty())
                            name.setError("");

                        if (cel.getText() != null && cel.getText().toString().isEmpty())
                            cel.setError("");

                        if (addr.getText() != null && addr.getText().toString().isEmpty())
                            addr.setError("");

                        if (!name.getText().toString().isEmpty() && !cel.getText().toString().isEmpty() && !addr.getText().toString().isEmpty()) {
                            if (cel.getText() != null)
                                celTemp = cel.getText().toString();
                            if (tel.getText() != null)
                                telTemp = tel.getText().toString();
                            int ok1 = 0, ok2 = 0;

                            if (celTemp.length() > 0) {
                                if (!celTemp.substring(0, 1).equals("0") || celTemp.length() < 12) {
                                    cel.setError("");
                                    celOverlap.setText("올바르게 입력하세요");
                                    ok1 = -1;
                                } else if (celTemp.substring(0, 3).equals("010") && celTemp.length() < 13) {
                                    cel.setError("");
                                    celOverlap.setText("올바르게 입력하세요");
                                    ok1 = -1;
                                } else if (!celTemp.substring(0, 2).equals("01")) {
                                    cel.setError("");
                                    celOverlap.setText("올바르게 입력하세요");
                                    ok1 = -1;
                                }
                            }

                            if (telTemp.length() > 0) {
                                if (!telTemp.substring(0, 1).equals("0") || telTemp.length() < 2) {
                                    tel.setError("");
                                    telOverlap.setText("지역 번호를 입력하세요");
                                    ok2 = -1;
                                } else if (telTemp.substring(1, 2).equals("0") || telTemp.substring(1, 2).equals("1") || telTemp.substring(1, 2).equals("7") || telTemp.substring(1, 2).equals("8") || telTemp.substring(1, 2).equals("9")) {
                                    tel.setError("");
                                    telOverlap.setText("올바르게 입력하세요");
                                    ok2 = -1;
                                } else if (telTemp.length() < 11) {
                                    tel.setError("");
                                    telOverlap.setText("올바르게 입력하세요");
                                    ok2 = -1;
                                }
                            }

                            if (ok1 > -1 && ok2 > -1) {
                                if (telTemp.equals(""))
                                    telTemp = celTemp;

                                if (telTemp.replaceAll("-", "").length() > 6) {
                                    key2 = generateKey(telTemp.replaceAll("-", "").substring(telTemp.replaceAll("-", "").length() - 6));
                                    telTemp = encryptTel(telTemp);
                                }
                                if (celTemp.replaceAll("-", "").length() > 6) {
                                    key4 = generateKey(celTemp.replaceAll("-", "").substring(celTemp.replaceAll("-", "").length() - 6));
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
                                    data.put("name", name.getText().toString());
                                    data.put("sex", sexTemp);
                                    data.put("sms", smsTemp);
                                    data.put("tel", telTemp);
                                    data.put("cel", celTemp);
                                    data.put("key2", key2);
                                    data.put("key4", key4);
                                    data.put("addr", addrTemp);
                                    data.put("zip", zipTemp);
                                    data.put("email", emailTemp);
                                    data.put("date", date);

                                    JSONObject json = new JSONObject(data);
                                    JsonParser jsonParser = new JsonParser();

                                    RetrofitApi retrofitApi = new RetrofitApi();

                                    Observable<RepoResult> observable = retrofitApi.getApi().memsignup((JsonObject) jsonParser.parse(json.toString()));

                                    mDisposable.add(observable.subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(repoResult -> {
                                                        scrollView.setVisibility(View.GONE);
                                                        welcome.setVisibility(View.VISIBLE);
                                                        LottieAnimationView lottie = view.findViewById(R.id.lottie);
                                                        lottie.setAnimation("check.json");
                                                        lottie.playAnimation();
                                                        mTitle.setText("가입 완료");
                                                        mNext.setVisibility(View.INVISIBLE);
                                                        mPrevious.setVisibility(View.INVISIBLE);
                                                        StringBuilder stringBuilder = new StringBuilder(name.getText().toString());
                                                        stringBuilder.append("님");
                                                        welcomeName.setText(stringBuilder);
                                                    },
                                                    e -> {
                                                        if (getView() != null)
                                                            Snackbar.make(getView(), "Error\n", Snackbar.LENGTH_SHORT).show();
                                                    },
                                                    () -> {
                                                    }
                                            )
                                    );
                                } else {
                                    mViewPager.getChildAt(1).findViewById(R.id.cel).requestFocus();
                                }
                            }
                        }
                    }, 600);
                }
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

    private String generateKey(String tel) {
        Random random = new Random();
        int randomNum = random.nextInt((59 - 1) + 1) + 1;

        int aa = Integer.parseInt(tel.substring(tel.length() - 6, tel.length() - 4));
        int bb = Integer.parseInt(tel.substring(tel.length() - 4, tel.length() - 2));
        int cc = Integer.parseInt(tel.substring(tel.length() - 2));

        cc = (cc % 38) + 12 + randomNum;
        bb = (bb % 37) + 13 + randomNum;
        aa = aa ^ randomNum ^ cc ^ bb;

        return String.valueOf(Integer.parseInt(String.format(Locale.getDefault(), "%02x", randomNum)
                + String.format(Locale.getDefault(), "%02x", cc)
                + String.format(Locale.getDefault(), "%02x", bb)
                + String.format(Locale.getDefault(), "%02x", aa), 16));
    }

    private String encryptTel(String tel) {
        return (tel.substring(0, tel.length() - 7)) + "xx-" + (tel.substring(tel.length() - 4));
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getDialog() != null && getDialog().getWindow() != null) {
            mView = getDialog().getWindow().getCurrentFocus();
            if (getActivity() != null) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null && mView != null) {
                    imm.hideSoftInputFromWindow(mView.getWindowToken(), 0);
                }
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
