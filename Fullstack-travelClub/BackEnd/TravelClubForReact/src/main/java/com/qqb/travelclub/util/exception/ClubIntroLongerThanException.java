package com.qqb.travelclub.util.exception;

public class ClubIntroLongerThanException extends RuntimeException {
	//
	private static final long serialVersionUID = -4246979292397269539L;

	public ClubIntroLongerThanException(String message) {
		super(message); 
	}
}