package com.example.TestProject;

import java.util.List;
import java.util.Optional;

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
import org.springframework.http.HttpStatus; import org.springframework.http.MediaType;


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
    public ResponseEntity<List<Member>> mainss() throws Exception {
    	List<Member> mem =repo.findAll();
    	
        return new ResponseEntity<List<Member>>(mem, HttpStatus.OK);
    }
    
    @GetMapping(value="/mem") 
    public ResponseEntity<List<Member>> getAllmembers() {
    	List<Member> member = ser.getMemberList(); 
    	return new ResponseEntity<List<Member>>(member, HttpStatus.OK); 
    }
    
    //pk로 검색
    @GetMapping(value="/memno") 
    public ResponseEntity<Optional<Member>> getnomembers(@RequestParam(value="no") int no) {
    	Optional<Member> member = repo.findById(no); 
    	return new ResponseEntity<Optional<Member>>(member, HttpStatus.OK); 
    }
    
    //mem_pw 로 검색
    @GetMapping(value="/mempw") 
    public ResponseEntity<Optional<Member>> getpwmembers(@RequestParam(value="pw") String pw) {
    	Optional<Member> member = repo.findByPw(pw); 
    	return new ResponseEntity<Optional<Member>>(member, HttpStatus.OK); 
    }
    
    //mem_id 로 검색
    @GetMapping(value="/memid") 
    public ResponseEntity<Optional<Member>> getidmembers(@RequestParam(value="id") String id) {
    	Optional<Member> member = repo.findById(id); 
    	return new ResponseEntity<Optional<Member>>(member, HttpStatus.OK); 
    }
    
    @PostMapping(value="/members")
    public ResponseEntity<Member> insertmembers(@RequestParam(value="id") String id, @RequestParam(value="pw") String pw) {
    	Member member =  Member.builder().id(id).pw(pw).build();
    	repo.save(member);
    	
    	return new ResponseEntity<Member>(member, HttpStatus.OK); 
    }
    
    @PatchMapping(value="/members")
    public ResponseEntity<Optional<Member>> updatemembers(@RequestParam(value="id") String id, @RequestParam(value="pw") String pw) {

    	Optional<Member> mem = repo.findById(id);
    	Member member1 = mem.get();
    	mem.ifPresent(a -> {
    		Member member =  Member.builder().no(member1.no).id(id).pw(pw).build();
        	repo.save(member);
    	});
    	
    	return new ResponseEntity<Optional<Member>>(mem, HttpStatus.OK); 
    }
    
    @DeleteMapping(value="/members")
    public ResponseEntity<Optional<Member>> deletemembers(@RequestParam(value="id") String id) {

    	Optional<Member> mem = repo.findById(id);
    	Member member1 = mem.get();
    	mem.ifPresent(a -> {
        	repo.delete(member1);
    	});
    	
    	return new ResponseEntity<Optional<Member>>(mem, HttpStatus.OK); 
    }
    
}