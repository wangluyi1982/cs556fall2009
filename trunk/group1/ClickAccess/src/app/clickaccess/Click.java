package app.clickaccess;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Click extends ListActivity {
   
    private WebinfoDbAdapter mDbHelper;
    private Cursor mWebinfoCursor;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webinfo_list);
        mDbHelper = new WebinfoDbAdapter(this);
        mDbHelper.open();
        mDbHelper.deleteWebinfo();
        mDbHelper.createWebinfo("Economic Crisis. Who will save the world?", "www.google.com");
        mDbHelper.createWebinfo("G2 or G20? What will China play in the world?", "www.time.com");
        fillData();
        registerForContextMenu(getListView());
    }
    
    private void fillData() {
        // Get all of the rows from the database and create the item list
        mWebinfoCursor = mDbHelper.fetchAllWebinfo();
        startManagingCursor(mWebinfoCursor);
        
        // Create an array to specify the fields we want to display in the list (only TITLE)
        String[] from = new String[]{WebinfoDbAdapter.KEY_TITLE};
        
        // and an array of the fields we want to bind those fields to (in this case just text1)
        int[] to = new int[]{R.id.text1};
        
        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter webinfo = 
        	    new SimpleCursorAdapter(this, R.layout.webinfo_row, mWebinfoCursor, from, to);
        setListAdapter(webinfo);
    }
    
/*
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch(item.getItemId()) {
        case INSERT_ID:
            createNote();
            return true;
        }
        
        return super.onMenuItemSelected(featureId, item);
    }

    
	public void onCreateContextMenu (ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, DELETE_ID, 0, R.string.menu_delete);
	}

    @Override
	public boolean onContextItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
        case DELETE_ID:
            AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
            mDbHelper.deleteNote(info.id);
            fillData();
            return true;
        }
        return super.onContextItemSelected(item);
	}

    private void createNote() {
        // TODO: fill in implementation
    	Intent i = new Intent(this, NoteEdit.class);
    	startActivityForResult(i, ACTIVITY_CREATE);
    }  */
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Cursor c = mWebinfoCursor;
        c.moveToPosition(position);
        Intent i = new Intent(this, Access.class);
        i.putExtra(WebinfoDbAdapter.KEY_ROWID, id);
        i.putExtra(WebinfoDbAdapter.KEY_TITLE, c.getString(
                c.getColumnIndexOrThrow(WebinfoDbAdapter.KEY_TITLE)));
        i.putExtra(WebinfoDbAdapter.KEY_URL, c.getString(
                c.getColumnIndexOrThrow(WebinfoDbAdapter.KEY_URL)));
        startActivity(i);
        // TODO: fill in rest of method
        
    }

/*    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	super.onActivityResult(requestCode, resultCode, intent);
    	Bundle extras = intent.getExtras();

    	switch(requestCode) {
    	case ACTIVITY_CREATE:
    	    String title = extras.getString(NotesDbAdapter.KEY_TITLE);
    	    String body = extras.getString(NotesDbAdapter.KEY_BODY);
    	    mDbHelper.createNote(title, body);
    	    fillData();
    	    break;
    	case ACTIVITY_EDIT:
    	    Long mRowId = extras.getLong(NotesDbAdapter.KEY_ROWID);
    	    if (mRowId != null) {
    	        String editTitle = extras.getString(NotesDbAdapter.KEY_TITLE);
    	        String editBody = extras.getString(NotesDbAdapter.KEY_BODY);
    	        mDbHelper.updateNote(mRowId, editTitle, editBody);
    	    }
    	    fillData();
    	    break;
    	}
    }*/
    
    

}
