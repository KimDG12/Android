package com.icarus.recycle_app.ui.home

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.net.http.SslError
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.PermissionRequest
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.google.gson.Gson
import com.icarus.recycle_app.databinding.FragmentDaumAddressDialogBinding
import com.icarus.recycle_app.dto.Address


class DaumAddressDialogFragment : DialogFragment() {

    private var _binding: FragmentDaumAddressDialogBinding? = null
    private val binding get() = _binding!!

    private var handler = Handler()


    inner class JavaScriptInterface {
        @JavascriptInterface
        fun processDATA(data: String?) {
            val intent = Intent()
            intent.putExtra("data", data)
        }
    }


    override fun onStart() {
        super.onStart()
        val dialog = dialog

        if (dialog != null) {
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDaumAddressDialogBinding.inflate(inflater, container, false)

        setupWebView()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setupWebView() {
        val webView = binding.webView

        // JavaScript 허용
        true.also { webView.settings.javaScriptEnabled = it }

        // JavaScript의 window.open 허용
        webView.settings.javaScriptCanOpenWindowsAutomatically = true

        // JavaScript이벤트에 대응할 함수를 정의 한 클래스를 붙여줌
        webView.addJavascriptInterface(AndroidBridge(), "MysosoApp")

        //DOMStorage 허용
        webView.settings.domStorageEnabled = true

        //ssl 인증이 없는 경우 해결을 위한 부분
        webView.webChromeClient = object : WebChromeClient() {
            override fun onPermissionRequest(request: PermissionRequest) {
                request.grant(request.resources)
            }
        }
        webView.webViewClient = object : WebViewClient() {
            @SuppressLint("WebViewClientOnReceivedSslError")
            override fun onReceivedSslError(
                view: WebView,
                handler: SslErrorHandler,
                error: SslError
            ) {
                // SSL 에러가 발생해도 계속 진행
                handler.proceed()
            }

            @Deprecated("Deprecated in Java")
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                Log.e("페이지 로딩", url)
                webView.loadUrl("javascript:sample2_execDaumPostcode();")
            }
        }

        // webview url load. php or html 파일 주소
        webView.loadUrl("http://220.68.27.150:8080/daum")

    }

    inner class AndroidBridge {
        @JavascriptInterface
        @Suppress("unused")
        fun processDATA(roadAdd: String?) {
            handler.post(Runnable {

                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("알림")
                builder.setMessage("선택하신 주소로 입력하시겠습니까?")
                builder.setPositiveButton("네"
                ) { dialog, _ ->


                    listener.onChange(roadAdd.toString())

                    dialog.dismiss()
                }


                builder.setNegativeButton("아니요"
                ) { dialog, which -> dialog.dismiss() }
                val dialog: AlertDialog = builder.create()
                dialog.show()


                dismiss()


            })
        }
    }

    lateinit var listener: ChangeAddressListener

    interface ChangeAddressListener {
        fun onChange(roadAdd: String)
    }

}
