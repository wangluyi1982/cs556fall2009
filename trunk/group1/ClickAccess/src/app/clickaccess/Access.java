package app.clickaccess;


import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class Access extends Activity {
	
	    /** Called when the activity is first created. */
		WebView webview;
		//private Long mRowId;
		private String url;
		
		private class AccessWebViewClient extends WebViewClient {
	        @Override
	        public boolean shouldOverrideUrlLoading(WebView view, String url) {
	            view.loadUrl(url);
	            return true;
	        }
	    }
		
	    @Override
	    
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //setContentView(R.layout.access_view);
	        //webview = (WebView) findViewById(R.id.webview);
	        //webview.setWebViewClient(new AccessWebViewClient());
	        //webview.getSettings().setJavaScriptEnabled(true);
	        //webview.loadUrl("http://www.google.com");
	        
	        Bundle extras = getIntent().getExtras();
	        if (extras != null) {
	        	url = extras.getString(WebinfoDbAdapter.KEY_URL);
	        }
	    
		    webview = (WebView) findViewById(R.id.webview);
	        webview.setWebViewClient(new WebViewClient());
	        webview.getSettings().setJavaScriptEnabled(true);
	        webview.loadUrl(url);
		}
	    
	    @Override
	    public boolean onKeyDown(int keyCode, KeyEvent event) {
	        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
	            webview.goBack();
	            return true;
	        }
	        return super.onKeyDown(keyCode, event);
	    }
	}