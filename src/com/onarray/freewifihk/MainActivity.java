// © 2013 onarray <http://www.onarray.com>

package com.onarray.freewifihk;

import static com.onarray.freewifihk.Util.readFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity {

	static final LatLng HONG_KONG = new LatLng(22.278333, 114.158889);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		List<Hotspot> hotspots = getHotspots();

		GoogleMap map;
		map = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();

		map.moveCamera(CameraUpdateFactory.newLatLngZoom(HONG_KONG, 10));

		for (Hotspot hotspot : hotspots) {
			map.addMarker(new MarkerOptions().position(
					new LatLng(hotspot.getLatitude(), hotspot.getLongitude()))
					.title(hotspot.getName()));
		}
	}

	private List<Hotspot> getHotspots() {
		List<Hotspot> hotspots = new ArrayList<Hotspot>();
		try {
			JSONArray json = new JSONArray(readHotspots());

			for (int i = 0; i < json.length(); i++) {
				JSONObject hotspot = json.getJSONObject(i);

				Double longitude = hotspot.getDouble("longitude");
				Double latitude = hotspot.getDouble("latitude");
				String name = hotspot.getString("name");

				hotspots.add(new Hotspot(longitude, latitude, name));
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return hotspots;
	}

	private String readHotspots() {
		String file = "";
		try {
			InputStream in = getBaseContext().getAssets().open("hotspots.json");
			file = readFile(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.about:
            Intent intent = new Intent(this, AboutPreferenceActivity.class);
            startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
