package com.vprp.user.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.vprp.user.entity.DistrictMaster;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BlockListDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private long blocktId;
	public long getBlocktId() {
		return blocktId;
	}

	public void setBlocktId(long blocktId) {
		this.blocktId = blocktId;
	}

	public String getBlocktName() {
		return blocktName;
	}

	public void setBlocktName(String blocktName) {
		this.blocktName = blocktName;
	}

	private String blocktName;
	
	public BlockListDto(long blockId, String blockName) {
		super();
		this.blocktId = blockId;
		this.blocktName = blockName;
		
	}
	
}