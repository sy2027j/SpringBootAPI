package com.example.TestProject.Controller;

import com.example.TestProject.Model.Dto.MemberDTO;
import com.example.TestProject.Model.Service.MemberListService;
import com.example.TestProject.Response.CommonResponse;
import com.example.TestProject.Exception.MyErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "api/members")
@RequiredArgsConstructor //의존성 주입 생성자 @Autowired 보다 좋대
public class MainController {
	
	private final MemberListService ser;

	//@Responsebody 어노테이션을 사용하면 http요청 body를 자바 객체로 전달받을 수 있다.
    //@Valid 데이터 유효성 검사하는 어노테이션

    //전체 회원 목록 조회
    @GetMapping(value="")
    public ResponseEntity<?> MemberList() throws Exception {
    	//return ResponseEntity.ok().body(new MemberResponse.MemberListResponse(ser.getMemberList(),"회원 전체 조회"));
        return ResponseEntity.ok().body(new CommonResponse<>(ser.getMemberList(),"회원 전체 조회"));
    }


    //id로 회원 조회하기
    @GetMapping(value="/{id}")
    public ResponseEntity<?> getIdMembers(@PathVariable String id) throws Exception {
    	MemberDTO member = ser.getMemberById(id);
        //return ResponseEntity.ok().body(new MemberResponse.MemResponse(member,"회원 조회"));
        return ResponseEntity.ok().body(new CommonResponse<>(member,"회원 조회"));
    }

    //회원 가입하기
    @PostMapping(value="")
    public ResponseEntity<?> insertMembers(MemberDTO dto) throws Exception {
    	MemberDTO newMem = ser.InsertMember(dto);
        //return ResponseEntity.status(HttpStatus.CREATED).body(new MemberResponse.MemResponse(newMem,"회원 가입"));
        return ResponseEntity.status(HttpStatus.CREATED).body(new CommonResponse<>(newMem,"회원 가입"));
    }

    //회원 정보 수정하기
    @PatchMapping(value="/{id}")
    public ResponseEntity<?> updateMembers(@PathVariable(value="id") String id, MemberDTO dto) throws Exception{
    	dto.setId(id);
    	//return ResponseEntity.ok().body(new MemberResponse.MemResponse(ser.UpdateMember(dto),"회원 정보 수정"));
        return ResponseEntity.ok().body(new CommonResponse<>(ser.UpdateMember(dto),"회원 정보 수정"));
    }

    //회원 탈퇴하기
    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> deleteMembers(@PathVariable(value="id") String id) throws Exception {
        if(ser.DeleteMember(id)){
            return ResponseEntity.noContent().build();
        }else{
            throw new MyErrors.ResourceNotFoundException("회원 정보를 찾을 수 없습니다.");
        }
    }
}