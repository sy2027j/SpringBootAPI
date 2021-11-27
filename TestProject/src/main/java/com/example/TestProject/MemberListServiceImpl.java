package com.example.TestProject;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface MemberListServiceImpl {
	List<Member> getMemberList() throws Exception;
}
