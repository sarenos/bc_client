package com.example.bambicity.APILayers.Registration;

import com.example.bambicity.view.registration.RegistrationFragment;

public class RegistrationManagerConfig {
	
	protected String nickName;
	protected String sex;
	protected String age;
	private RegistrationFragment registrationFragment;
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public RegistrationFragment getRegistrationFragment() {
		return registrationFragment;
	}
	public void setRegistrationFragment(RegistrationFragment registrationFragment) {
		this.registrationFragment = registrationFragment;
	}
}
