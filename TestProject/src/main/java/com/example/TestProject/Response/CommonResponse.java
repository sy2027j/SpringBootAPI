package com.example.TestProject.Response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommonResponse<T> {

	private String success="true";
	private String message;
	private T response;
	private int count;

	public CommonResponse(T response, String message) {
		this.response=response;
		if(response instanceof List) {  // data 를 List 형식으로 형변환이 가능
			this.count=((List<?>)response).size();
		}else {
			this.count=1;
		}
		this.message=message;
	}
}
