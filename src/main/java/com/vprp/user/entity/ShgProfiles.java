package com.vprp.user.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "shg_profile", schema = "nrlm_master")
public class ShgProfiles {

    public ShgProfiles() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shg_id")
    private Long shgId;

    @Column(name = "state_id")
    private Long stateId;

    @Column(name = "district_id")
    private Long districtId;

    @Column(name = "block_id")
    private Long blockId;

    @Column(name = "panchayat_id")
    private Long panchayatId;

    @Column(name = "village_id")
    private Long villageId;

    @Column(name = "geo_entity_code")
    private String geoEntityCode;

    @Column(name = "shg_code")
    private String shgCode;

    @Column(name = "shg_name")
    private String shgName;

    @Column(name = "parent_cbo_id")
    private Long parentCBOId;

    @Column(name = "parent_cbo_type")
    private Integer parentCBOType;

    @Column(name = "data_source")
    private String dataSource;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updateDate;

    @Transient
    private Boolean isAssigned;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(targetEntity = ShgApplications.class, mappedBy = "shgId", orphanRemoval = false, fetch = FetchType.LAZY)
    private List<ShgApplications> shgApplications;


    public String getGeoEntityCode() {
        return geoEntityCode;
    }

    public void setGeoEntityCode(String geoEntityCode) {
        this.geoEntityCode = geoEntityCode;
    }

    public List<ShgApplications> getShgApplications() {
        return shgApplications;
    }

    public void setShgApplications(List<ShgApplications> shgApplications) {
        this.shgApplications = shgApplications;
    }

    public ShgProfiles(Long shgId, Long stateId, Long districtId, Long blockId, Long panchayatId, Long villageId,
                       String geoEndityCode, String shgCode, String shgName, Long parentCBOId, Integer parentCBOType,
                       String dataSource, String createdBy, String updatedBy, Date createdDate, Date updateDate, Boolean isAssigned) {
        super();
        this.shgId = shgId;
        this.stateId = stateId;
        this.districtId = districtId;
        this.blockId = blockId;
        this.panchayatId = panchayatId;
        this.villageId = villageId;
        this.geoEntityCode = geoEndityCode;
        this.shgCode = shgCode;
        this.shgName = shgName;
        this.parentCBOId = parentCBOId;
        this.parentCBOType = parentCBOType;
        this.dataSource = dataSource;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
        this.isAssigned = isAssigned;
    }

    public ShgProfiles(Long shgId, String shgCode, String shgName, Long parentCBOId) {
        super();
        this.shgId = shgId;
        this.shgCode = shgCode;
        this.shgName = shgName;
        this.parentCBOId = parentCBOId;
    }

    public Long getShgId() {
        return shgId;
    }

    public void setShgId(Long shgId) {
        this.shgId = shgId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Long getBlockId() {
        return blockId;
    }

    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }

    public Long getPanchayatId() {
        return panchayatId;
    }

    public void setPanchayatId(Long panchayatId) {
        this.panchayatId = panchayatId;
    }

    public Long getVillageId() {
        return villageId;
    }

    public void setVillageId(Long villageId) {
        this.villageId = villageId;
    }

    public String getGeoEndityCode() {
        return geoEntityCode;
    }

    public void setGeoEndityCode(String geoEndityCode) {
        this.geoEntityCode = geoEndityCode;
    }

    public String getShgCode() {
        return shgCode;
    }

    public void setShgCode(String shgCode) {
        this.shgCode = shgCode;
    }

    public String getShgName() {
        return shgName;
    }

    public void setShgName(String shgName) {
        this.shgName = shgName;
    }

    public Long getParentCBOId() {
        return parentCBOId;
    }

    public void setParentCBOId(Long parentCBOId) {
        this.parentCBOId = parentCBOId;
    }

    public Integer getParentCBOType() {
        return parentCBOType;
    }

    public void setParentCBOType(Integer parentCBOType) {
        this.parentCBOType = parentCBOType;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Boolean getAssigned() {
        return isAssigned;
    }

    public void setAssigned(Boolean assigned) {
        isAssigned = assigned;
    }
}
