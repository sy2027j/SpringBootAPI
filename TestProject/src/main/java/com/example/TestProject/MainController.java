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
    public ResponseEntity<Optional<Member>> getidmembers(@PathVariable(value="id") String id) {
    	Optional<Member> member = repo.findById(id); 
    	if(member.isPresent()){
    		return new ResponseEntity<Optional<Member>>(member, HttpStatus.OK);
    	}else {
    		return new ResponseEntity<Optional<Member>>(member, HttpStatus.NOT_FOUND);
    	}
    	
    }
    
    @PostMapping(value="/members")
    public ResponseEntity<Map<String, Object>> insertmembers(@RequestParam(value="id") String id, @RequestParam(value="pw") String pw) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
    	Optional<Member> check = repo.findById(id);
    	if(check.isPresent()){
    		throw new MyErrors.AlreadyExistException("이미 등록된 사용자");
    	}else {
    		Member member =  Member.builder().id(id).pw(pw).build();
    		repo.save(member);
    		map.put("message", "회원 정보가 등록되었습니다.");
    		map.put("status", "success");
    	//	throw new MyErrors.CreatedException("등록성공~!");
    		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.CREATED);
    	}
    }
    
    @PatchMapping(value="/members")
    public ResponseEntity<Map<String, Object>> updatemembers(@RequestParam(value="id") String id, @RequestParam(value="pw") String pw) throws Exception{
    	Map<String, Object> map = new HashMap<String, Object>();
    	Optional<Member> mem = repo.findById(id);
    	if(mem.isPresent()){
    		Member member1 = mem.get();
    		Member member =  Member.builder().no(member1.no).id(id).pw(pw).build();
        	repo.save(member);
        	map.put("message", "회원 정보가 수정되었습니다.");
    		map.put("status", "success");
    		throw new MyErrors.OkException("수정성공~!");
    		//return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    	}else {
    		throw new MyErrors.ResourceNotFoundException("수정 실패");
    	}
    }
    
    @DeleteMapping(value="/members")
    public ResponseEntity<Map<String, Object>> deletemembers(@RequestParam(value="id") String id) throws Exception{
    	Map<String, Object> map = new HashMap<String, Object>();
    	Optional<Member> mem = repo.findById(id);
    	if(mem.isPresent()){
    		Member member1 = mem.get();
    		repo.delete(member1);
        	map.put("message", "회원 정보가 삭제되었습니다.");
    		map.put("status", "success");
    		//return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    		throw new MyErrors.OkException("삭제성공~!");
    	}else {
    		map.put("message", "회원 정보 삭제를 실패했습니다.");
    		map.put("status", "fail");
    		//return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
    		throw new MyErrors.ResourceNotFoundException("삭제 실패");
    	}
    	
    }
    
}