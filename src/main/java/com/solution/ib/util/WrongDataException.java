package com.solution.ib.util;

public class WrongDataException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Invalid Data".toUpperCase();
	}
}
