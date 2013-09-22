// © 2013 onarray <http://www.onarray.com>

package com.onarray.freewifihk;

public class Premise {
	private Double longitude;
	private Double latitude;
	private String address;
	private int accuracy;
	private String name;

	public Premise(Double longitude, Double latitude, String address,
			int accuracy, String name) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.address = address;
		this.accuracy = accuracy;
		this.name = name;
	}

	public Premise(Double longitude, Double latitude, String name) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.name = name;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
