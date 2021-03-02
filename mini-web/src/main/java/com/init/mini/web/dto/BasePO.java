package com.init.mini.web.dto;

import java.io.Serializable;

public class BasePO implements Serializable {

    private String deptCOde;

    private String deptLevel;

    public String getDeptCOde() {
        return deptCOde;
    }

    public void setDeptCOde(String deptCOde) {
        this.deptCOde = deptCOde;
    }

    public String getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(String deptLevel) {
        this.deptLevel = deptLevel;
    }
}
