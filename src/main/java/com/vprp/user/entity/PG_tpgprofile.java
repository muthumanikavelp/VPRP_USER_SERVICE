package com.vprp.user.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "pg_profile", schema = "nrlm_master")
public class PG_tpgprofile implements Serializable{
	
	private static final long serialVersionUID = 1L;

    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pg_id;
    
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
    
    @Column(name = "geo_entity_code")
    private String geoentitycode;
    
    @Column(name = "pg_code")
    private String pgcode;
    
    @Column(name = "pg_name")
    private String pgname;
    
    @Column(name = "parent_cbo_id")
    private Long parentcboid;
    
    @Column(name = "parent_cbo_type")
    private Integer parentcbotype;
    
    @Column(name = "data_source")
    private String datasource;
    
    @Column(name = "created_date")
    private LocalDateTime createddate;
    
    @Column(name = "created_by")
    private String createdby;
    
    @Column(name = "updated_date")
    private LocalDateTime updateddate;
    
    @Column(name = "updated_by")
    private String updatedby;
    
    
    @Transient
    private Boolean isAssigned;
    
    @Transient
    private Integer cboId;
    
    
	
	public Integer getCboId() {
		return cboId;
	}

	public void setCboId(Integer cboId) {
		this.cboId = cboId;
	}

	public Boolean getAssigned() {
		return isAssigned;
	}

	public void setAssigned(Boolean assigned) {
		this.isAssigned = assigned;
	}

	

	public Long getPg_id() {
		return pg_id;
	}

	public void setPg_id(Long pg_id) {
		this.pg_id = pg_id;
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

	public String getPgcode() {
		return pgcode;
	}

	public void setPgcode(String pgcode) {
		this.pgcode = pgcode;
	}

	public String getPgname() {
		return pgname;
	}

	public void setPgname(String pgname) {
		this.pgname = pgname;
	}

	public Long getParentcboid() {
		return parentcboid;
	}

	public void setParentcboid(Long parentcboid) {
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

	public LocalDateTime getCreateddate() {
		return createddate;
	}

	public void setCreateddate(LocalDateTime createddate) {
		this.createddate = createddate;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	

	public LocalDateTime getUpdateddate() {
		return updateddate;
	}

	public void setUpdateddate(LocalDateTime updateddate) {
		this.updateddate = updateddate;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public PG_tpgprofile() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public PG_tpgprofile(Long pg_id,Long stateid,Long districtid,Long blockid,Long panchayatid
			,Long villageid,String geoentitycode,String pgcode,String pgname,Long parentcboid
			,Integer parentcbotype,String datasource) {
		this.pg_id = pg_id;
		this.stateid = stateid;
		this.districtid = districtid;
		this.blockid = blockid;
		this.panchayatid = panchayatid;
		this.villageid = villageid;
		this.geoentitycode = geoentitycode;
		this.pgcode = pgcode;
		this.pgname = pgname;
		this.parentcboid = parentcboid;
		this.parentcbotype = parentcbotype;
		this.datasource = datasource;
		
	}
	
	public PG_tpgprofile(Long pg_id,String pgcode,String pgname,Integer cboId,Long panchayatid) {
		this.pg_id = pg_id;
		this.panchayatid = panchayatid;
		this.pgcode = pgcode;
		this.pgname = pgname;
		this.cboId = cboId;
	}

	public PG_tpgprofile(Long pgId, String statecode, String districtcode, String blockcode, String panchayatcode,
			String villagcode, String pgname, String pgcode, LocalDateTime now, String usercode, Long parentcboid, Integer parentcbotype,
			Long stateid, Long districtid, Long blockid,Long panchayatid, Long villageid) {
		// TODO Auto-generated constructor stub
		this.pg_id = pgId;
		this.statecode = statecode;
		this.districtcode = districtcode;
		this.blockcode = blockcode;
		this.panchayatcode = panchayatcode;
		this.villagecode = villagcode;
		this.pgname = pgname;
		this.pgcode = pgcode;
		this.createddate = now;
		this.createdby = usercode;
		this.parentcbotype = 0;
		this.parentcboid = parentcboid;
		this.stateid = stateid;
		this.districtid = districtid;
		this.blockid = blockid;
		this.panchayatid = panchayatid;
		this.villageid = villageid;
		this.datasource = "PG";
		
		
	}
	
	
}
