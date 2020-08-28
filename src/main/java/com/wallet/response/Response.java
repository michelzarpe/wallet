package com.wallet.response;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
	private T data;
	private List<String> errors;
	
	public List<String> getErrors(){
		if(this.errors==null) {
			this.errors = new ArrayList<String>();
		}
		return this.errors;
	}

	public T getData() {
		return data;
	}
}
