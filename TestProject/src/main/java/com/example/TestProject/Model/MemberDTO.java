package com.example.TestProject.Model;

import com.example.TestProject.Entity.Member;
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
}