package com.wdk.general.core.storage.entity;

import java.io.Serializable;
import java.util.Date;

public class GeneralApi implements Serializable {
    private Integer id;

    private String path;

    private String args;

    private String vo;

    private String froms;

    private String wheres;

    private String orderbys;

    private String limits;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    private String updateSql;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args == null ? null : args.trim();
    }

    public String getVo() {
        return vo;
    }

    public void setVo(String vo) {
        this.vo = vo == null ? null : vo.trim();
    }

    public String getFroms() {
        return froms;
    }

    public void setFroms(String froms) {
        this.froms = froms == null ? null : froms.trim();
    }

    public String getWheres() {
        return wheres;
    }

    public void setWheres(String wheres) {
        this.wheres = wheres == null ? null : wheres.trim();
    }

    public String getOrderbys() {
        return orderbys;
    }

    public void setOrderbys(String orderbys) {
        this.orderbys = orderbys == null ? null : orderbys.trim();
    }

    public String getLimits() {
        return limits;
    }

    public void setLimits(String limits) {
        this.limits = limits == null ? null : limits.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateSql() {
        return this.updateSql;
    }

    public void setUpdateSql(String updateSql) {
        this.updateSql = updateSql;
    }
}