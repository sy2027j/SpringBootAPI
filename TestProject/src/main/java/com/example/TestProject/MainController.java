package com.example.TestProject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.TestProject.MemberDTO.ResponseDTO;
import com.example.TestProject.MemberDTO.ResponseDTO2;
import com.example.TestProject.MemberDTO.ResponseDTO3;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RestController
@RequiredArgsConstructor //의존성 주입 생성자 @Autowired 보다 좋대 

public class MainController {
	
	private final MemberListService ser;
	
	//@Responsebody 어노테이션을 사용하면 http요청 body를 자바 객체로 전달받을 수 있다.
    //@Valid 데이터 유효성 검사하는 어노테이션
	@RequestMapping(value="/")
    public String main() throws Exception {
        return "index";
    }

    //전체 회원 목록 조회
    @GetMapping(value="/members")
    public ResponseDTO2 mainss() throws Exception {
    	return ser.getMemberList();
    }

    //id로 회원 조회하기
    @GetMapping(value="/members/{id}") 
    public ResponseEntity<?> getIdMembers(@PathVariable String id) throws Exception {
    	MemberDTO member = ser.getMemberById(id);
    	return ResponseEntity.ok().body(new MemberResponse(member));
    }

    //회원 가입하기
    @PostMapping(value="/members")
    public ResponseEntity<?> insertMembers(MemberDTO dto) throws Exception {
    	MemberDTO newMem = ser.InsertMember(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MemberResponse(newMem));
    }

    //회원 정보 수정하기
    @PatchMapping(value="/members/{id}")
    public ResponseEntity<?> updateMembers(@PathVariable(value="id") String id, MemberDTO dto) throws Exception{
    	dto.setId(id);
    	return ResponseEntity.ok().body(new MemberResponse(ser.UpdateMember(dto)));
    }

    //회원 탈퇴하기
    @DeleteMapping(value="/members/{id}")
    public ResponseEntity<?> deleteMembers(@PathVariable(value="id") String id) throws Exception {
        if(ser.DeleteMember(id)){
            return ResponseEntity.ok().build();
        }else{
            throw new MyErrors.ResourceNotFoundException("회원 정보를 찾을 수 없습니다.");
        }
    }
}