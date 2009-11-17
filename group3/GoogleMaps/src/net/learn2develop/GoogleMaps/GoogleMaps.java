package net.learn2develop.GoogleMaps;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.KeyEvent;

public class GoogleMaps extends MapActivity implements LocationListener
{
    private MapView mapView;
    private MapController mc;
    private LocationManager lm;
    private MyLocationOverlay lo;
    private MapView mv;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initLocation();
        mapView = (MapView) findViewById(R.id.mapview1);
        mc = mapView.getController();

        String coordinates[] = {"40.747778", "-73.985556"};
        double lat = Double.parseDouble(coordinates[0]);
        double lng = Double.parseDouble(coordinates[1]);
     
        GeoPoint p = new GeoPoint(
            (int) (lat * 1E6), 
            (int) (lng * 1E6));

        mc.animateTo(p);
        mc.setZoom(17);
            
        mapView.setSatellite(true);
        mapView.setStreetView(true);
        
        mapView.invalidate();        
    }
    
    private void initLocation(){
    
             lm= (LocationManager) this.getSystemService(LOCATION_SERVICE);
             lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 500, this);
     
        }

    @Override
    protected boolean isRouteDisplayed() {
        // TODO Auto-generated method stub
        return false;
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
        case KeyEvent.KEYCODE_3:
            mc.zoomIn();
            break;
        case KeyEvent.KEYCODE_1:
            mc.zoomOut();
            break;
        }
        return super.onKeyDown(keyCode, event);
    }
    
    
    public void onLocationChanged(Location location) {
                List<Overlay> overlays = mv.getOverlays();
                lo = new MyLocationOverlay(this,mv);
                overlays.add(lo);
                lo.enableMyLocation();
     }

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}
