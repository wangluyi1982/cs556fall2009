package android_programmers_guide.AndroidViews;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
public class testhelp extends Activity {
	private TextView helptxt; 
/** Called when the Activity is first created. */
@Override
public void onCreate(Bundle icicle) {
super.onCreate(icicle);
setContentView(R.layout.testhelp);
helptxt = (TextView)findViewById(R.id.help);
helptxt.setText("This is our health app developed to keep you in shape...." +
		"BMI calculator helps you in understanding your fitness state \n" +
		"Fitness Stat is used to enter the calories burnt \n" +
		"Food is used to enter the calories consumed \n" +
		"Statistics gives you information about your calorie levels per week");
}

}