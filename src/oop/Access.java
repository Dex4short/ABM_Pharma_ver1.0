package oop;

import oop.enumerations.SecurityRole;

public class Access {
	private int sec_id;
	private SecurityRole role;
	
	public Access(int sec_id, SecurityRole role) {
		setSec_id(sec_id);
		setRole(role);
	}
	public int getSec_id() {
		return sec_id;
	}
	public void setSec_id(int sec_id) {
		this.sec_id = sec_id;
	}
	public SecurityRole getRole() {
		return role;
	}
	public void setRole(SecurityRole role) {
		this.role = role;
	}
}
