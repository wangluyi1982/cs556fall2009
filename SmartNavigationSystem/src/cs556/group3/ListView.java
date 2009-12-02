package cs556.group3;
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import cs556.group3.R;

public class ListView extends Activity 
{
    String[] countries = {
            "",
            "Morgantown",
            "London",
            "Paris",
            "Sydney"
            };
 
    Spinner s1;
 
    @Override    
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button);
 
        s1 = (Spinner) findViewById(R.id.spinner1);
 
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_item, countries);
 
        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(new OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> arg0, 
            View arg1, int arg2, long arg3) 
            {
                int index = s1.getSelectedItemPosition();
                Toast.makeText(getBaseContext(), 
                    "You have selected item : " + countries[index], 
                    Toast.LENGTH_SHORT).show(); 
                switch(index)
                {
                    case 1:
                	showMorgantown();
                    //return true;
                	break;
                    case 2:
                    showLondon();
                    break;
                    case 3:
                    showParis();
                    break;
                	
                }
            }
 
            public void onNothingSelected(AdapterView<?> arg0) {}
        });
    }
    private void showMorgantown() {
    	// TODO Auto-generated method stub
    	Intent showmorg = new Intent(this, MapsActivity.class);
    	startActivity(showmorg);	
    	
        }
    private void showLondon() {
    	// TODO Auto-generated method stub
    	Intent showlon = new Intent(this, MapsLondon.class);
    	startActivity(showlon);	
    }
    	private void showParis() {
        	// TODO Auto-generated method stub
        	Intent showpar = new Intent(this, MapsParis.class);
        	startActivity(showpar);	
}
}