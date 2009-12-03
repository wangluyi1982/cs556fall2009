package cs556.group3;
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
 
public class ViewsActivity extends Activity 
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  
 
        startActivity(new Intent(this, ListView.class));
 
    }
}