package android_programmers_guide.AndroidViews;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.graphics.Color;
public class autocomplete extends Activity {
	static final String[] Months = new String[]{
		"Burger King","Chilis","McDonalds"
		};
	@Override
	public void onCreate(Bundle icicle) {
	super.onCreate(icicle);
	setContentView(R.layout.autocomplete);
	ArrayAdapter<String> monthArray = new ArrayAdapter<String>(this,
	android.R.layout.simple_list_item_1, Months);
	final AutoCompleteTextView textView = (AutoCompleteTextView)
	findViewById(R.id.testAutoComplete);
	textView.setAdapter(monthArray);
	final Button changeButton = (Button)
	findViewById(R.id.autoCompleteButton);
	changeButton.setOnClickListener(new Button.OnClickListener() {
	public void onClick(View v){
	changeOption(textView);
	}
	});
	final Button changeButton2 = (Button)
	findViewById(R.id.textColorButton);
	changeButton2.setOnClickListener(new Button.OnClickListener() {
	public void onClick(View v){
	changeOption2(textView);
	}
	});
	}
	
	public void changeOption(AutoCompleteTextView text){
	if (text.getHeight()==100){
	text.setHeight(30);
	}
	else{
	text.setHeight(100);
	}
	}
	public void changeOption2(AutoCompleteTextView text){
	text.setTextColor(Color.RED);
	}
	}