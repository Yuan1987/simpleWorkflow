package com.dynastech.model.entity;

import java.util.Date;

public class Apply {
	
    private String id;

    private String no;

    private String type;

    private String typeV;
    
    private String seq;
    
    private String seqV;

    private String grade;
    
    private String gradeV;

    private String userid;

    private String username;

    private String deptcode;

    private String deptname;

    private String status=ModelEnum.STATUS_0.getValue();

    private String flowstatus;

    private Date datetimecreated;

    private Date datetimemodified;

    private Boolean isdeleted=false;

    private String unqualifiedqualifications;

    private String abnormalityreasons;

    private String qualificationmatching;

    private String graduateschool;

    private String majors;

    private String educational;

    private String degree;

    private String experience;

    private String standardsmatching;

    private String jobstype;
    
    private String jobstypeV;

    private String jobsgrade;
    
    private String jobsgradeV;

    private String havequalificationseq;
    
    private String havequalificationseqV;

    private String havequalificationgrade;
    
    private String havequalificationgradeV;

    private String havequalificationgradeTime;

    private String workconditionsseq;
    
    private String workconditionsseqV;

    private String workconditionsgrade;
    
    private String workconditionsgradeV;

    private String workconditionsgradeAnnual;

    private String nuclearresultsfront;

    private String nuclearresultsfrontAnnual;

    private Boolean improvementplanclosedloop;

    private String processid;

    private Date submittime;

    /**
     * 任务ID
     */
    private String taskId;
    
    /**
     * 任务名称
     */
    private String taskName;
    
    /**
     * task key
     */
    private String taskKey;
    
    /**
     * 任务是否结束
     */
    private Boolean complete;
    
    /**
     * 任务完成时间
     * @return
     */
    private Date taskEndTime;
    
    public Date getTaskEndTime() {
		return taskEndTime;
	}
    
    public void setTaskEndTime(Date taskEndTime) {
		this.taskEndTime = taskEndTime;
	}
    
    public Boolean getComplete() {
		return complete;
	}
    
    public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeV() {
		return typeV;
	}

	public void setTypeV(String typeV) {
		this.typeV = typeV;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getSeqV() {
		return seqV;
	}

	public void setSeqV(String seqV) {
		this.seqV = seqV;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getGradeV() {
		return gradeV;
	}

	public void setGradeV(String gradeV) {
		this.gradeV = gradeV;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDeptcode() {
		return deptcode;
	}

	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFlowstatus() {
		return flowstatus;
	}

	public void setFlowstatus(String flowstatus) {
		this.flowstatus = flowstatus;
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

	public String getUnqualifiedqualifications() {
		return unqualifiedqualifications;
	}

	public void setUnqualifiedqualifications(String unqualifiedqualifications) {
		this.unqualifiedqualifications = unqualifiedqualifications;
	}

	public String getAbnormalityreasons() {
		return abnormalityreasons;
	}

	public void setAbnormalityreasons(String abnormalityreasons) {
		this.abnormalityreasons = abnormalityreasons;
	}

	public String getQualificationmatching() {
		return qualificationmatching;
	}

	public void setQualificationmatching(String qualificationmatching) {
		this.qualificationmatching = qualificationmatching;
	}

	public String getGraduateschool() {
		return graduateschool;
	}

	public void setGraduateschool(String graduateschool) {
		this.graduateschool = graduateschool;
	}

	public String getMajors() {
		return majors;
	}

	public void setMajors(String majors) {
		this.majors = majors;
	}

	public String getEducational() {
		return educational;
	}

	public void setEducational(String educational) {
		this.educational = educational;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getStandardsmatching() {
		return standardsmatching;
	}

	public void setStandardsmatching(String standardsmatching) {
		this.standardsmatching = standardsmatching;
	}

	public String getJobstype() {
		return jobstype;
	}

	public void setJobstype(String jobstype) {
		this.jobstype = jobstype;
	}

	public String getJobsgrade() {
		return jobsgrade;
	}

	public void setJobsgrade(String jobsgrade) {
		this.jobsgrade = jobsgrade;
	}

	public String getHavequalificationseq() {
		return havequalificationseq;
	}

	public void setHavequalificationseq(String havequalificationseq) {
		this.havequalificationseq = havequalificationseq;
	}

	public String getHavequalificationgrade() {
		return havequalificationgrade;
	}

	public void setHavequalificationgrade(String havequalificationgrade) {
		this.havequalificationgrade = havequalificationgrade;
	}

	public String getHavequalificationgradeTime() {
		return havequalificationgradeTime;
	}

	public void setHavequalificationgradeTime(String havequalificationgradeTime) {
		this.havequalificationgradeTime = havequalificationgradeTime;
	}

	public String getWorkconditionsseq() {
		return workconditionsseq;
	}

	public void setWorkconditionsseq(String workconditionsseq) {
		this.workconditionsseq = workconditionsseq;
	}

	public String getWorkconditionsgrade() {
		return workconditionsgrade;
	}

	public void setWorkconditionsgrade(String workconditionsgrade) {
		this.workconditionsgrade = workconditionsgrade;
	}

	public String getWorkconditionsgradeAnnual() {
		return workconditionsgradeAnnual;
	}

	public void setWorkconditionsgradeAnnual(String workconditionsgradeAnnual) {
		this.workconditionsgradeAnnual = workconditionsgradeAnnual;
	}

	public String getNuclearresultsfront() {
		return nuclearresultsfront;
	}

	public void setNuclearresultsfront(String nuclearresultsfront) {
		this.nuclearresultsfront = nuclearresultsfront;
	}

	public String getNuclearresultsfrontAnnual() {
		return nuclearresultsfrontAnnual;
	}

	public void setNuclearresultsfrontAnnual(String nuclearresultsfrontAnnual) {
		this.nuclearresultsfrontAnnual = nuclearresultsfrontAnnual;
	}

	public Boolean getImprovementplanclosedloop() {
		return improvementplanclosedloop;
	}

	public void setImprovementplanclosedloop(Boolean improvementplanclosedloop) {
		this.improvementplanclosedloop = improvementplanclosedloop;
	}

	public String getProcessid() {
		return processid;
	}

	public void setProcessid(String processid) {
		this.processid = processid;
	}

	public Date getSubmittime() {
		return submittime;
	}

	public void setSubmittime(Date submittime) {
		this.submittime = submittime;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public String getTaskKey() {
		return taskKey;
	}
	
	public void setTaskKey(String taskKey) {
		this.taskKey = taskKey;
	}
	

	public String getJobstypeV() {
		return jobstypeV;
	}

	public void setJobstypeV(String jobstypeV) {
		this.jobstypeV = jobstypeV;
	}

	public String getJobsgradeV() {
		return jobsgradeV;
	}

	public void setJobsgradeV(String jobsgradeV) {
		this.jobsgradeV = jobsgradeV;
	}

	public String getHavequalificationseqV() {
		return havequalificationseqV;
	}

	public void setHavequalificationseqV(String havequalificationseqV) {
		this.havequalificationseqV = havequalificationseqV;
	}

	public String getHavequalificationgradeV() {
		return havequalificationgradeV;
	}

	public void setHavequalificationgradeV(String havequalificationgradeV) {
		this.havequalificationgradeV = havequalificationgradeV;
	}

	public String getWorkconditionsseqV() {
		return workconditionsseqV;
	}

	public void setWorkconditionsseqV(String workconditionsseqV) {
		this.workconditionsseqV = workconditionsseqV;
	}

	public String getWorkconditionsgradeV() {
		return workconditionsgradeV;
	}

	public void setWorkconditionsgradeV(String workconditionsgradeV) {
		this.workconditionsgradeV = workconditionsgradeV;
	}

	@Override
	public String toString() {
		return "Apply [id=" + id + ", no=" + no + ", type=" + type + ", typeV=" + typeV + ", seq=" + seq + ", seqV="
				+ seqV + ", grade=" + grade + ", gradeV=" + gradeV + ", userid=" + userid + ", username=" + username
				+ ", deptcode=" + deptcode + ", deptname=" + deptname + ", status=" + status + ", flowstatus="
				+ flowstatus + ", datetimecreated=" + datetimecreated + ", datetimemodified=" + datetimemodified
				+ ", isdeleted=" + isdeleted + ", unqualifiedqualifications=" + unqualifiedqualifications
				+ ", abnormalityreasons=" + abnormalityreasons + ", qualificationmatching=" + qualificationmatching
				+ ", graduateschool=" + graduateschool + ", majors=" + majors + ", educational=" + educational
				+ ", degree=" + degree + ", experience=" + experience + ", standardsmatching=" + standardsmatching
				+ ", jobstype=" + jobstype + ", jobstypeV=" + jobstypeV + ", jobsgrade=" + jobsgrade + ", jobsgradeV="
				+ jobsgradeV + ", havequalificationseq=" + havequalificationseq + ", havequalificationseqV="
				+ havequalificationseqV + ", havequalificationgrade=" + havequalificationgrade
				+ ", havequalificationgradeV=" + havequalificationgradeV + ", havequalificationgradeTime="
				+ havequalificationgradeTime + ", workconditionsseq=" + workconditionsseq + ", workconditionsseqV="
				+ workconditionsseqV + ", workconditionsgrade=" + workconditionsgrade + ", workconditionsgradeV="
				+ workconditionsgradeV + ", workconditionsgradeAnnual=" + workconditionsgradeAnnual
				+ ", nuclearresultsfront=" + nuclearresultsfront + ", nuclearresultsfrontAnnual="
				+ nuclearresultsfrontAnnual + ", improvementplanclosedloop=" + improvementplanclosedloop
				+ ", processid=" + processid + ", submittime=" + submittime + ", taskId=" + taskId + ", taskName="
				+ taskName + ", taskKey=" + taskKey + ", complete=" + complete + ", taskEndTime=" + taskEndTime + "]";
	}

}