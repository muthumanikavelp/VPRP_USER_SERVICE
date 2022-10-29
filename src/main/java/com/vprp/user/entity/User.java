package com.vprp.user.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.exolab.castor.types.DateTime;

@Entity
@Table(name = "users", schema = "nrlm_security")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login_id")
    private String loginId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "mob_num_primary")
    private String mobNumPrimary;

    @Column(name = "mob_num_secondary")
    private String mobNumSecondary;

    @Column(name = "email")
    private String email;

    @JsonIgnore
    @Column(name = "designation")
    private String designation;

    @Column(name = "user_status")
    private String status;

    @Column(name = "channel")
    private String channel;

    @Column(name = "password")
    @JsonIgnore
    private String password;
    
    /*PG Application start*/
    @Column(name = "password_reset_flag")
    private Character pwdresetflag;
    
    @Column(name = "password_expire_flag")
    private Character pwdexpireflag;
    
    @Column(name = "password_validuntil")
    private LocalDateTime pwdvaliduntil;
    
    
    @Transient
    private String oldpassword;
    /*PG Application End*/

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private Long[] applicationRoles;
    
    

    

	public String getChannel() {
        return channel;
    }

    public Character getPwdresetflag() {
		return pwdresetflag;
	}

	public void setPwdresetflag(Character pwdresetflag) {
		this.pwdresetflag = pwdresetflag;
	}

	public void setChannel(String channel) {
        this.channel = channel;
    }

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private String applicationName;

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private String roleName;

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private String stateName;

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private String districtName;

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private String blockName;
    


    @OneToMany(targetEntity = UserCBOs.class, mappedBy = "user", orphanRemoval = false, fetch = FetchType.LAZY)
    private List<UserCBOs> userCBOs;

    @OneToMany(targetEntity = UserRoleGeography.class, mappedBy = "user", orphanRemoval = false, fetch = FetchType.LAZY)
    private List<UserRoleGeography> userRoleGeographies;

    @OneToMany(targetEntity = UserRoles.class, mappedBy = "user", orphanRemoval = false, fetch = FetchType.EAGER)
    private List<UserRoles> userRoles;

    public List<UserCBOs> getUserCBOs() {
        return userCBOs;
    }

    public void setUserCBOs(List<UserCBOs> userCBOs) {
        this.userCBOs = userCBOs;
    }

    public List<UserRoles> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRoles> userRoles) {
        this.userRoles = userRoles;
    }

    public List<UserRoleGeography> getUserRoleGeographies() {
        return userRoleGeographies;
    }

    public void setUserRoleGeographies(List<UserRoleGeography> userRoleGeographies) {
        this.userRoleGeographies = userRoleGeographies;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public User() {

    }

    public User(Long id, String loginId, String userName, String password, String email,Character pwdresetflag ) {
        this.loginId = loginId;
        this.userName = userName;
        this.id = id;
        this.password = password;
        this.email = email;
        this.pwdresetflag = pwdresetflag;
    }

    public User(String loginId, String userName, String mobNumPrimary, String mobNumSecondary, String email,
                String designation, String status, String password, Character Pwdresetflag, Character pwdexpireflag) {
        super();
        this.loginId = loginId;
        this.userName = userName;
        this.mobNumPrimary = mobNumPrimary;
        this.mobNumSecondary = mobNumSecondary;
        this.email = email;
        this.designation = designation;
        this.status = status;
        this.password = password;
        this.pwdresetflag = Pwdresetflag;
        this.pwdexpireflag = pwdexpireflag;
    }

    public User(Long id, String loginId, String userName, String mobNumPrimary, String mobNumSecondary, String status,
                String applicationName, String roleName, String stateName, String districtName, String blockName,
                String email) {
        this.id = id;
        this.loginId = loginId;
        this.userName = userName;
        this.mobNumPrimary = mobNumPrimary;
        this.mobNumSecondary = mobNumSecondary;
        this.status = status;
        this.applicationName = applicationName;
        this.roleName = roleName;
        this.stateName = stateName;
        this.districtName = districtName;
        this.blockName = blockName;
        this.email = email;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobNumPrimary() {
        return mobNumPrimary;
    }

    public void setMobNumPrimary(String mobNumPrimary) {
        this.mobNumPrimary = mobNumPrimary;
    }

    public String getMobNumSecondary() {
        return mobNumSecondary;
    }

    public void setMobNumSecondary(String mobNumSecondary) {
        this.mobNumSecondary = mobNumSecondary;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long[] getApplicationRoles() {
        return applicationRoles;
    }

    public void setApplicationRoles(Long[] applicationRoles) {
        this.applicationRoles = applicationRoles;
    }

    @JsonProperty("stateName")
    public String getStateName() {
        return this.stateName;
    }

    @JsonProperty("applicationName")
    public String getApplicationName() {
        return this.applicationName;
    }

    @JsonProperty("roleName")
    public String getRoleName() {
        return this.roleName;
    }

    @JsonProperty("districtName")
    public String getDistrictName() {
        return this.districtName;
    }

    @JsonProperty("blockName")
    public String getblockName() {
        return this.blockName;
    }
    
    /*PG Application Changes Start*/
    @Transient
    @JsonSerialize
    @JsonDeserialize
    private String userStatus;
    
    @Transient
    @JsonSerialize
    @JsonDeserialize
    private String application_id;
    
    
    @Transient
    @JsonSerialize
    @JsonDeserialize
    private String roleDesc;
    
    @Transient
    @JsonSerialize
    @JsonDeserialize
    private String appCode;
    
    @Transient
    @JsonSerialize
    @JsonDeserialize
    private String appName;
    
    @Transient
    @JsonSerialize
    @JsonDeserialize
    private String applicationStatus;
    
    @Transient
    @JsonSerialize
    @JsonDeserialize
    private String roleStatus;





	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getApplication_id() {
		return application_id;
	}

	public void setApplication_id(String application_id) {
		this.application_id = application_id;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public String getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}



    public class roleApplication {
    	@Transient
        @JsonSerialize
        @JsonDeserialize
        private String application_id;

		public String getApplication_id() {
			return application_id;
		}

		public void setApplication_id(String application_id) {
			this.application_id = application_id;
		}
    	
    	
    }

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private String OTP;
    
    @Transient
    @JsonSerialize
    @JsonDeserialize
    private String Result;
    
    @Transient
    @JsonSerialize
    @JsonDeserialize
    private String block_code;
    
    @Transient
    @JsonSerialize
    @JsonDeserialize
    private String block_name_en;

	public String getOTP() {
		return OTP;
	}

	public void setOTP(String oTP) {
		OTP = oTP;
	}

	public String getResult() {
		return Result;
	}

	public void setResult(String result) {
		Result = result;
	}

	public String getBlock_code() {
		return block_code;
	}

	public void setBlock_code(String block_code) {
		this.block_code = block_code;
	}

	public String getBlock_name_en() {
		return block_name_en;
	}

	public void setBlock_name_en(String block_name_en) {
		this.block_name_en = block_name_en;
	}

	public Character getPwdexpireflag() {
		return pwdexpireflag;
	}

	public void setPwdexpireflag(Character pwdexpireflag) {
		this.pwdexpireflag = pwdexpireflag;
	}

	public LocalDateTime getPwdvaliduntil() {
		return pwdvaliduntil;
	}

	public void setPwdvaliduntil(LocalDateTime pwdvaliduntil) {
		this.pwdvaliduntil = pwdvaliduntil;
	}
	
	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public User(Long id, String userName, String mobNumPrimary, String mobNumSecondary, Character pwdexpireflag,
			LocalDateTime pwdvaliduntil) {
    super();
    this.id = id;
    this.userName = userName;
    this.mobNumPrimary = mobNumPrimary;
    this.mobNumSecondary = mobNumSecondary;
    this.pwdexpireflag = pwdexpireflag;
    this.pwdvaliduntil = pwdvaliduntil;
}
    /*PG Application Changes End*/

}
