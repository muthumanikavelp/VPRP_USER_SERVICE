package com.vprp.user.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name = "user_pwd", schema = "nrlm_security")
public class PG_UserPwd implements Serializable {
	
	public PG_UserPwd(Long userId, int passwordSlno, String password, Date createdDate, 
			String createdBy, Character currentpwdflag
			) {
		// TODO Auto-generated constructor stub
		super();
        this.userId = userId;
        this.passwordSlno = passwordSlno;
        this.password = password;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.currentpwdflag = currentpwdflag;
	}
	
	public PG_UserPwd(Long id,Long userId, int passwordSlno, String password, Date createdDate, 
			String createdBy, Character currentpwdflag
			) {
		// TODO Auto-generated constructor stub
		super();
		this.id = id;
        this.userId = userId;
        this.passwordSlno = passwordSlno;
        this.password = password;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.currentpwdflag = currentpwdflag;
	} 
	
	public PG_UserPwd(Long id) {
		// TODO Auto-generated constructor stub
		super();
		this.id = id;
        //this.userId = userId;
        //this.passwordSlno = passwordSlno;
        //this.password = password;
        //this.createdDate = createdDate;
        //this.createdBy = createdBy;
        //this.currentpwdflag = currentpwdflag;
	}

	public PG_UserPwd() {
		super();
		// TODO Auto-generated constructor stub
	}



	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "password_slno")
    private int passwordSlno;

    @Column(name = "password")
    private String password;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "created_by")
    private String createdBy;
    
    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "updated_by")
    private String updatedBy;
    
    @Column(name = "current_password_flag")
    private Character currentpwdflag;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getPasswordSlno() {
		return passwordSlno;
	}

	public void setPasswordSlno(int passwordSlno) {
		this.passwordSlno = passwordSlno;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Character getCurrentpwdflag() {
		return currentpwdflag;
	}

	public void setCurrentpwdflag(Character currentpwdflag) {
		this.currentpwdflag = currentpwdflag;
	}
    
    

}
