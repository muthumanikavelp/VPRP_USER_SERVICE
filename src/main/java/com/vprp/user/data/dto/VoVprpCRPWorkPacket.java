package com.vprp.user.data.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.vprp.user.dto.PanchayatMasterResponse;
import com.vprp.user.entity.FederationProfiles;
import com.vprp.user.entity.MessageBundle;
import com.vprp.user.entity.PanchayatMaster;

public class VoVprpCRPWorkPacket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<FederationProfiles> assignedVOs;
	private List<PanchayatMasterResponse> gps;

	public VoVprpCRPWorkPacket(List<Long> assignedVOs, List<Long> otherRequiredVOs, List<FederationProfiles> allVOs,
			List<PanchayatMasterResponse> gps) {
		super();
		this.gps = gps;
	}

	public VoVprpCRPWorkPacket() {
		// TODO Auto-generated constructor stub
	}

	public List<FederationProfiles> getAssignedVOs() {
		return assignedVOs;
	}

	public void setAssignedVOs(List<FederationProfiles> assignedVOs) {
		this.assignedVOs = assignedVOs;
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
