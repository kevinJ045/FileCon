package com.nad.argparser;

import java.util.Arrays;

public class Argument {

	private final String [] args;

	private Argument(String [] args){
		this.args = args;
	}


	public static Argument from(String [] args){
		if(args == null)
			throw new NullPointerException();

		return new Argument(args);
	}


	// checks if args is 0
	public boolean isEmpty(){return args.length == 0;}

	// returns the length of args
	public int length(){
		return args.length;
	}

	public int argumentLength(){
		int count = 0;
		for (String arg : args) {
			if (isArgument(arg))
				count++;
		}

		return count;
	}

	public int parameterLength(){
		int count = 0;
		for (String arg : args) {
			if (!isArgument(arg))
				count++;
		}

		return count;
	}
	

	public ArgumentBuilder build(){
		return new ArgumentBuilder(args);
	}


	private static boolean isArgument(String s){
		return s.startsWith("--") || s.startsWith("-");
	}


	@Override
	public String toString() {
		return "Argument{" +
				"args=" + Arrays.toString(args) +
				'}';
	}
}
