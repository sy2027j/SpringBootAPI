package com.example.TestProject;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {

	private Long no;
	private String id;
	private String pw;
	
	@Builder
	public MemberDTO(Long no, String id, String pw) {
		super();
		this.no = no;
		this.id = id;
		this.pw = pw;
	}

	public Member toEntity() {
		return Member.builder()
				.no(no)
				.id(id)
				.pw(pw)
				.build();
	}

	@Getter
	@AllArgsConstructor
	public static class ResponseDTO {
		private int statusCode;
		private String message;
		private Member data;
	}
	
	@Getter
	@AllArgsConstructor
	public static class ResponseDTO2 {
		private int statusCode;
		private String message;
		private List<Member> member;
	}
	
	@Getter
	@AllArgsConstructor
	public static class ResponseDTO3 {
		private int statusCode;
		private String message;
	}	
}