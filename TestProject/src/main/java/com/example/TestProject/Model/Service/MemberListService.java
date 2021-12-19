package com.example.TestProject.Model.Service;

import java.util.ArrayList; 
import java.util.List;
import java.util.Optional;

import com.example.TestProject.Model.Entity.Member;
import com.example.TestProject.Model.Dto.MemberDTO;
import com.example.TestProject.Exception.MyErrors;
import com.example.TestProject.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor //의존성 주입
public class MemberListService{

	private final MemberRepository repo;

	//회원 목록 조회
	@Transactional(readOnly = true)
	public List<MemberDTO> getMemberList() {
		List<MemberDTO> dto = new ArrayList<>();
		for(int i=0; i<repo.findAll().size(); i++){
			dto.add(repo.findAll().get(i).getMember());
		}
		return dto;
	}

	//id로 회원 조회
	@Transactional(readOnly = true)
	public MemberDTO getMemberById(String id) throws Exception {
		return repo.findById(id).orElseThrow(()-> new MyErrors.ResourceNotFoundException("존재하지 않는 회원입니다.")).getMember();
	}

	//회원 가입하기
	@Transactional
	public MemberDTO InsertMember(MemberDTO dto) throws Exception {
		Optional<Member> mem = repo.findById(dto.getId());
    	if(mem.isPresent()){
    		throw new MyErrors.AlreadyExistException("이미 사용 중인 아이디입니다.");
    	}else {
    		Member member =  Member.builder().id(dto.getId()).pw(dto.getPw()).build();
			return repo.save(member).getMember();
    	}
    }

	//회원 정보 수정하기
	@Transactional
    public MemberDTO UpdateMember(MemberDTO dto) throws Exception{
    	Optional<Member> mem = repo.findById(dto.getId());
    	if(mem.isPresent()){
			Member member = Member.builder().no(mem.get().getNo()).id(dto.getId()).pw(dto.getPw()).build();
			MemberDTO dto1 = repo.save(member).getMember();
			return dto1;
    	}else {
    		throw new MyErrors.ResourceNotFoundException("회원 정보를 찾을 수 없습니다.");
    	}
    }

	//회원 탈퇴하기
	@Transactional
    public boolean DeleteMember(String id) throws Exception{
    	Optional<Member> mem = repo.findById(id);
    	if(mem.isPresent()){
    		repo.delete(mem.get());
			return true;
    	}else
			throw new MyErrors.ResourceNotFoundException("회원 정보를 찾을 수 없습니다.");
    }
}
