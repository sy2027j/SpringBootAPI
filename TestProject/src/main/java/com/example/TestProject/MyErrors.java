package com.example.TestProject;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class MyErrors {

	@ResponseStatus(value = HttpStatus.CONFLICT)
	public static class AlreadyExistException extends Exception {

		private static final long serialVersionUID = 1L;

		public AlreadyExistException(String message) {
			super(message);
		}
	}
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public static class ResourceNotFoundException extends Exception{

		private static final long serialVersionUID = 1L;

		public ResourceNotFoundException(String message){
	    	super(message);
	    }
	}
	
	@ResponseStatus(value = HttpStatus.CREATED)
	public static class CreatedException extends Exception{

		private static final long serialVersionUID = 1L;

		public CreatedException(String message){
	    	super(message);
	    }
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	public static class OkException extends Exception{

		private static final long serialVersionUID = 1L;

		public OkException(String message){
	    	super(message);
	    }
	}

}
