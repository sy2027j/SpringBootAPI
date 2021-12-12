package com.example.TestProject;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TestProject.UserDTO.ResponseDTO;
import com.example.TestProject.UserDTO.ResponseDTO2;
import com.example.TestProject.UserDTO.ResponseDTO3;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class MainController {
	
	@Autowired
	private MemberListService ser;
	
    @RequestMapping(value="/")
    public String main() throws Exception {
        return "index";
    }
    
    @GetMapping(value="/members")
    public ResponseDTO2 mainss() throws Exception {
    	return ser.getMemberList();
    }
    
    //id 로 검색
    @GetMapping(value="/members/{id}") 
    public ResponseDTO getidmembers(@PathVariable(value="id") String id) throws Exception {
    	return ser.getMemberById(id);
    }
    
    @PostMapping(value="/members")
    public ResponseDTO insertmembers(@RequestParam(value="id") String id, @RequestParam(value="pw") String pw) throws Exception {
    	return ser.InsertMember(id, pw);
    }
    
    @PatchMapping(value="/members")
    public ResponseDTO updatemembers(@RequestParam(value="id") String id, @RequestParam(value="pw") String pw) throws Exception{
    	return ser.UpdateMember(id, pw);
    }
    
    @DeleteMapping(value="/members")
    public ResponseDTO3 deletemembers(@RequestParam(value="id") String id) throws Exception{
    	return ser.DeleteMember(id);
    }
    
}