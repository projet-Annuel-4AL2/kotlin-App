package com.example.pa4a

import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true

        webView.webViewClient = WebViewClient()
        val htmlContent = """
            <html>
            <head>
                <link rel="preconnect" href="https://fonts.gstatic.com/" crossorigin="" />
                <link
                  rel="stylesheet"
                  as="style"
                  onload="this.rel='stylesheet'"
                  href="https://fonts.googleapis.com/css2?display=swap&amp;family=Noto+Sans%3Awght%40400%3B500%3B700%3B900&amp;family=Plus+Jakarta+Sans%3Awght%40400%3B500%3B700%3B800"
                />
                <title>Galileo Design</title>
                <link rel="icon" type="image/x-icon" href="data:image/x-icon;base64," />
                <script src="https://cdn.tailwindcss.com?plugins=forms,container-queries"></script>
            </head>
            <body>
                <div class="relative flex size-full min-h-screen flex-col bg-black dark justify-between group/design-root overflow-x-hidden" style='font-family: "Plus Jakarta Sans", "Noto Sans", sans-serif;'>
                    <!-- Your content goes here -->
                </div>
            </body>
            </html>
        """
        webView.loadDataWithBaseURL(null, htmlContent, "text/html", "utf-8", null)
    }
}
