package com.vprp.user.data.dto;

import java.io.Serializable;
import java.util.List;

import com.vprp.user.model.CommonResponse;

public class UploadListResponse extends CommonResponse implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Long ShgId;
    private Long voId;
    private Long gpId;
    private String Status;
    private String Error;

    public UploadListResponse(Long shgId, String status, String error) {
        super();
        ShgId = shgId;
        Status = status;
        Error = error;
    }

    public UploadListResponse(Long shgId, Long voId, String status, String error) {
        super();
        ShgId = shgId;
        Status = status;
        Error = error;
        this.voId = voId;
    }

    public UploadListResponse(Long shgId, Long voId, Long gpId, String status, String error) {
        ShgId = shgId;
        this.voId = voId;
        this.gpId = gpId;
        Status = status;
        Error = error;
    }

    public UploadListResponse() {
        // TODO Auto-generated constructor stub
    }

    public Long getGpId() {
        return gpId;
    }

    public void setGpId(Long gpId) {
        this.gpId = gpId;
    }

    public Long getShgId() {
        return ShgId;
    }

    public void setShgId(Long shgId) {
        ShgId = shgId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getVoId() {
        return voId;
    }

    public void setVoId(Long voId) {
        this.voId = voId;
    }
}
