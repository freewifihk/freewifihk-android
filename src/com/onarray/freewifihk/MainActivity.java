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

		List<Premise> premises = getPremises();

		GoogleMap map;
		map = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();

		map.moveCamera(CameraUpdateFactory.newLatLngZoom(HONG_KONG, 10));

		for (Premise premise : premises) {
			map.addMarker(new MarkerOptions().position(
					new LatLng(premise.getLatitude(), premise.getLongitude()))
					.title(premise.getName()));
		}
	}

	private List<Premise> getPremises() {
		List<Premise> premises = new ArrayList<Premise>();
		try {
			JSONArray json = new JSONArray(readPremises());

			for (int i = 0; i < json.length(); i++) {
				JSONObject premise = json.getJSONObject(i);

				Double longitude = premise.getDouble("longitude");
				Double latitude = premise.getDouble("latitude");
				String name = premise.getString("name");

				premises.add(new Premise(longitude, latitude, name));
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return premises;
	}

	private String readPremises() {
		String file = "";
		try {
			InputStream in = getBaseContext().getAssets().open("premises.json");
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
