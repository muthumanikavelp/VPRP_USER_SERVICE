package com.vprp.user.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import com.vprp.user.model.CustomIntegerArrayType;

import lombok.Getter;

@Entity
@Table(name = "user_role_geographies", schema = "nrlm_security")
public class UserRoleGeography implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "state")
	private Long state;

	@Column(name = "district")
	private Long district;

	@Column(name = "block")
	private Long block;

	@Column(name = "gram_panychayat", columnDefinition = "int[]")
	@Type(type = "com.vprp.user.model.CustomIntegerArrayType")
	private Integer[] grampPanchayat;

	@Column(name = "status")
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public StateMaster getStateData() {
		return stateData;
	}

	public void setStateData(StateMaster stateData) {
		this.stateData = stateData;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "village", columnDefinition = "int[]")
	@Type(type = "com.vprp.user.model.CustomIntegerArrayType")
	private Integer[] village;

	@Column(name = "national_level")
	private Boolean nationalLevel;

	@Column(name = "application_role_id")
	private Long applicationRoleId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	@Fetch(FetchMode.JOIN)
	private User user;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "state", referencedColumnName = "state_id", insertable = false, updatable = false)
	private StateMaster stateData;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "district", referencedColumnName = "district_id", insertable = false, updatable = false)
	private DistrictMaster districtData;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "block", referencedColumnName = "block_id", insertable = false, updatable = false)
	private BlockMaster blockData;

	public UserRoleGeography() {

	}

	public UserRoleGeography(Long userId, Long state, Long district, Long block, Integer[] grampPanchayat,
			Integer[] village, Boolean nationalLevel, Long applicationRoleId) {

		this.state = state;
		this.district = district;
		this.block = block;
		this.grampPanchayat = grampPanchayat;
		this.village = village;
		this.nationalLevel = nationalLevel;
		this.userId = userId;
		this.applicationRoleId = applicationRoleId;
	}

	public UserRoleGeography(Long userId, Long state, Long district, Long block, Integer[] grampPanchayat,
			Integer[] village, Boolean nationalLevel, Long applicationRoleId, String status) {

		this.state = state;
		this.district = district;
		this.block = block;
		this.grampPanchayat = grampPanchayat;
		this.village = village;
		this.nationalLevel = nationalLevel;
		this.userId = userId;
		this.applicationRoleId = applicationRoleId;
		this.status = status;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public Long getDistrict() {
		return district;
	}

	public void setDistrict(Long district) {
		this.district = district;
	}

	public Long getBlock() {
		return block;
	}

	public void setBlock(Long block) {
		this.block = block;
	}

	public Boolean getNationalLevel() {
		return nationalLevel;
	}

	public void setNationalLevel(Boolean nationalLevel) {
		this.nationalLevel = nationalLevel;
	}

	public Integer[] getGrampPanchayat() {
		return grampPanchayat;
	}

	public void setGrampPanchayat(Integer[] grampPanchayat) {
		this.grampPanchayat = grampPanchayat;
	}

	public Integer[] getVillage() {
		return village;
	}

	public void setVillage(Integer[] village) {
		this.village = village;
	}

	public void setApplicationRoleId(Long applicationRoleId) {
		this.applicationRoleId = applicationRoleId;
	}

	public Long getApplicationRoleId() {
		return this.applicationRoleId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DistrictMaster getDistrictData() {
		return districtData;
	}

	public void setDistrictData(DistrictMaster districtData) {
		this.districtData = districtData;
	}

	public BlockMaster getBlockData() {
		return blockData;
	}

	public void setBlockData(BlockMaster blockData) {
		this.blockData = blockData;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", state=" + state + ", district=" + district + ", block=" + block
				+ ", grampPanchayat=" + grampPanchayat + ", village=" + village + "]";
	}

}
