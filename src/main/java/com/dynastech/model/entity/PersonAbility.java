package com.dynastech.model.entity;

import java.util.Date;
import java.util.List;

public class PersonAbility {
	
    private String id;

    private String userid;

    private String sysAbilityid;

    private String name;

    private String typeid;
    
    private String typeV;

    private String serial;
    
    private String seqV;

    private String thelevel;
    
    private String gradeV;

    public String getSeqV() {
		return seqV;
	}

	public void setSeqV(String seqV) {
		this.seqV = seqV;
	}

	public String getGradeV() {
		return gradeV;
	}

	public void setGradeV(String gradeV) {
		this.gradeV = gradeV;
	}

	private String evidence;

    private String description;

    private Double score;

    private String formula;

    private Double scoring;

    private Double passmark;

    private Date datetimecreated;

    private Date datetimemodified;

    private Boolean isdeleted=false;
    
    /**
     * 附件信息
     */
    private List<AttachmentFile> files;
    
    /**
     * 能力项测评信息
     */
    private List<PersonAbilityEvaluate> paes;
    
    public List<PersonAbilityEvaluate> getPaes() {
		return paes;
	}
    
    public void setPaes(List<PersonAbilityEvaluate> paes) {
		this.paes = paes;
	}
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSysAbilityid() {
		return sysAbilityid;
	}

	public void setSysAbilityid(String sysAbilityid) {
		this.sysAbilityid = sysAbilityid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getTypeV() {
		return typeV;
	}

	public void setTypeV(String typeV) {
		this.typeV = typeV;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getThelevel() {
		return thelevel;
	}

	public void setThelevel(String thelevel) {
		this.thelevel = thelevel;
	}

	public String getEvidence() {
		return evidence;
	}

	public void setEvidence(String evidence) {
		this.evidence = evidence;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public Double getScoring() {
		return scoring;
	}

	public void setScoring(Double scoring) {
		this.scoring = scoring;
	}

	public Double getPassmark() {
		return passmark;
	}

	public void setPassmark(Double passmark) {
		this.passmark = passmark;
	}

	public Date getDatetimecreated() {
		return datetimecreated;
	}

	public void setDatetimecreated(Date datetimecreated) {
		this.datetimecreated = datetimecreated;
	}

	public Date getDatetimemodified() {
		return datetimemodified;
	}

	public void setDatetimemodified(Date datetimemodified) {
		this.datetimemodified = datetimemodified;
	}

	public Boolean getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public List<AttachmentFile> getFiles() {
		return files;
	}
	
	public void setFiles(List<AttachmentFile> files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "PersonAbility [id=" + id + ", userid=" + userid + ", name=" + name + "]";
	}
    
    
}