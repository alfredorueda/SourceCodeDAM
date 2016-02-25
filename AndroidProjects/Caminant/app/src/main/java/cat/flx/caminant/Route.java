package cat.flx.caminant;

import java.util.ArrayList;
import java.util.List;

public class Route extends ArrayList<LatLon> {
    private static final long serialVersionUID = 1L;
    private double minLat, maxLat, minLon, maxLon;

    public Route() {
        super();
        minLat = maxLat = -1;
        minLon = maxLon = -1;
    }

    public boolean add(LatLon l) {
        super.add(l);
        if (minLat == -1) {
            minLat = maxLat = l.latitude;
            minLon = maxLon = l.longitude;
        }
        else {
            minLat = Math.min(minLat, l.latitude);
            maxLat = Math.max(maxLat, l.latitude);
            minLon = Math.min(minLon, l.longitude);
            maxLon = Math.max(maxLon, l.longitude);
        }
        for (RouteListener lstnr : lstnrs) lstnr.latLonAdded(l);
        return true;
    }

    public double getMinLatitude() { return minLat; }
    public double getMaxLatitude() { return maxLat; }
    public double getMinLongitude() { return minLon; }
    public double getMaxLongitude() { return maxLon; }

    public interface RouteListener {
        public void latLonAdded(LatLon l);
    }

    public List<RouteListener> lstnrs = new ArrayList<RouteListener>();
    public void addRouteListener(RouteListener lstnr) {
        lstnrs.add(lstnr);
    }
}
