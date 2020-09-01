package com.ikco10.allbasketsignup;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

@SuppressLint("ValidFragment")
public class FragmentMemberSignup3 extends Fragment{

    private WebView mWebView;
    private Handler mHandler;

    FragmentMemberSignup3() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_membersignup3, container, false);
        mWebView = view.findViewById(R.id.webview);

        mHandler = new Handler();

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.addJavascriptInterface(new AndroidBridge(), "allbasket");
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("http://allbasket7755.iptime.org:5143/allbasket/address.jsp");

        return view;
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init_webView() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.addJavascriptInterface(new AndroidBridge(), "allbasket");
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("http://allbasket7755.iptime.org:5143/allbasket/address.jsp");
    }

    class AndroidBridge {
        @JavascriptInterface
        public void setAddress(final String arg1, final String arg2, final String arg3) {
            mHandler.post(() -> {
                if (getParentFragment() != null) {
                    ((DialogMemberSignup) getParentFragment()).setCurrentItem(1);
                }

                Fragment fragment = null;
                if (getFragmentManager() != null) {
                    fragment = getFragmentManager().getFragments().get(1);
                }

                if (fragment != null) {
                    ((FragmentMemberSignup2) fragment).passAddr(String.format("(%s) %s %s", arg1, arg2, arg3));
                    init_webView();
                }
            });
        }
    }
}
