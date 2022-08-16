package com.efarmer.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Value;

@Setter
@Getter
@ToString
@Value
public class User {
	
	private String userId;
	private long phone;
	private String firstName;
	private String lastName;
	private String address;


}
