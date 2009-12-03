package cs556.group3;
 
import java.io.IOException;
import java.util.List;
import java.util.Locale;
 
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.MapView.LayoutParams;
 
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import cs556.group3.R;
 
public class MapsParis extends MapActivity 
{    
    MapView mapView; 
    MapController mc;
    GeoPoint p;
 
    class MapOverlay extends com.google.android.maps.Overlay
    {
        @Override
        public boolean draw(Canvas canvas, MapView mapView, 
        boolean shadow, long when) 
        {
            super.draw(canvas, mapView, shadow);                   
 
            //---translate the GeoPoint to screen pixels---
            Point screenPts = new Point();
            mapView.getProjection().toPixels(p, screenPts);
 
            //---add the marker---
            Bitmap bmp = BitmapFactory.decodeResource(
                getResources(), R.drawable.pushpin);            
            canvas.drawBitmap(bmp, screenPts.x, screenPts.y-50, null);         
            return true;
        }
        @Override
        public boolean onTouchEvent(MotionEvent event, MapView mapView) 
        {   
            //---when user lifts his finger---
            if (event.getAction() == 1) {                
                GeoPoint p = mapView.getProjection().fromPixels(
                    (int) event.getX(),
                    (int) event.getY());
 
                Geocoder geoCoder = new Geocoder(
                    getBaseContext(), Locale.getDefault());
                try {
                    List<Address> addresses = geoCoder.getFromLocation(
                        p.getLatitudeE6()  / 1E6, 
                        p.getLongitudeE6() / 1E6, 1);
 
                    String add = "";
                    if (addresses.size() > 0) 
                    {
                        for (int i=0; i<addresses.get(0).getMaxAddressLineIndex(); 
                             i++)
                           add += addresses.get(0).getAddressLine(i) + "\n";
                    }
 
                    Toast.makeText(getBaseContext(), add, Toast.LENGTH_SHORT).show();
                }
                catch (IOException e) {                
                    e.printStackTrace();
                }   
                return true;
            }
            else                
                return false;
        }        
      
    } 
 
    /** Called when the activity is first created. */
    @SuppressWarnings("deprecation")
	@Override

    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main1);
 
        mapView = (MapView) findViewById(R.id.mapView);
        LinearLayout zoomLayout = (LinearLayout)findViewById(R.id.zoom);  
        View zoomView = mapView.getZoomControls(); 
 
        zoomLayout.addView(zoomView, 
            new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, 
                LayoutParams.WRAP_CONTENT)); 
        mapView.displayZoomControls(true);
 
        mc = mapView.getController();
        
        
        String coordinates[] = {"48.8588888888", "2.2958333333"};
 
        
        double lat = Double.parseDouble(coordinates[0]);
        double lng = Double.parseDouble(coordinates[1]);
 
        p = new GeoPoint(
            (int) (lat * 1E6), 
            (int) (lng * 1E6));
 
        mc.animateTo(p);
        mc.setZoom(17); 
        //---Add a location marker---
        MapOverlay mapOverlay = new MapOverlay();
        List<Overlay> listOfOverlays = mapView.getOverlays();
        listOfOverlays.clear();
        listOfOverlays.add(mapOverlay);        
 
        mapView.invalidate();
        mapView.setSatellite(true);
        mapView.setStreetView(true);
    }
    public boolean onCreateOptionsMenu(Menu menu) 
    {
    	super.onCreateOptionsMenu(menu);
    	menu.add(0, 0, 0, "Gallery");
    	return true;
    }
    /** Override onOptionsItemSelected to execute code for each
    menu item */
    public boolean onOptionsItemSelected(MenuItem item){
    /** Select statement to handle calls
    to specific menu items */
    switch (item.getItemId()) {
    case 0:
    	showgallery();
        return true;
    }
    return true;
    }
    
    private void showgallery() {
    	// TODO Auto-generated method stub
    	Intent gallery = new Intent(this, DisplayParis.class);
    	startActivity(gallery);	

    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
        case KeyEvent.KEYCODE_DPAD_UP:
            mc.zoomIn();
            break;
        case KeyEvent.KEYCODE_DPAD_DOWN:
            mc.zoomOut();
            break;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected boolean isRouteDisplayed() {
        // TODO Auto-generated method stub
        return false;
    }
}