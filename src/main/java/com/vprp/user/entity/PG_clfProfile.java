package com.vprp.user.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "clf_profile_view", schema = "nrlm_master")
public class PG_clfProfile implements Serializable {
	
	private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clf_id;
    
    @Column(name = "state_id")
    private Long stateid;
    
    @Column(name = "district_id")
    private Long districtid;
    
    @Column(name = "block_id")
    private Long blockid;
    
    @Column(name = "panchayat_id")
    private Long panchayatid;
    
    @Column(name = "village_id")
    private Long villageid;
    
    @Column(name = "geo_entity_code")
    private String geoentitycode;
    
    @Column(name = "clf_code")
    private String clfcode;
    
    @Column(name = "clf_name")
    private String clfname;
    
    @Column(name = "parent_cbo_id")
    private Long parentcboid;
    
    @Column(name = "parent_cbo_type")
    private Integer parentcbotype;
    
    @Column(name = "data_source")
    private String datasource;
    
    @Column(name = "created_date")
    private Date createddate;
    
    @Column(name = "created_by")
    private String createdby;
    
    @Column(name = "state_code")
    private String statecode;
    
    @Column(name = "district_code")
    private String districtcode;
    
    @Column(name = "block_code")
    private String blockcode;
    
    @Column(name = "panchayat_code")
    private String panchayatcode;
    
    @Column(name = "village_code")
    private String villagecode;
    
    @Transient
    private Boolean isAssigned;
    
    @Transient
    private Integer Cboid;
   
    /*PG Application Start*/
    @Transient
    private String loginId;
    
    @Transient
    private Long userid;
    
    @Transient
    private String userStatus;
    
    @Transient
    private String userName;
    
    /*PG Application End*/
    
    

	public Integer getCboid() {
		return Cboid;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setCboid(Integer cboid) {
		Cboid = cboid;
	}

	public Boolean getAssigned() {
		return isAssigned;
	}

	public void setAssigned(Boolean assigned) {
		this.isAssigned = assigned;
	}

	public Long getClf_id() {
		return clf_id;
	}

	public void setClf_id(Long clf_id) {
		this.clf_id = clf_id;
	}

	public Long getStateid() {
		return stateid;
	}

	public void setStateid(Long stateid) {
		this.stateid = stateid;
	}

	public Long getDistrictid() {
		return districtid;
	}

	public void setDistrictid(Long districtid) {
		this.districtid = districtid;
	}

	public Long getBlockid() {
		return blockid;
	}

	public void setBlockid(Long blockid) {
		this.blockid = blockid;
	}

	public Long getPanchayatid() {
		return panchayatid;
	}

	public void setPanchayatid(Long panchayatid) {
		this.panchayatid = panchayatid;
	}

	public Long getVillageid() {
		return villageid;
	}

	public void setVillageid(Long villageid) {
		this.villageid = villageid;
	}

	public String getGeoentitycode() {
		return geoentitycode;
	}

	public void setGeoentitycode(String geoentitycode) {
		this.geoentitycode = geoentitycode;
	}

	public String getClfcode() {
		return clfcode;
	}

	public void setClfcode(String clfcode) {
		this.clfcode = clfcode;
	}

	public String getClfname() {
		return clfname;
	}

	public void setClfname(String clfname) {
		this.clfname = clfname;
	}

	public long getParentcboid() {
		return parentcboid;
	}

	public void setParentcboid(long parentcboid) {
		this.parentcboid = parentcboid;
	}

	public Integer getParentcbotype() {
		return parentcbotype;
	}

	public void setParentcbotype(Integer parentcbotype) {
		this.parentcbotype = parentcbotype;
	}

	public String getDatasource() {
		return datasource;
	}

	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	

	public String getStatecode() {
		return statecode;
	}

	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}

	public String getDistrictcode() {
		return districtcode;
	}

	public void setDistrictcode(String districtcode) {
		this.districtcode = districtcode;
	}

	public String getBlockcode() {
		return blockcode;
	}

	public void setBlockcode(String blockcode) {
		this.blockcode = blockcode;
	}

	public String getPanchayatcode() {
		return panchayatcode;
	}

	public void setPanchayatcode(String panchayatcode) {
		this.panchayatcode = panchayatcode;
	}

	public String getVillagecode() {
		return villagecode;
	}

	public void setVillagecode(String villagecode) {
		this.villagecode = villagecode;
	}

	public void setParentcboid(Long parentcboid) {
		this.parentcboid = parentcboid;
	}

	public PG_clfProfile() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PG_clfProfile(Long clf_id,Long stateid,Long districtid,Long blockid,Long panchayatid
			,Long villageid,String geoentitycode,String clfcode,String clfname,Long parentcboid
			,Integer parentcbotype,String datasource,String statecode,String districtcode
			,String blockcode,String panchayatcode,String villagecode) {
		this.clf_id = clf_id;
		this.stateid = stateid;
		this.districtid = districtid;
		this.blockid = blockid;
		this.panchayatid = panchayatid;
		this.villageid = villageid;
		this.geoentitycode = geoentitycode;
		this.clfcode = clfcode;
		this.clfname = clfname;
		this.parentcboid = parentcboid;
		this.parentcbotype = parentcbotype;
		this.datasource = datasource;
		this.statecode = statecode;
		this.districtcode = districtcode;
		this.blockcode = blockcode;
		this.panchayatcode = panchayatcode;
		this.villagecode = villagecode;
	}
	
	public PG_clfProfile(Long clf_id,String clfcode,String clfname,Integer Cboid,Long blockid
			,Long parentcboid) {
		this.clf_id = clf_id;
		this.blockid = blockid;
		this.clfcode = clfcode;
		this.clfname = clfname;
		this.Cboid = Cboid;
		this.parentcboid = parentcboid;
	} 
	
	public PG_clfProfile(Long clf_id,String clfcode,String clfname,Integer Cboid,Long blockid
			) {
		this.clf_id = clf_id;
		this.blockid = blockid;
		this.clfcode = clfcode;
		this.clfname = clfname;
		this.Cboid = Cboid;
	} 
	
	/* @OneToMany(targetEntity = PG_clfProfile.class, mappedBy = "PG_clfProfile", orphanRemoval = false, fetch = FetchType.LAZY)
	 private List<PG_clfProfile> PG_clfProfile;


	public List<PG_clfProfile> getPG_clfProfile() {
		return PG_clfProfile;
	}

	public void setPG_clfProfile(List<PG_clfProfile> pG_clfProfile) {
		PG_clfProfile = pG_clfProfile;
	}*/
	 
	 

	 

	 

	 


	
	 
	 
    
    

}
