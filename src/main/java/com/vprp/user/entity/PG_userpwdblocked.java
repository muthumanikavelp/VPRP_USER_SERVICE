package com.vprp.user.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "user_pwd_blocked", schema = "nrlm_security")
public class PG_userpwdblocked implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "password")
    private String password;
    
    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "created_by")
    private String createdBy;
    
    @Column(name = "updated_date")
    private String updatedDate;

    @Column(name = "updated_by")
    private String updatedBy;
    
    

	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public PG_userpwdblocked(long id,String password) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		this.password = password;
	}
	
    
	

}
