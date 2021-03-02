package com.init.mini.common.entity;

public class RefreshTable extends BaseEntity {

    private Integer idRefreshTable;

    private String  name;

    private String serviceName;

    private String status;

    public Integer getIdRefreshTable() {
        return idRefreshTable;
    }

    public void setIdRefreshTable(Integer idRefreshTable) {
        this.idRefreshTable = idRefreshTable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }
}
