package com.example.TestProject;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface MemberListService {
	List<Member> getMemberList() throws Exception;
}
