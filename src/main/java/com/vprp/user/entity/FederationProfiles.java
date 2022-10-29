package com.vprp.user.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "federation_profile", schema = "nrlm_master")
public class FederationProfiles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "federation_id")
    private Long federationId;

    @Column(name = "state_id")
    private Long stateId;

    @Column(name = "district_id")
    private Long districtId;

    @Column(name = "block_id")
    private Long blockId;

    @Column(name = "panchayat_id")
    private Long panchayatId;

    @Column(name = "geo_entity_code")
    private String geoEntityCode;

    @Column(name = "federation_code")
    private String federationCode;

    @Column(name = "federation_code_no")
    private String federationCodeNo;

    @Column(name = "federation_name")
    private String federationName;

    @Column(name = "cbo_type")
    private Integer cboType;

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
    @OneToMany(targetEntity = VoApplications.class, mappedBy = "voId", orphanRemoval = false, fetch = FetchType.LAZY)
    private List<VoApplications> voApplicationsList;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(targetEntity = ShgProfiles.class, mappedBy = "parentCBOId", orphanRemoval = false, fetch = FetchType.LAZY)
    private List<ShgProfiles> shgProfilesList;


    public FederationProfiles(Long federationId, Long stateId, Long districtId, Long blockId, Long panchayatId,
                              String geoEndityCode, String federationCodeNo, String federationCode, String federationName,
                              Integer cboType, Long parentCBOId, Integer parentCBOType,
                              String dataSource, String createdBy, String updatedBy, Date createdDate, Date updateDate, Boolean isAssigned) {
        super();
        this.federationId = federationId;
        this.stateId = stateId;
        this.districtId = districtId;
        this.blockId = blockId;
        this.panchayatId = panchayatId;
        this.geoEntityCode = geoEndityCode;
        this.federationCodeNo = federationCodeNo;
        this.federationCode = federationCode;
        this.federationName = federationName;
        this.parentCBOId = parentCBOId;
        this.parentCBOType = parentCBOType;
        this.dataSource = dataSource;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
        this.cboType = cboType;
        this.isAssigned = isAssigned;
    }


    public FederationProfiles() {
        super();
        // TODO Auto-generated constructor stub
    }


    public FederationProfiles(Long federationId, String federationCode, String federationName, Integer cboType,
                              Long panchayatId) {
        this.federationId = federationId;
        this.federationCode = federationCode;
        this.federationName = federationName;
        this.cboType = cboType;
        this.panchayatId = panchayatId;
    }

    public Long getFederationId() {
        return federationId;
    }

    public void setFederationId(Long federationId) {
        this.federationId = federationId;
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


    public String getGeoEndityCode() {
        return geoEntityCode;
    }

    public void setGeoEndityCode(String geoEndityCode) {
        this.geoEntityCode = geoEndityCode;
    }

    public String getFederationCode() {
        return federationCode;
    }

    public void setFederationCode(String federationCode) {
        this.federationCode = federationCode;
    }

    public String getFederationCodeNo() {
        return federationCodeNo;
    }

    public void setFederationCodeNo(String federationCodeNo) {
        this.federationCodeNo = federationCodeNo;
    }

    public void setCboType(Integer cboType) {
        this.cboType = cboType;
    }

    public Integer getCboType() {
        return cboType;
    }

    public String getFederationName() {
        return federationName;
    }

    public void setFederationName(String federationName) {
        this.federationName = federationName;
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

    public String getGeoEntityCode() {
        return geoEntityCode;
    }


    public void setGeoEntityCode(String geoEntityCode) {
        this.geoEntityCode = geoEntityCode;
    }


    public List<ShgProfiles> getShgProfilesList() {
        return shgProfilesList;
    }


    public void setShgProfilesList(List<ShgProfiles> shgProfilesList) {
        this.shgProfilesList = shgProfilesList;
    }


    public Boolean getAssigned() {
        return isAssigned;
    }

    public void setAssigned(Boolean assigned) {
        isAssigned = assigned;
    }

    public List<VoApplications> getVoApplicationsList() {
        return voApplicationsList;
    }

    public void setVoApplicationsList(List<VoApplications> voApplicationsList) {
        this.voApplicationsList = voApplicationsList;
    }
}
