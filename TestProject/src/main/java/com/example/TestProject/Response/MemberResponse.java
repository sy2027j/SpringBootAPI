package com.example.TestProject.Response;

import com.example.TestProject.Model.Dto.MemberDTO;

import java.util.List;

public class MemberResponse{

	public static class MemberListResponse extends CommonResponse<List>{
		public MemberListResponse(List<MemberDTO> member, String message){
			super(member,message);
		}
	}

	public static class MemResponse extends CommonResponse<MemberDTO> {
		public MemResponse(MemberDTO dto, String message){
			super(dto,message);
		}
	}
}