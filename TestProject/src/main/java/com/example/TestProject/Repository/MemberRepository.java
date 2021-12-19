package com.example.TestProject.Repository;

import java.util.Optional;

import com.example.TestProject.Model.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>{
	Optional<Member> findById(String id);
}
