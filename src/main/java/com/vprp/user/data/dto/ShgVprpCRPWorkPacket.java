package com.vprp.user.data.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.vprp.user.dto.PanchayatMasterResponse;
import com.vprp.user.entity.MemberProfiles;
import com.vprp.user.entity.MessageBundle;
import com.vprp.user.entity.PanchayatMaster;

public class ShgVprpCRPWorkPacket implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Long> assignedSHGs;
	private List<Long> otherRequiredSHGs;
	private List<ShgProfilesResponse> allSHGs;
	private List<MemberProfiles> allMembers;
	private List<PanchayatMasterResponse> gps;
	
	
	public ShgVprpCRPWorkPacket(List<Long> assignedSHGs, List<Long> otherRequiredSHGs,
			List<ShgProfilesResponse> allSHGs, List<MemberProfiles> allMembers) {
		super();
		this.assignedSHGs = assignedSHGs;
		this.otherRequiredSHGs = otherRequiredSHGs;
		this.allSHGs = allSHGs;
		this.allMembers = allMembers;
	}
	
	public ShgVprpCRPWorkPacket() {
		// TODO Auto-generated constructor stub
	}

	public List<Long> getAssignedSHGs() {
		return assignedSHGs;
	}
	public void setAssignedSHGs(List<Long> assignedSHGs) {
		this.assignedSHGs = assignedSHGs;
	}
	public List<Long> getOtherRequiredSHGs() {
		return otherRequiredSHGs;
	}
	public void setOtherRequiredSHGs(List<Long> otherRequiredSHGs) {
		this.otherRequiredSHGs = otherRequiredSHGs;
	}
	public List<ShgProfilesResponse> getAllSHGs() {
		return allSHGs;
	}
	public void setAllSHGs(List<ShgProfilesResponse> allSHGs) {
		this.allSHGs = allSHGs;
	}
	public List<MemberProfiles> getAllMembers() {
		return allMembers;
	}
	public void setAllMembers(List<MemberProfiles> allMembers) {
		this.allMembers = allMembers;
	}

	public List<PanchayatMasterResponse> getGps() {
		return gps;
	}

	public void setGps(List<PanchayatMasterResponse> gps) {
		this.gps = gps;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
