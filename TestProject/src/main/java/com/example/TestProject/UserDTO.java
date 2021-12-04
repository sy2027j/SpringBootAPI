package com.example.TestProject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	private int no;
	private String id;
	private String pw;
	
	public UserDTO(Member member) {
		this.no=member.no;
		this.id=member.id;
		this.pw=member.pw;
	}
	
	@Getter
	@AllArgsConstructor
	public static class ResponseDTO {
		private int statusCode;
		private String message;
		private Member data;
	}
	
	@AllArgsConstructor
	public static class ResponseDTO2 {
		
	}
	
	@AllArgsConstructor
	public static class ResponseDTO3 {
		
	}
	
}