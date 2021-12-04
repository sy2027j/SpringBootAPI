package com.example.TestProject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TestProject.UserDTO.ResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class MainController {
	
	@Autowired
	private MemberRepository repo;
	
	@Autowired
	private MemberListServiceImpl ser;
	
    @RequestMapping(value="/")
    public String main() throws Exception {
        return "index";
    }
    
    @GetMapping(value="/members")
    public ResponseEntity<Map<String, Object>> mainss() throws Exception {
    	List<Member> mem =repo.findAll();
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("data", mem);
    	return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }
    
    //mem_id 로 검색
    @GetMapping(value="/members/{id}") 
    public ResponseDTO getidmembers(@PathVariable(value="id") String id) throws Exception {
    	Optional<Member> mem = repo.findById(id); 
    	if(mem.isPresent()){
    		Member member =  Member.builder().id(mem.get().getId()).pw(mem.get().getPw()).build();
    		return new ResponseDTO(200, "회원 정보 조회", member);
    	}else {
    		throw new MyErrors.ResourceNotFoundException("회원 정보를 찾을 수 없습니다.");
    	}
    	
    }
    
    @PostMapping(value="/members")
    public ResponseDTO responseDinsertmembers(@RequestParam(value="id") String id, @RequestParam(value="pw") String pw) throws Exception {
    	Optional<Member> check = repo.findById(id);
    	if(check.isPresent()){
    		throw new MyErrors.AlreadyExistException("이미 등록된 사용자");
    	}else {
    		Member member =  Member.builder().id(id).pw(pw).build();
    		repo.save(member);
    		return new ResponseDTO(201, "회원 정보가 등록되었습니다.", member);
    	}
    }
    
    @PatchMapping(value="/members")
    public ResponseDTO updatemembers(@RequestParam(value="id") String id, @RequestParam(value="pw") String pw) throws Exception{
    	Optional<Member> mem = repo.findById(id);
    	if(mem.isPresent()){
    		Member member =  Member.builder().no(mem.get().no).id(id).pw(pw).build();
        	repo.save(member);
    		return new ResponseDTO(200, "회원 정보가 수정되었습니다.", member);
    	}else {
    		throw new MyErrors.ResourceNotFoundException("수정 실패");
    	}
    }
    
    @DeleteMapping(value="/members")
    public ResponseEntity<Map<String, Object>> deletemembers(@RequestParam(value="id") String id) throws Exception{
    	Optional<Member> mem = repo.findById(id);
    	if(mem.isPresent()){
    		repo.delete(mem.get());
    		//return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    		throw new MyErrors.OkException("삭제성공~!");
    	}else {
    		throw new MyErrors.ResourceNotFoundException("삭제 실패");
    	}
    	
    }
    
}