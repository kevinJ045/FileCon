package com.srch.fileutil;

import java.io.File;
import java.io.IOException;


public class FileUtil {

	/*
	 * static method
	 * returns true if file is created successfully or false
	 */
	public static boolean createFile(Path path) {
		if(path.isEmpty() || !path.isPath())
			return false;
		File mFile = fromPath(path);
		try {
			return mFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * creates files
	 */
	public static void createFiles(Path[] path) {
		for (Path p : path) {
			if (p.isEmpty() || !p.isPath())
				return;

			File mFile = new File(p.getPath());
			if (!mFile.exists()) {
				try {
					mFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
			}
		}
	}

	/*
	 * creates dir
	 */
	public static boolean createDir(Path path) {
		boolean created = false;
		if (path.isPath() && !path.isEmpty()) {
			File mFile = new File(path.getPath());
			if (mFile.exists())
				created = false;

			created = new File(path.getPath()).mkdir();
		}

		return created;
	}

	/*
	 * create dirs
	 */
	public static void createDirs(Path[] paths) {
		for (Path p : paths) {
			if (p.isPath() && !p.isEmpty()) {
				File mFile = new File(p.getPath());
				if (mFile.exists())
					return;
				mFile.mkdir();
			}
		}
	}


	/*
	 * deletes a given file or folder
	 */
	public static boolean delete(Path path) {
		boolean deleted = false;
		if (path.isEmpty() || !path.isPath())
			return false;

		File dFile = new File(path.getPath());
		if (dFile.exists())
			deleted = dFile.delete();

		return deleted;
	}

	public static void deleteFiles(Path[] paths) {
		for (Path p : paths) {
			if (!p.isPath() && p.isEmpty())
				return;
			File dFiles = new File(p.getPath());
			if (dFiles.exists())
				dFiles.delete();
		}
	}

	public static boolean rename(Path source, Path dest) {
		if (source.isEmpty() || !source.isPath())
			return false;
		File src = new File(source.getPath());
		File dst = new File(dest.getPath());
		if (!src.exists())
			return false;
		if (dest.isEmpty() || !dest.isPath())
			return false;

		return src.renameTo(dst);
	}

	public static void renameFiles(Path[] src, Path[] dst) {
		if (src.length != dst.length)
			return;

		for (int i = 0; i < src.length; i++) {
			if (src[i].isEmpty() || !src[i].isPath())
				return;

			if (dst[i].isEmpty() || !dst[i].isPath())
				return;

			File source = new File(src[i].getPath());
			File dest = new File(src[i].getPath());
			if (source.exists()) {
				source.renameTo(dest);
			}
		}
	}

	public static boolean fileExist(Path path) {
		if (!path.isPath() || path.isEmpty())
			return false;

		return new File(path.getPath()).exists();
	}

	public static boolean isFile(Path path) {
		if (!path.isPath() || path.isEmpty())
			return false;

		return new File(path.getPath()).isFile();
	}

	public static boolean isDirectory(Path path) {
		if (!path.isPath() || path.isEmpty())
			return false;

		return new File(path.getPath()).isDirectory();
	}

	public static String getFormat(Path path){
		if(!path.isPath() || path.isEmpty())
			return null;
		String fileName = fromPath(path).getName();
		return format(fileName);
	}

	private static String format(String s){
		if(!s.contains("."))
			return null;
		StringBuilder f = new StringBuilder();
		for(int i =s.length()-1; i>=0; i--){
			if(s.toCharArray()[i]=='.')
				break;
			f.append(s.toCharArray()[i]);
		}

		return f.reverse().toString();
	}
	public static File fromPath(Path p) {
		return new File(p.getPath());
	}


}