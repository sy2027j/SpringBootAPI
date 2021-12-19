package com.example.TestProject.Response;

import com.example.TestProject.Entity.Member;
import com.example.TestProject.Model.MemberDTO;

import java.util.List;

public class MemberResponse{

	public static class MemberListResponse extends CommonResponse<List>{
		public MemberListResponse(List<Member> member, String message){
			super(member,message);
		}
	}

	public static class MemResponse extends CommonResponse<MemberDTO> {
		public MemResponse(MemberDTO dto, String message){
			super(dto,message);
		}
	}
}