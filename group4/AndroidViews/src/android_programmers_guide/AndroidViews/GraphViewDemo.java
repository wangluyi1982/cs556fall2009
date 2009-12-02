package android_programmers_guide.AndroidViews;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
public class GraphViewDemo extends Activity {
	private foodDbAdapter mDbHelper;
	public TextView text;
	public TextView text1;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teststats);
		/** This is our Test Activity
		All code goes below */
		text = (TextView)findViewById(R.id.week);
		text1 = (TextView)findViewById(R.id.week1);	
		mDbHelper = new foodDbAdapter(this);
		mDbHelper.open();
		Cursor  val = mDbHelper.fetchNote(AndroidViews.id);
		Double val1 = Double.parseDouble(val.getString(1));
		Double val2 = Double.parseDouble(val.getString(2));
		Double val3 = Double.parseDouble(val.getString(3));
		text.setText("Total calories to be consumed this week is: "+val1 +"\n"+
				"Total calories consumed till today is: "+val3 +"\n"+
						"Total calories burnt during exercise is: "+val2+"\n"+
						"Total caories yet to be consumed is :"+(val1-val2)+val3 );
		double[] values = new double[] { val1,val2, val3, (val1-val2)+val3  };
		String[] verlabels = new String[] { "calories"};
		String[] horlabels = new String[] { "C1", "C2", "C3", "C4" };
		GraphView graphView = new GraphView(this, values,"Statistics",horlabels, verlabels, GraphView.BAR);
		setContentView(graphView);
	}
}
