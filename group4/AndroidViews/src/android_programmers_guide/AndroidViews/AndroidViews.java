package android_programmers_guide.AndroidViews;

//import java.lang.CharSequence;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
public class AndroidViews extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    /*public void showAutoComplete(){
  	  Intent autocomplete=new Intent(this,autocomplete.class);
  	  startActivity(autocomplete);
    }*/
    public void showBMI() {
    	Intent showBMI = new Intent(this, testBMI.class);
    	startActivity(showBMI);
    	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	menu.add(0,0,0,"BMI");
    	menu.add(0,1,0,"Fitness Stats");
    	menu.add(0,2,0,"Food");
    	return true;
    	}
    public boolean onOptionsItemSelected(MenuItem item){
    /** Select statement to handle calls
    to specific menu items */
    switch (item.getItemId()) {
    case 0:
    	showBMI();
    	return true;
    case 1:
    	return true;
    case 2:
    	//showAutoComplete();
    return true;
   }
    return true;
    }
 
}