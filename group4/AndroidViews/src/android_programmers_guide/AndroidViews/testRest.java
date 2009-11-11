package android_programmers_guide.AndroidViews;

//import java.lang.CharSequence;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.SubMenu;
import android.view.MenuItem;
import android.content.Intent;
public class testRest extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testrest);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	SubMenu arbymenu=menu.addSubMenu(0, 0, Menu.NONE, "Arby's");
    	SubMenu chickmenu=menu.addSubMenu(0, 1, Menu.NONE, "ChickFil-A");
    	SubMenu chilismenu=menu.addSubMenu(0, 2, Menu.NONE, "Chilis");
    	arbymenu.add(0, 0, 0, "Chicken sub");
    	arbymenu.add(0,1,0,"Milkshake");
    	chickmenu.add(1, 0, 0, "Potato wedges");
    	chilismenu.add(2,0,0,"Choclate cake");
    	return true;
    	}
    public boolean onOptionsItemSelected(MenuItem item){
    /** Select statement to handle calls
    to specific menu items */
    switch (item.getItemId()) {
    case 0:
    	
    	return true;
    case 1:
    	return true;
    case 2:
        
    return true;
   }
    return true;
    }
 
}