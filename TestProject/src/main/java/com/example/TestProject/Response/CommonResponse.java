package com.example.TestProject.Response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommonResponse<T> {

	private String message;
	private int count;
	private T data;

	public CommonResponse(T data, String message) {
		this.data=data;
		if(data instanceof List) {
			this.count=((List<?>)data).size();
		}else {
			this.count=1;
		}
		this.message=message;
	}
}
