package com.example.TestProject.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.TestProject.Model.MemberDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="tbmember")
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mem_no")
	private Long no;
	
	@Column(name="mem_id")
	private String id;
	
	@Column(name="mem_pw")
	private String pw;
	
	public MemberDTO getMember() {
		return MemberDTO.builder().id(getId()).no(no).pw(pw).build();
	}

}
