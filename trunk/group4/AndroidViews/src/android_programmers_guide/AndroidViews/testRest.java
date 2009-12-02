package android_programmers_guide.AndroidViews;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
public class testRest extends Activity {
	  public TextView msg;
	  public TextView txtmsg;
	private foodDbAdapter mDbHelper;
	 @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testrest);
        mDbHelper = new foodDbAdapter(this);
        mDbHelper.open();
        Button okBurger = (Button) findViewById(R.id.burger);
        Button okChicken = (Button) findViewById(R.id.chicken);
        Button okMilkshake = (Button) findViewById(R.id.milkshake);
        Button okcoke = (Button) findViewById(R.id.coke);
        Button okbread = (Button) findViewById(R.id.bread);
        Button oktaco = (Button) findViewById(R.id.taco);

    okBurger.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
    	 msg =(TextView) findViewById(R.id.message);
         Cursor  val = mDbHelper.fetchNote(AndroidViews.id);
  	   	 Double val1 = Double.parseDouble(val.getString(3));
  	     val1 = val1 + 300.0;
  	     mDbHelper.updateFood(AndroidViews.id,val1);
  	     msg.setText("300 cal for burger is added");
      }
        });
        
        okChicken.setOnClickListener(new OnClickListener(){
        	@Override
        	  public void onClick(View v) {
        	   msg =(TextView) findViewById(R.id.message);
           		Cursor  val = mDbHelper.fetchNote(AndroidViews.id);
        		Double val1 = Double.parseDouble(val.getString(3));
        		val1 = val1 + 270.0;
        		 mDbHelper.updateFood(AndroidViews.id, val1);
        	 	 msg.setText("270 cal for chicken sub is added");  
        	  }
        	    });
        okbread.setOnClickListener(new OnClickListener(){
        	@Override
        	  public void onClick(View v) {
        	   msg =(TextView) findViewById(R.id.message);
           		Cursor  val = mDbHelper.fetchNote(AndroidViews.id);
        		Double val1 = Double.parseDouble(val.getString(3));
        		val1 = val1 + 100.0;
        		 mDbHelper.updateFood(AndroidViews.id, val1);
        	 	 msg.setText("100 cal for bread is added");  
        	  }
        	    });
        oktaco.setOnClickListener(new OnClickListener(){
        	@Override
        	  public void onClick(View v) {
        	   msg =(TextView) findViewById(R.id.message);
           		Cursor  val = mDbHelper.fetchNote(AndroidViews.id);
        		Double val1 = Double.parseDouble(val.getString(3));
        		val1 = val1 + 571.0;
        		 mDbHelper.updateFood(AndroidViews.id, val1);
        	 	 msg.setText("571 cal for taco is added");  
        	  }
        	    });
        okcoke.setOnClickListener(new OnClickListener(){
        	@Override
        	  public void onClick(View v) {
        	   msg =(TextView) findViewById(R.id.message);
           		Cursor  val = mDbHelper.fetchNote(AndroidViews.id);
        		Double val1 = Double.parseDouble(val.getString(3));
        		val1 = val1 + 270.0;
        		 mDbHelper.updateFood(AndroidViews.id, val1);
        	 	 msg.setText("97 cal for coke is added");  
        	  }
        	    });
       
        okMilkshake.setOnClickListener(new OnClickListener(){
        	@Override
        	  public void onClick(View v) {
        		msg =(TextView) findViewById(R.id.message);
           	 Cursor  val = mDbHelper.fetchNote(AndroidViews.id);
             Double val1 = Double.parseDouble(val.getString(3));
             val1 = val1 + 350.0;
              
           /*mDbHelper.updateFood(1,0);
             mDbHelper.updateFood(2,0);
            mDbHelper.updateFood(3,0);*/
            mDbHelper.updateFood(AndroidViews.id, val1);
             msg.setText("350 cal for milkshake is added");  
        	/*  Cursor c = mDbHelper.fetchAllNotes();
      	      if(c.moveToFirst())
      	       {
      	    	    do {
      	    	    	// DisplayTitle(c);
      	    	    	
      	    	    }
      	    	    while(c.moveToNext());
      	       }
      	     
      		mDbHelper.close();*/
        	  }
        	    });
	
    
   
}
	 public void DisplayTitle(Cursor c)
	 {
	     Toast.makeText(this, 
	             "ID " + c.getString(0) + "---" +
	             "TOTAL CALORIE FOOD:  " + c.getString(3),
	             Toast.LENGTH_LONG).show();  
	     Toast.makeText(this, "This data is displayed", Toast.LENGTH_LONG);
	 } 
}



