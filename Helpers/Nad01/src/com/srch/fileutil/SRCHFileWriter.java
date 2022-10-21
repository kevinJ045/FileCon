package com.srch.fileutil;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class SRCHFileWriter {

	public static void write(Writer w){

		if(!FileUtil.fileExist(w.path()))
			return;

		if(!FileUtil.isFile(w.path()))
			return;

		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(FileUtil.fromPath(w.path())));
			bufferedWriter.write(w.data());
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			assert bufferedWriter != null;
			try {
				bufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
