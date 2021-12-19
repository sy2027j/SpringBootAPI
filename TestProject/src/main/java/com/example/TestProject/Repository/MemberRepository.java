package com.example.TestProject.Repository;

import java.util.Optional;

import com.example.TestProject.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>{

	Optional<Member> findByPw(String pw);
	
	Optional<Member> findById(String id);
}
