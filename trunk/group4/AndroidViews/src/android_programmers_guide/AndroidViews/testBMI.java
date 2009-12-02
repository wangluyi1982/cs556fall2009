package android_programmers_guide.AndroidViews;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
//import android.graphics.Color;
public class testBMI extends Activity implements RadioGroup.OnCheckedChangeListener{
	
	public EditText txtheight;
	public EditText txtweight;
	public Button btnCalculate;
	public Button btnReset;
	public TextView txtbmi;
	//public TextView uid;
	public TextView txtmsg;
	public TextView txtmsg1,txtmsg2;
	public EditText txtage;
	public RadioGroup radiogroup;
    public RadioButton r1,r2;
    private foodDbAdapter mDbHelper;
    public int radioCheckedId = -1;
   

	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.testbmi);
	mDbHelper = new foodDbAdapter(this);
    mDbHelper.open();
	radiogroup = (RadioGroup) findViewById(R.id.testRadioGroup);
	radiogroup.setOnCheckedChangeListener(this);
	Button okButton = (Button) findViewById(R.id.submit);
    okButton.setOnClickListener(okOnClickListener);
    Button cancelButton = (Button) findViewById(R.id.cancel);
    cancelButton.setOnClickListener(cancelOnClickListener);
}
	public void onCheckedChanged(RadioGroup group, int checkedId) {
        radioCheckedId = checkedId;
    }

private Button.OnClickListener okOnClickListener =
          new Button.OnClickListener(){
    @Override
    public void onClick(View v) {
    radioCheckedId = radiogroup.getCheckedRadioButtonId();
   txtheight = (EditText)findViewById(R.id.height);
   txtweight = (EditText)findViewById(R.id.weight);
   txtage =  (EditText)findViewById(R.id.age);
  txtbmi=(TextView) findViewById(R.id.bmi);
  txtmsg=(TextView) findViewById(R.id.msg);
  txtmsg1=(TextView) findViewById(R.id.msg1);
  txtmsg2=(TextView) findViewById(R.id.msg2);
   Double age = Double.parseDouble(txtage.getText().toString());
   Double height = Double.parseDouble(txtheight.getText().toString());
   Double weight = Double.parseDouble(txtweight.getText().toString());
   Double bmi = weight/(height*height);
   Double calories=0.0,calperweek=0.0;
    txtbmi.setText("BMI is "+bmi.toString());
   if (radioCheckedId == R.id.radio1)
  {
	 if((age >= 18) && (age <= 29))
	 {
		calories = (15.1*weight+692.0); 
	 }
	 if((age >= 30) && (age <= 59))
	 {
		 calories = (11.5*weight+873.0);  
	 }
  }
  if (radioCheckedId ==R.id.radio2)  //female
  {
	  if((age >= 18) && (age <= 29))
		 {
		  calories = (14.8*weight+692);  
		 }
		 if((age >= 30) && (age <= 59))
		 {
			 calories = (8.3*weight+692);  
		 }
  }
  calperweek= (calories*7.0);
  txtmsg1.setText("You require -" + calories.toString() + "/day");
  txtmsg2.setText("You require -"+calperweek.toString()+"/week");
 if(bmi<=18.5)
	 txtmsg.setText("Underweight");
 else if(bmi> 18.5 && bmi < 24.9)
	 txtmsg.setText("Normal weight");
 else if (bmi > 25 && bmi < 29.9)
	 txtmsg.setText("Overweight");
 else 
	 txtmsg.setText("Obese");
     //mDbHelper.createNote(calperweek, 0, 0);
     mDbHelper.updateCal(AndroidViews.id, calperweek);
     
    /*Cursor c = mDbHelper.fetchAllNotes();
    if(c.moveToFirst())
   {
	    do {
	    	 DisplayTitle(c);
	    	
	    }
	    while(c.moveToNext());
   }*/
   
mDbHelper.close();
    }
};

private Button.OnClickListener cancelOnClickListener =
        new Button.OnClickListener(){
    @Override
    public void onClick(View v) {
        finish();
    }
};
public void DisplayTitle(Cursor c)
{
    Toast.makeText(this, 
            "id: " + c.getString(0) + "---" +
            "TOTAL CALORIES:  " + c.getString(1),
            Toast.LENGTH_LONG).show();  
    Toast.makeText(this, "This data is displayed", Toast.LENGTH_LONG);
} 
}