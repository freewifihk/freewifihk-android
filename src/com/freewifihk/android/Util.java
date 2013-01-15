package com.freewifihk.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Util {

	public static String readFile(InputStream in) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));

		StringBuilder buffer = new StringBuilder();

		String line = reader.readLine();
		while (line != null) {
			buffer.append(line);
			buffer.append('\n');
			line = reader.readLine();
		}

		return buffer.toString();
	}
}
