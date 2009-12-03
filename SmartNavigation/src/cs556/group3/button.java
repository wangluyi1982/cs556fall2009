package cs556.group3;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import cs556.group3.R;



public class button extends Activity 
{
	public static double a;
	public static double b;
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
        setContentView(R.layout.button);	
        
        final Button changeButton = (Button)findViewById(R.id.layoutButton);
        changeButton.setOnClickListener(new Button.OnClickListener() {
        public void onClick(View v){
        changeOption(); }
        });
        
        final Button changegpsButton = (Button)findViewById(R.id.gpsbutton);
        changegpsButton.setOnClickListener(new Button.OnClickListener() {
        public void onClick(View v){
        changeOptiontoGPS(); }
        });
        
        final Button changeButtonmost = (Button)findViewById(R.id.most);
        changeButtonmost.setOnClickListener(new Button.OnClickListener() {
        public void onClick(View v){
        changeOptionmost(); }
        });
      
        final Button changeButtonmake = (Button)findViewById(R.id.make);
        changeButtonmake.setOnClickListener(new Button.OnClickListener() {
        public void onClick(View v){
        changeOptionmake(); }
        });
      
        final Button changewrite = (Button)findViewById(R.id.write);
        changewrite.setOnClickListener(new Button.OnClickListener() {
        public void onClick(View v){
        changeOptionwrite(); }
        });
}
	public void changeOption(){
		Intent showButton = new Intent(this, MapsActivity.class);
		startActivity(showButton);
		}
	
	public void changeOptiontoGPS(){
		String requete;
		requete="geo:lat,long";
		Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(requete));
		startActivity(intent);

		}
	public void changeOptionmost(){
		Intent showButtonmost = new Intent(this, ListView.class);
		startActivity(showButtonmost);
		}

	public void changeOptionmake(){
		Intent showmake = new Intent(this, DialANumber.class);
		startActivity(showmake);
		}
	
	public void changeOptionwrite(){
		Intent showmake = new Intent(this, SMSApp.class);
		startActivity(showmake);
		}
}
