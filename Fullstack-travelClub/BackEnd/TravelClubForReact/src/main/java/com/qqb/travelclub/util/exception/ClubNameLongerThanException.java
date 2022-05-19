package com.qqb.travelclub.util.exception;

public class ClubNameLongerThanException extends RuntimeException {
	//
	private static final long serialVersionUID = -4246979292397269539L;

	public ClubNameLongerThanException(String message) {
		super(message); 
	}
}