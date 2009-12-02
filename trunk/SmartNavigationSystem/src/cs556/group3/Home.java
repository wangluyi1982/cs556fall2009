package cs556.group3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import cs556.group3.R;

public class Home extends Activity {

	
@Override
public void onCreate(Bundle icicle) {
super.onCreate(icicle);
setContentView(R.layout.main);
}

public boolean onCreateOptionsMenu(Menu menu) 
{
	super.onCreateOptionsMenu(menu);
	menu.add(0, 0, 0, "Enter Menu");
	return true;
}


public boolean onOptionsItemSelected(MenuItem item){
/** Select statement to handle calls
to specific menu items */
switch (item.getItemId()) {
case 0:
	showButton();
    return true;
}
return true;
}

private void showButton() {
	// TODO Auto-generated method stub
	Intent showButton = new Intent(this, button.class);
	startActivity(showButton);	

}
}