package com.jimbonlemu.mycustomview

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jimbonlemu.mycustomview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            webView.apply {
                settings.javaScriptEnabled = true
                webViewClient = object : WebViewClient(){
                    override fun onPageFinished(view: WebView, url: String?) {
                        view.loadUrl("javascript:alert('Web Dicoding berhasil dimuat')")
                    }
                }
                webChromeClient = object :WebChromeClient(){
                    override fun onJsAlert(
                        view: WebView,
                        url: String,
                        message: String ,
                        result: JsResult
                    ): Boolean {
                        Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
                        result.confirm()
                        return true
                    }
                }
                loadUrl("https://www.dicoding.com")
            }
        }
    }
}