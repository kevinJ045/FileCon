package com.nad.filecon;

import com.nad.argparser.Argument;
import com.nad.argparser.ArgumentBuilder;
import com.nad.argparser.Param;

import java.io.File;

import java.util.ArrayList;
import java.util.Objects;

public class FileCon {

	private String path;
	private String [] searches;
	private boolean verbose;
	private static int TOTAL_FILE=0, FOUND_FILE=0;

	private static ArrayList<String>searched;
	private File file;


	static{
		searched = new ArrayList<String>();
	}

	private FileCon(String path, String [] searches, boolean verbose) {
		this.path=path;
		this.searches=searches;
		this.verbose =verbose;

		file = new File(path);

	}


	public static FileCon searchFrom(String path, String [] searches, boolean verbose){
		return new FileCon(path, searches, verbose);
	}

	public void search(){
		if(!file.exists()){
			System.out.println("\033[31m[!]Invalid operation \033[00m");
			return;
		}
		if(file.isFile()){
			System.out.println("\033[31m[!]search cannot start from file\033[00m");
			return;
		}
		listFileAndSearch(file.listFiles(), 0, searches);
		System.out.println("____________________________________________________________________________________");
		for(String found : searched){
			System.out.println(found);
		}
		System.out.println("\033[32m "+FOUND_FILE+"\033[00m out of \033[32m"+TOTAL_FILE+" \033[00mmaotches found");
	}

	/*
	 * Base algorithm to list files recursively
	*/
	private void listFileAndSearch(File [] nodes, int index, Object[] searches){
		if(index > nodes.length)
			return;

		for(File child : nodes){
			if(child.isFile()){
				if(this.verbose)
					System.out.println(child.getAbsolutePath());

				for(Object o: searches){
					if( child.getName().equalsIgnoreCase(o.toString()) || child.getName().contains(o.toString()) ){
						searched.add(child.getAbsolutePath());
						FOUND_FILE++;
					}
				}
			}
			if(child.isDirectory()){
				// open recursively
				listFileAndSearch(Objects.requireNonNull(child.listFiles()), Objects.requireNonNull(child.listFiles()).length,searches);
			}
		}

		TOTAL_FILE++;
		index += 1;
	}


	public static void main(String[] args) {

		Argument argument = Argument.from(args);

		if (argument.isEmpty()) {
			System.out.println("\033[31m[ ! ] no parameter is passed\033[00m");
			System.out.println("\033[32m[ + ] \033[00mExiting program");
			System.exit(0);
		} else {

			ArgumentBuilder builder = argument.build();
			if (builder.isEmpty()){
				System.out.println("\033[31m[ ! ] no parameter or is passed\033[00m");
				return;
			}

			if (builder.length() == 1)
				if(builder.parse("--help", "-h")){
					printHelp();
				}
				else{
					System.out.println("\033[31m[ ! ] unknown command \033[00m");
					System.exit(1);
				}

			boolean verbose = builder.parse("-v", "--verbose");
			Param path = builder.parseParam("-p");
			Param find = builder.parseParam("-f");
			FileCon fCon = FileCon.searchFrom(path.getParamAt(0), find.toArray(), verbose);
			fCon.search();

		}
	}

	private static void printHelp(){
		System.out.println("\033[32mhelp menu\033[00m" +
				"\npass a path where search \033[32m[-p, --path]\033[00m" +
				"\npass a file name or format to search \033[32m[-f, --find]\033[00m" +
				"\nverbose mode \033[32m[-v, --verbose]\033[00m" +
				"\nhelp or this help menu \033[32m[-h, --help, -?]\033[00m");
	}

}
