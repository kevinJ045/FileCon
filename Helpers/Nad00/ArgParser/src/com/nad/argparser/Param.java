package com.nad.argparser;

import java.util.Arrays;

public class Param {

	private final String [] params;

	public Param(String [] params){
		this.params = params;
	}

	public String [] toArray(){
		return params;
	}

	public String getParamAt(int index){
		if(index >= params.length) // check for the index
			throw new IndexOutOfBoundsException();

		return params[index];
	}

	public int length(){  return  params.length; } // returns the length of the param

	public boolean isEmpty(){
		return params.length == 0;
	} // check if the param is null

	@Override
	public String toString() {
		return "Param{" +
				"params=" + Arrays.toString(params) +
				'}';
	}

}
