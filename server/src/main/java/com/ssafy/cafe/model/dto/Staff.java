package com.ssafy.cafe.model.dto;

public class Staff {
	private Integer sId;
    private String id;
    private String nickname;
    private String pwd;
    
    public Staff() {}
    
    public Staff(String id, String nickname, String pwd) {
		this.id = id;
		this.nickname = nickname;
		this.pwd = pwd;
    }
    
	public Staff(Integer sId, String id, String nickname, String pwd) {
		super();
		this.sId = sId;
		this.id = id;
		this.nickname = nickname;
		this.pwd = pwd;
	}

	public Integer getSId() {
		return sId;
	}
	public void setSId(Integer sId) {
		this.sId = sId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return "Staff [sId=" + sId + ", id=" + id + ", nickname=" + nickname + ", pwd=" + pwd + "]";
	}
    
}
