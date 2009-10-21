package android_programmers_guide.AndroidViews;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.graphics.Color;
public class testBMI extends Activity {
	@Override
	public void onCreate(Bundle icicle) {
	super.onCreate(icicle);
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
        EditText edit_height = (EditText)
             findViewById(R.id.height);
        EditText edit_weight = (EditText)
        findViewById(R.id.weight);
        EditText edit_age = (EditText)
        findViewById(R.id.age);
        //int value=edit_height./edit_weight;
        TextView bmi=(TextView) findViewById(R.id.bmi);
        //bmi.setText(value);
        /*CharSequence edit_text_value =
             edit_text.getText();
        setTitle("Hello:"+edit_text_value);*/
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