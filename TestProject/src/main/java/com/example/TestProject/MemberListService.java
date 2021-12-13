package com.example.TestProject;

import java.util.ArrayList; 
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import com.example.TestProject.MemberDTO.ResponseDTO;
import com.example.TestProject.MemberDTO.ResponseDTO2;
import com.example.TestProject.MemberDTO.ResponseDTO3;

@Service
public class MemberListService{
	
	@Autowired
	private MemberRepository repo;

	public ResponseDTO2 getMemberList() {
		List<Member> members = new ArrayList<>();
		repo.findAll().forEach(e -> members.add(e));
		return new ResponseDTO2(200, "전체 회원 정보 조회", members);
	}
	
	public ResponseDTO getMemberById(String id) throws Exception{
		Optional<Member> mem = repo.findById(id); 
    	if(mem.isPresent()){
    		Member member =  Member.builder().id(mem.get().getId()).pw(mem.get().getPw()).build();
    		return new ResponseDTO(200, "회원 정보 조회", member);
    	}else {
    		throw new MyErrors.ResourceNotFoundException("회원 정보를 찾을 수 없습니다.");	
    	}
	}
	
	public ResponseDTO InsertMember(MemberDTO dto) throws Exception {
    	Optional<Member> check = repo.findById(dto.getId());
    	if(check.isPresent()){
    		throw new MyErrors.AlreadyExistException("이미 등록된 사용자");
    	}else {
    		Member member =  Member.builder().id(dto.getId()).pw(dto.getPw()).build();
    		repo.save(member);
    		return new ResponseDTO(201, "회원 정보가 등록되었습니다.", member);
    	}
    }
	
    public ResponseDTO UpdateMember(MemberDTO dto) throws Exception{
    	
    	Optional<Member> mem = repo.findById(dto.getId());
    	if(mem.isPresent()){
    		dto.setNo(mem.get().getNo());
        	Member member = repo.save(dto.toEntity());
    		return new ResponseDTO(200, "회원 정보가 수정되었습니다.", member);
    	}else {
    		throw new MyErrors.ResourceNotFoundException("회원 정보를 찾을 수 없습니다.");
    	}
    }
    
    public ResponseDTO3 DeleteMember(String id) throws Exception{
    	Optional<Member> mem = repo.findById(id);
    	if(mem.isPresent()){
    		repo.delete(mem.get());
    		return new ResponseDTO3(200, "회원 정보가 삭제되었습니다.");
    	}else {
    		throw new MyErrors.ResourceNotFoundException("회원 정보를 찾을 수 없습니다.");
    	}
    }
}
