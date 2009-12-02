package android_programmers_guide.AndroidViews;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
public class showfit extends Activity {
	public TextView msg;
	  public TextView txtmsg;
	private foodDbAdapter mDbHelper;
		 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.showfit);
	        mDbHelper = new foodDbAdapter(this);
	        mDbHelper.open();
	        Button okrun = (Button) findViewById(R.id.run);
	        Button okwalk = (Button) findViewById(R.id.walk);
	        Button okswim = (Button) findViewById(R.id.swim);
	        Button okhike = (Button) findViewById(R.id.hike);
	        Button okcycle = (Button) findViewById(R.id.cycle);
	        Button okread = (Button) findViewById(R.id.read);
	        


	    okrun.setOnClickListener(new OnClickListener() {
	      @Override
	      public void onClick(View v) {
	    	 msg =(TextView) findViewById(R.id.mess);
	      
	         Cursor  val = mDbHelper.fetchNote(AndroidViews.id);
	  	   	 Float val1 = Float.parseFloat(val.getString(2));
	  	     val1 = val1 + 225;
	  	   	 mDbHelper.updateFit(AndroidViews.id,val1);
	  	     msg.setText("225 cal were burnt for running");
	      }
	        });
	        
	        okwalk.setOnClickListener(new OnClickListener(){
	        	@Override
	        	  public void onClick(View v) {
	        	   msg =(TextView) findViewById(R.id.mess);
	           	 	Cursor  val = mDbHelper.fetchNote(AndroidViews.id);
	        		Double val1 = Double.parseDouble(val.getString(2));
	        		val1 = val1 + 135;
	        		 mDbHelper.updateFit(AndroidViews.id, val1);
	        	 	 msg.setText("135 cal were burnt for walking");  
	        	  }
	        	    });
	        okread.setOnClickListener(new OnClickListener(){
	        	@Override
	        	  public void onClick(View v) {
	        	   msg =(TextView) findViewById(R.id.mess);
	           	 	Cursor  val = mDbHelper.fetchNote(AndroidViews.id);
	        		Double val1 = Double.parseDouble(val.getString(2));
	        		val1 = val1 + 500;
	        		 mDbHelper.updateFit(AndroidViews.id, val1);
	        	 	 msg.setText("500 cal were burnt for reading");  
	        	  }
	        	    });
	        okcycle.setOnClickListener(new OnClickListener(){
	        	@Override
	        	  public void onClick(View v) {
	        	   msg =(TextView) findViewById(R.id.mess);
	           	 	Cursor  val = mDbHelper.fetchNote(AndroidViews.id);
	        		Double val1 = Double.parseDouble(val.getString(2));
	        		val1 = val1 + 665;
	        		 mDbHelper.updateFit(AndroidViews.id, val1);
	        	 	 msg.setText("665 cal were burnt for cycling");  
	        	  }
	        	    });
	        okhike.setOnClickListener(new OnClickListener(){
	        	@Override
	        	  public void onClick(View v) {
	        	   msg =(TextView) findViewById(R.id.mess);
	           	 	Cursor  val = mDbHelper.fetchNote(AndroidViews.id);
	        		Double val1 = Double.parseDouble(val.getString(2));
	        		val1 = val1 + 775;
	        		 mDbHelper.updateFit(AndroidViews.id, val1);
	        	 	 msg.setText("775 cal were burnt for hiking");  
	        	  }
	        	    });
	       
	        okswim.setOnClickListener(new OnClickListener(){
	        	@Override
	        	  public void onClick(View v) {
	        		msg =(TextView) findViewById(R.id.mess);
	           	 Cursor  val = mDbHelper.fetchNote(AndroidViews.id);
	             Double val1 = Double.parseDouble(val.getString(2));
	             val1 = val1 + 290;
	              	            
	        /*   mDbHelper.updateFit(1, 0.0);
	            mDbHelper.updateFit(2, 0.0);
	            mDbHelper.updateFit(3, 0.0); */
	         mDbHelper.updateFit(AndroidViews.id, val1); 
	            msg.setText("290 cal were burnt for swimming");  
	        	/*  Cursor c = mDbHelper.fetchAllNotes();
	      	    //  if(c.moveToFirst())
	      	       {
	      	    	    do {
	      	    	    	// DisplayTitle(c);
	      	    	    	
	      	    	    }
	      	    	    while(c.moveToNext());
	      	       }*/
	      	     
	      		//mDbHelper.close();
	        	  }
	        	    });
		
	    
	   
	}
		 public void DisplayTitle(Cursor c)
		 {
		     Toast.makeText(this, 
		             "id: " + c.getString(0) + "---" +
		             "TOTAL FITNESS CALORIES:  " + c.getString(2),
		             Toast.LENGTH_LONG).show();  
		     Toast.makeText(this, "This data is displayed", Toast.LENGTH_LONG);
		 } 
	}



	
