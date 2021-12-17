package com.example.TestProject;

import java.util.ArrayList; 
import java.util.List;
import java.util.Optional;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.TestProject.MemberDTO.ResponseDTO;
import com.example.TestProject.MemberDTO.ResponseDTO2;
import com.example.TestProject.MemberDTO.ResponseDTO3;

@Service
@Transactional
public class MemberListService{
	
	@Autowired
	private MemberRepository repo;

	//회원 목록 조회
	@Transactional(readOnly = true)
	public ResponseDTO2 getMemberList() {
		List<Member> members = new ArrayList<>();
		repo.findAll().forEach(e -> members.add(e));
		return new ResponseDTO2(200, "전체 회원 정보 조회", members);
	}

	//id로 회원 조회
	public MemberDTO getMemberById(String id) throws Exception {
		return repo.findById(id).orElseThrow(()-> new MyErrors.ResourceNotFoundException("존재하지 않는 회원입니다.")).getMember();
	}

	//회원 가입하기
	public MemberDTO InsertMember(MemberDTO dto) throws Exception {
		Optional<Member> mem = repo.findById(dto.getId());
    	if(mem.isPresent()){
			System.out.println("mem = " + mem);
    		throw new MyErrors.AlreadyExistException("이미 등록된 사용자");
    	}else {
    		Member member =  Member.builder().id(dto.getId()).pw(dto.getPw()).build();
			MemberDTO newMem = repo.save(member).getMember();
			return newMem;
    	}
    }

	//회원 정보 수정하기
    public MemberDTO UpdateMember(MemberDTO dto) throws Exception{
    	Optional<Member> mem = repo.findById(dto.getId());
    	if(mem.isPresent()){
    		dto.setNo(mem.get().getNo());
        	MemberDTO member = repo.save(dto.toEntity()).getMember();
			return member;
    	}else {
    		throw new MyErrors.ResourceNotFoundException("회원 정보를 찾을 수 없습니다.");
    	}
    }

	//회원 탈퇴하기
    public boolean DeleteMember(String id) throws Exception{
    	Optional<Member> mem = repo.findById(id);
    	if(mem.isPresent()){
    		repo.delete(mem.get());
			return true;
    	}else
    		return false;
    }
}
