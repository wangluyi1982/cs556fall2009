package android_programmers_guide.AndroidViews;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.*;
//import android.net.Uri;
import android.view.View;
//import android.view.View.OnClickListener;
public class chicken extends Activity{
	public TextView txtmsg;
	public TextView txtmsg1;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	setContentView(R.layout.chicken);
	Button okButton = (Button) findViewById(R.id.ok);
    okButton.setOnClickListener(OnClickListener);
	}
	private Button.OnClickListener OnClickListener =
        new Button.OnClickListener(){
		public void onClick(View v) {
			txtmsg=(TextView) findViewById(R.id.msg);
			txtmsg1=(TextView) findViewById(R.id.msg);
			txtmsg.setText("For 1 quantity of item");
			txtmsg1.setText("You will receive 100 calories");
			
}
};
}