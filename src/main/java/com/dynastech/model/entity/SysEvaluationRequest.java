package com.dynastech.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ganlu on 2017/9/19.
 */


public class SysEvaluationRequest   {
    private String id;
    private String name;
    private String version;
    private String serial;
    private String level;
    private Integer judgesnumber;
    private double passmark;
    private String standardfile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getJudgesnumber() {
        return judgesnumber;
    }

    public void setJudgesnumber(Integer judgesnumber) {
        this.judgesnumber = judgesnumber;
    }

    public double getPassmark() {
        return passmark;
    }

    public void setPassmark(double passmark) {
        this.passmark = passmark;
    }

    public String getStandardfile() {
        return standardfile;
    }

    public void setStandardfile(String standardfile) {
        this.standardfile = standardfile;
    }
}


