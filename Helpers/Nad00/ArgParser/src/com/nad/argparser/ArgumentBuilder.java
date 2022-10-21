package com.nad.argparser;

import java.util.Arrays;
import java.util.Objects;

public class ArgumentBuilder {

	private String [] args; // base property of the argument

	/**
	 * @param args
	 * Param builder is an object which is used to build a parameter
	 */
	public ArgumentBuilder(String [] args){
		this.args = args;
	}

	/* param builder */
	public Param parseParam(String... p){

		if (!checkParam(p))
			return null;

		String selected = getParam(p);
		int start = indexOf(selected);
		int capacity = countToNextArg(start);

		if(capacity == 0)
			return new Param(null);

		String[] params = new String[ countToNextArg(start) ]; // len of all the params to next parameters
		/* loop over index of args and add to param */
		for(int i=start+1, index =0; i<start+params.length+1; i++, index++)
			params[index] =  args[i];

		return new Param(params);
	}

	/**
	 *
	 * @param param
	 * @return String []
	 * returns String [] by building from Param class
	 */
	public String [] rawArgs(){return args;}

	/**
	 *  @param p
	 * @return boolean
	 * returns if the param (p) is in the argument list
	 */
	public boolean parse(String... choice){
		for(String s: args){
			for (String c: choice){
				if(c.equals(s))
					return true;
			}
		}

		return false;
	}


	public boolean isEmpty(){return  args.length == 0;}

	// returns the length of arguments
	public int length (){ return args.length;}


	/*
		@return boolean
		check if param is in argument
	 */
	public boolean checkParam(String... param){
		for(String s : args){
			for(String p: param){
				if(p.equals(s))
					return true;
			}
		}
		return false;
	}

	/*
	returns selected param form the args
	 */
	private String getParam(String... params){
		String param = null;
		for(String arg: args){
			for (String search: params){
				if(arg.equals(search))
					param = search;
			}
		}
		return param;
	}

	/*
	 * @param search
	 * @return int
	 * returns the index of search from a given arg list
	 */
	public int indexOf(String p){
		int index = 0;
		for(String s: args){
			if(s.equals(p))
				break;

			index++;
		}
		return index;
	}

	/*
	 * @param start
	 * @return int
	 * counts from a given index to next arg
	 * while counting arg check null or (-)
	 */
	public int countToNextArg(int start){
		// check for the next item if it is the last one or if the selected is the last one
		if(start+1 >= args.length)
			return 0;

		int count = 0;
		// count until item from the start to the item which not contain (-)
		for(int i = start+1; i< args.length; i++){
			if(args[i].startsWith("-"))
				break;

			count++;
		}

		return count;
	}


	/**
	 returns argument class as a string
	 */
	@Override
	public String toString() {
		return "ArgumentBuilder{" +
				"args=" + Arrays.toString(args) + // array is used to speed up the process to build the method
				'}';
	}
}
