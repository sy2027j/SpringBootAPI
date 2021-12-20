package com.example.TestProject.Model.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {

	private Long no;
	private String id;

	@JsonIgnore
	private String pw;

	@Builder
	public MemberDTO(Long no, String id, String pw) {
		super();
		this.no = no;
		this.id = id;
		this.pw = pw;
	}

}