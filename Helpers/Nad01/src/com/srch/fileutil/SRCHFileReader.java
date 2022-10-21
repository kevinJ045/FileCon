package com.srch.fileutil;

import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class SRCHFileReader {

	/*
	 * This is a file reader object
	 * reads as many formats
	 */
	public static String readRaw(Path p) {
		if (FileUtil.isFile(p))
			return null;

		if (!FileUtil.fileExist(p) || FileUtil.isDirectory(p))
			return null;

		StringBuilder mBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(FileUtil.fromPath(p)));
			String data = "";
			while ((data = bufferedReader.readLine()) != null) {
				mBuilder.append(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				assert bufferedReader != null;
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return mBuilder.toString();
	}

	public static String read(Path p) {
		if (FileUtil.isFile(p))
			return null;

		if (!FileUtil.fileExist(p) || FileUtil.isDirectory(p))
			return null;

		StringBuilder mBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(FileUtil.fromPath(p)));
			String data = "";
			while ((data = bufferedReader.readLine()) != null) {
				mBuilder.append(data);
				mBuilder.append((char) 10);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				assert bufferedReader != null;
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return mBuilder.toString();
	}

	public static List<String> readByLine(Path p) {
		List<String> lines = new ArrayList<>();
		try {
			Scanner fScanner = new Scanner(FileUtil.fromPath(p));
			while (fScanner.hasNext()) {
				lines.add(fScanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return lines;
	}

	public static String readLine(Path path, int start){
		if(start >= Objects.requireNonNull(readByLine(path)).size())
			return null;
		StringBuilder stringBuilder = new StringBuilder();
		List<String> lines = readByLine(path);
		for(int i = start; i< Objects.requireNonNull(lines).size(); i++){
			stringBuilder.append(lines.get(i));
		}
		return stringBuilder.toString();
	}

	public static String readLine(Path path, int start, int end){
		if(start >= Objects.requireNonNull(readByLine(path)).size() || end >= Objects.requireNonNull(readByLine(path)).size())
			return null;

		if(start==end)
			return null;

		StringBuilder stringBuilder = new StringBuilder();
		List<String> lines = readByLine(path);
		for(int i = start; i< end; i++){
			assert lines != null;
			stringBuilder.append(lines.get(i));
		}
		return stringBuilder.toString();
	}

}
