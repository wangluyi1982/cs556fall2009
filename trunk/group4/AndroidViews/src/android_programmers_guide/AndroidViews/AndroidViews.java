package android_programmers_guide.AndroidViews;

 import android.app.Activity;  
 import android.os.Bundle;  
 import android.view.View;  
 import android.view.View.OnClickListener;  
 import android.widget.Button;  
 import android.widget.EditText;  
 import android.widget.TextView;  
 import android.content.Intent;
 
 public class AndroidViews extends Activity {  
     private EditText etUsername;  
     private EditText etPassword;  
     private Button btnLogin;  
     private Button btnCancel;  
     private TextView lblResult;   
     public static int id=1;
     public void showmain(){
     Intent showmain = new Intent(this, main.class);
 	startActivity(showmain);
   }
     /** Called when the activity is first created. */  
     @Override  
     public void onCreate(Bundle savedInstanceState) {  
         super.onCreate(savedInstanceState);  
         setContentView(R.layout.login);  
         etUsername = (EditText)findViewById(R.id.username);  
         etPassword = (EditText)findViewById(R.id.password);  
         btnLogin = (Button)findViewById(R.id.login_button);  
         btnCancel = (Button)findViewById(R.id.cancel_button);  
         lblResult = (TextView)findViewById(R.id.result);  
         btnLogin.setOnClickListener(new OnClickListener() {  
             @Override  
             public void onClick(View v) {  
                 String username = etUsername.getText().toString();  
                 String password = etPassword.getText().toString();  
   
                 if(username.equals("guest") && password.equals("guest")){  
                     lblResult.setText("Login successful."); 
                     id=3;
                     showmain();
                 }
                 else if(username.equals("pavan") && password.equals("pavan")){  
                     lblResult.setText("Login successful."); 
                     id=1;
                     showmain();
                     
                 }
                 else if(username.equals("preeti") && password.equals("preeti")){  
                     id=2;
                     lblResult.setText("Login successful."); 
                     showmain();
                     
                 }
                 else {  
                     lblResult.setText("Login failed. Username and/or password doesn't match.");  
                 }  
             }  
         });  
         btnCancel.setOnClickListener(new OnClickListener() {  
             @Override  
             public void onClick(View v) {   
                 finish();  
             }  
         });  
     }  
 }  