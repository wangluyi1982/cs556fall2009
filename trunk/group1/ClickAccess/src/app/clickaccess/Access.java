package app.clickaccess;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* A custom WebView. */
public class Access extends Activity {
	private WebView webview;
	private String defaultUrl = "http://www.google.com";
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.access_view);
        
        String url = defaultUrl;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
        	url = extras.getString(WebinfoDbAdapter.KEY_URL);
        }
    
	    webview = (WebView) findViewById(R.id.webview);
        webview.setWebViewClient(new AccessWebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(url);
	}
    
    /* Enables the use of the back key. */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    
    /* This class disables the passing of URLs to the default Android browser. */
	private class AccessWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}