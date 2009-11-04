package android_programmers_guide.AndroidViews;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.graphics.Color;
public class testBMI extends Activity {
	
	public EditText txtheight;
	public EditText txtweight;
	public EditText txtTipOther;
	public RadioGroup rdoGroupTips;
	public Button btnCalculate;
	public Button btnReset;
	public TextView txtbmi;
	public TextView txtmsg;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.testbmi);
	Button okButton = (Button) findViewById(R.id.submit);
    okButton.setOnClickListener(okOnClickListener);
    Button cancelButton = (Button) findViewById(R.id.cancel);
    cancelButton.setOnClickListener(cancelOnClickListener);
}
private Button.OnClickListener okOnClickListener =
          new Button.OnClickListener(){
    @Override
    public void onClick(View v) {
   txtheight = (EditText)findViewById(R.id.height);
   txtweight = (EditText)findViewById(R.id.weight);
  txtbmi=(TextView) findViewById(R.id.bmi);
  txtmsg=(TextView) findViewById(R.id.msg);
  Double height = Double.parseDouble(txtheight.getText().toString());
   Double weight = Double.parseDouble(txtweight.getText().toString());
  Double bmi = weight/(height*height);
  txtbmi.setText(bmi.toString());  
 if(bmi<=18.5)
	 txtmsg.setText("Underweight");
 else if(bmi> 18.5 && bmi < 24.9)
	 txtmsg.setText("Normal weight");
 else if (bmi > 25 && bmi < 29.9)
	 txtmsg.setText("Overweight");
 else 
	 txtmsg.setText("Obese");
  	  
	 
    }
};

private Button.OnClickListener cancelOnClickListener =
        new Button.OnClickListener(){
    @Override
    public void onClick(View v) {
        finish();
    }
};
}