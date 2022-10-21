package com.srch.fileutil;

import com.srch.fileutil.annotations.File;

import java.lang.annotation.ElementType;

/*
 * Path class is used to check validity of a path
 * works on linux based and windows OS
 */
public class Path {

	private String path; // base path

	/*
	 * uses factory method to construct in order to use methods efficiently
	 */
	@File(values = ElementType.CONSTRUCTOR)
	private Path(String path){
		this.path = path;
	}

	/*
	 * factory method class initialization
	 */
	public static Path form(String path){
		return new Path(path);
	}

	/*
	 * returns direct path
	 */
	@File(values = {ElementType.METHOD})
	public String getPath() {
		return path;
	}

	/*
	 * checks if path is empty or null
	 */
	public boolean isEmpty(){return path==null || path.equals("");}

	/*
	 * it is based on operating systems to check if a path has the '/' char
	 * on windows path usually starts with letters
	 * on linux based OS including android path starts with '/'
	 */
	public boolean isPath(){  return path.startsWith("/") || path.contains("/"); }

	/*
	 * returns path at a given index
	 * as isEmpty() method this one is also based on OS
	 */
	public String getPathAt(int index){
		String p = null;
		String [] subPath = null;
		if(path.startsWith("/"))
			path = path.substring(1, path.length());

		subPath = path.split("/");
		if(index >= subPath.length)
			return null;

		for(int i = 0; i<subPath.length; i++){
			if(i == index) {
				p = subPath[i];
				return p;
			}
		}

		return null;
	}

	@Override
	public String toString() {
		return "Path{" +
				"path='" + path + '\'' +
				'}';
	}
}
