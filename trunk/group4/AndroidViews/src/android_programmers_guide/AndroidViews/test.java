package android_programmers_guide.AndroidViews;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
public class test extends Activity {
/** Called when the Activity is first created. */
@Override
public void onCreate(Bundle icicle) {
super.onCreate(icicle);
setContentView(R.layout.test);
Intent testActivity=new Intent(this,test.class);
startActivity(testActivity);
/** This is our Test Activity
All code goes below */
}
}