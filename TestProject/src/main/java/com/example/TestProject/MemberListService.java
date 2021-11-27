package com.example.TestProject;

import java.util.ArrayList; 
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberListService implements MemberListServiceImpl{
	
	@Autowired
	private MemberRepository repo;

	@Override
	public List<Member> getMemberList() {
		List<Member> members = new ArrayList<>();
		repo.findAll().forEach(e -> members.add(e)); 
		return members;
		//return repo.findAll(); 
	}

}
