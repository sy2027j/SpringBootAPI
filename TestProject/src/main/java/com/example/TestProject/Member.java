package com.example.TestProject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name="tbmember")
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int mem_no;
	
	public String mem_id;
	public String mem_pw;

}
