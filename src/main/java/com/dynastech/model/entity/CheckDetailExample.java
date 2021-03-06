package com.dynastech.model.entity;

import java.util.ArrayList;
import java.util.List;

public class CheckDetailExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table T_Apply_CheckDetail
	 * @mbggenerated  Wed Nov 29 09:19:21 CST 2017
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table T_Apply_CheckDetail
	 * @mbggenerated  Wed Nov 29 09:19:21 CST 2017
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table T_Apply_CheckDetail
	 * @mbggenerated  Wed Nov 29 09:19:21 CST 2017
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table T_Apply_CheckDetail
	 * @mbggenerated  Wed Nov 29 09:19:21 CST 2017
	 */
	public CheckDetailExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table T_Apply_CheckDetail
	 * @mbggenerated  Wed Nov 29 09:19:21 CST 2017
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table T_Apply_CheckDetail
	 * @mbggenerated  Wed Nov 29 09:19:21 CST 2017
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table T_Apply_CheckDetail
	 * @mbggenerated  Wed Nov 29 09:19:21 CST 2017
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table T_Apply_CheckDetail
	 * @mbggenerated  Wed Nov 29 09:19:21 CST 2017
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table T_Apply_CheckDetail
	 * @mbggenerated  Wed Nov 29 09:19:21 CST 2017
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table T_Apply_CheckDetail
	 * @mbggenerated  Wed Nov 29 09:19:21 CST 2017
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table T_Apply_CheckDetail
	 * @mbggenerated  Wed Nov 29 09:19:21 CST 2017
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table T_Apply_CheckDetail
	 * @mbggenerated  Wed Nov 29 09:19:21 CST 2017
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table T_Apply_CheckDetail
	 * @mbggenerated  Wed Nov 29 09:19:21 CST 2017
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table T_Apply_CheckDetail
	 * @mbggenerated  Wed Nov 29 09:19:21 CST 2017
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table T_Apply_CheckDetail
	 * @mbggenerated  Wed Nov 29 09:19:21 CST 2017
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andIdIsNull() {
			addCriterion("ace.id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("ace.id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(String value) {
			addCriterion("ace.id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(String value) {
			addCriterion("ace.id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(String value) {
			addCriterion("ace.id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(String value) {
			addCriterion("ace.id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(String value) {
			addCriterion("ace.id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(String value) {
			addCriterion("ace.id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLike(String value) {
			addCriterion("ace.id like", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotLike(String value) {
			addCriterion("ace.id not like", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<String> values) {
			addCriterion("ace.id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<String> values) {
			addCriterion("ace.id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(String value1, String value2) {
			addCriterion("ace.id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(String value1, String value2) {
			addCriterion("ace.id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andApplyidIsNull() {
			addCriterion("ace.applyId is null");
			return (Criteria) this;
		}

		public Criteria andApplyidIsNotNull() {
			addCriterion("ace.applyId is not null");
			return (Criteria) this;
		}

		public Criteria andApplyidEqualTo(String value) {
			addCriterion("ace.applyId =", value, "applyid");
			return (Criteria) this;
		}

		public Criteria andApplyidNotEqualTo(String value) {
			addCriterion("ace.applyId <>", value, "applyid");
			return (Criteria) this;
		}

		public Criteria andApplyidGreaterThan(String value) {
			addCriterion("ace.applyId >", value, "applyid");
			return (Criteria) this;
		}

		public Criteria andApplyidGreaterThanOrEqualTo(String value) {
			addCriterion("ace.applyId >=", value, "applyid");
			return (Criteria) this;
		}

		public Criteria andApplyidLessThan(String value) {
			addCriterion("ace.applyId <", value, "applyid");
			return (Criteria) this;
		}

		public Criteria andApplyidLessThanOrEqualTo(String value) {
			addCriterion("ace.applyId <=", value, "applyid");
			return (Criteria) this;
		}

		public Criteria andApplyidLike(String value) {
			addCriterion("ace.applyId like", value, "applyid");
			return (Criteria) this;
		}

		public Criteria andApplyidNotLike(String value) {
			addCriterion("ace.applyId not like", value, "applyid");
			return (Criteria) this;
		}

		public Criteria andApplyidIn(List<String> values) {
			addCriterion("ace.applyId in", values, "applyid");
			return (Criteria) this;
		}

		public Criteria andApplyidNotIn(List<String> values) {
			addCriterion("ace.applyId not in", values, "applyid");
			return (Criteria) this;
		}

		public Criteria andApplyidBetween(String value1, String value2) {
			addCriterion("ace.applyId between", value1, value2, "applyid");
			return (Criteria) this;
		}

		public Criteria andApplyidNotBetween(String value1, String value2) {
			addCriterion("ace.applyId not between", value1, value2, "applyid");
			return (Criteria) this;
		}

		public Criteria andNodenameIsNull() {
			addCriterion("ace.nodeName is null");
			return (Criteria) this;
		}

		public Criteria andNodenameIsNotNull() {
			addCriterion("ace.nodeName is not null");
			return (Criteria) this;
		}

		public Criteria andNodenameEqualTo(String value) {
			addCriterion("ace.nodeName =", value, "nodename");
			return (Criteria) this;
		}

		public Criteria andNodenameNotEqualTo(String value) {
			addCriterion("ace.nodeName <>", value, "nodename");
			return (Criteria) this;
		}

		public Criteria andNodenameGreaterThan(String value) {
			addCriterion("ace.nodeName >", value, "nodename");
			return (Criteria) this;
		}

		public Criteria andNodenameGreaterThanOrEqualTo(String value) {
			addCriterion("ace.nodeName >=", value, "nodename");
			return (Criteria) this;
		}

		public Criteria andNodenameLessThan(String value) {
			addCriterion("ace.nodeName <", value, "nodename");
			return (Criteria) this;
		}

		public Criteria andNodenameLessThanOrEqualTo(String value) {
			addCriterion("ace.nodeName <=", value, "nodename");
			return (Criteria) this;
		}

		public Criteria andNodenameLike(String value) {
			addCriterion("ace.nodeName like", value, "nodename");
			return (Criteria) this;
		}

		public Criteria andNodenameNotLike(String value) {
			addCriterion("ace.nodeName not like", value, "nodename");
			return (Criteria) this;
		}

		public Criteria andNodenameIn(List<String> values) {
			addCriterion("ace.nodeName in", values, "nodename");
			return (Criteria) this;
		}

		public Criteria andNodenameNotIn(List<String> values) {
			addCriterion("ace.nodeName not in", values, "nodename");
			return (Criteria) this;
		}

		public Criteria andNodenameBetween(String value1, String value2) {
			addCriterion("ace.nodeName between", value1, value2, "nodename");
			return (Criteria) this;
		}

		public Criteria andNodenameNotBetween(String value1, String value2) {
			addCriterion("ace.nodeName not between", value1, value2, "nodename");
			return (Criteria) this;
		}

		public Criteria andResultIsNull() {
			addCriterion("ace.result is null");
			return (Criteria) this;
		}

		public Criteria andResultIsNotNull() {
			addCriterion("ace.result is not null");
			return (Criteria) this;
		}

		public Criteria andResultEqualTo(Boolean value) {
			addCriterion("ace.result =", value, "result");
			return (Criteria) this;
		}

		public Criteria andResultNotEqualTo(Boolean value) {
			addCriterion("ace.result <>", value, "result");
			return (Criteria) this;
		}

		public Criteria andResultGreaterThan(Boolean value) {
			addCriterion("ace.result >", value, "result");
			return (Criteria) this;
		}

		public Criteria andResultGreaterThanOrEqualTo(Boolean value) {
			addCriterion("ace.result >=", value, "result");
			return (Criteria) this;
		}

		public Criteria andResultLessThan(Boolean value) {
			addCriterion("ace.result <", value, "result");
			return (Criteria) this;
		}

		public Criteria andResultLessThanOrEqualTo(Boolean value) {
			addCriterion("ace.result <=", value, "result");
			return (Criteria) this;
		}

		public Criteria andResultIn(List<Boolean> values) {
			addCriterion("ace.result in", values, "result");
			return (Criteria) this;
		}

		public Criteria andResultNotIn(List<Boolean> values) {
			addCriterion("ace.result not in", values, "result");
			return (Criteria) this;
		}

		public Criteria andResultBetween(Boolean value1, Boolean value2) {
			addCriterion("ace.result between", value1, value2, "result");
			return (Criteria) this;
		}

		public Criteria andResultNotBetween(Boolean value1, Boolean value2) {
			addCriterion("ace.result not between", value1, value2, "result");
			return (Criteria) this;
		}

		public Criteria andOpinionIsNull() {
			addCriterion("ace.opinion is null");
			return (Criteria) this;
		}

		public Criteria andOpinionIsNotNull() {
			addCriterion("ace.opinion is not null");
			return (Criteria) this;
		}

		public Criteria andOpinionEqualTo(String value) {
			addCriterion("ace.opinion =", value, "opinion");
			return (Criteria) this;
		}

		public Criteria andOpinionNotEqualTo(String value) {
			addCriterion("ace.opinion <>", value, "opinion");
			return (Criteria) this;
		}

		public Criteria andOpinionGreaterThan(String value) {
			addCriterion("ace.opinion >", value, "opinion");
			return (Criteria) this;
		}

		public Criteria andOpinionGreaterThanOrEqualTo(String value) {
			addCriterion("ace.opinion >=", value, "opinion");
			return (Criteria) this;
		}

		public Criteria andOpinionLessThan(String value) {
			addCriterion("ace.opinion <", value, "opinion");
			return (Criteria) this;
		}

		public Criteria andOpinionLessThanOrEqualTo(String value) {
			addCriterion("ace.opinion <=", value, "opinion");
			return (Criteria) this;
		}

		public Criteria andOpinionLike(String value) {
			addCriterion("ace.opinion like", value, "opinion");
			return (Criteria) this;
		}

		public Criteria andOpinionNotLike(String value) {
			addCriterion("ace.opinion not like", value, "opinion");
			return (Criteria) this;
		}

		public Criteria andOpinionIn(List<String> values) {
			addCriterion("ace.opinion in", values, "opinion");
			return (Criteria) this;
		}

		public Criteria andOpinionNotIn(List<String> values) {
			addCriterion("ace.opinion not in", values, "opinion");
			return (Criteria) this;
		}

		public Criteria andOpinionBetween(String value1, String value2) {
			addCriterion("ace.opinion between", value1, value2, "opinion");
			return (Criteria) this;
		}

		public Criteria andOpinionNotBetween(String value1, String value2) {
			addCriterion("ace.opinion not between", value1, value2, "opinion");
			return (Criteria) this;
		}

		public Criteria andCreatedtimeIsNull() {
			addCriterion("ace.createdTime is null");
			return (Criteria) this;
		}

		public Criteria andCreatedtimeIsNotNull() {
			addCriterion("ace.createdTime is not null");
			return (Criteria) this;
		}

		public Criteria andCreatedtimeEqualTo(String value) {
			addCriterion("ace.createdTime =", value, "createdtime");
			return (Criteria) this;
		}

		public Criteria andCreatedtimeNotEqualTo(String value) {
			addCriterion("ace.createdTime <>", value, "createdtime");
			return (Criteria) this;
		}

		public Criteria andCreatedtimeGreaterThan(String value) {
			addCriterion("ace.createdTime >", value, "createdtime");
			return (Criteria) this;
		}

		public Criteria andCreatedtimeGreaterThanOrEqualTo(String value) {
			addCriterion("ace.createdTime >=", value, "createdtime");
			return (Criteria) this;
		}

		public Criteria andCreatedtimeLessThan(String value) {
			addCriterion("ace.createdTime <", value, "createdtime");
			return (Criteria) this;
		}

		public Criteria andCreatedtimeLessThanOrEqualTo(String value) {
			addCriterion("ace.createdTime <=", value, "createdtime");
			return (Criteria) this;
		}

		public Criteria andCreatedtimeLike(String value) {
			addCriterion("ace.createdTime like", value, "createdtime");
			return (Criteria) this;
		}

		public Criteria andCreatedtimeNotLike(String value) {
			addCriterion("ace.createdTime not like", value, "createdtime");
			return (Criteria) this;
		}

		public Criteria andCreatedtimeIn(List<String> values) {
			addCriterion("ace.createdTime in", values, "createdtime");
			return (Criteria) this;
		}

		public Criteria andCreatedtimeNotIn(List<String> values) {
			addCriterion("ace.createdTime not in", values, "createdtime");
			return (Criteria) this;
		}

		public Criteria andCreatedtimeBetween(String value1, String value2) {
			addCriterion("ace.createdTime between", value1, value2, "createdtime");
			return (Criteria) this;
		}

		public Criteria andCreatedtimeNotBetween(String value1, String value2) {
			addCriterion("ace.createdTime not between", value1, value2, "createdtime");
			return (Criteria) this;
		}

		public Criteria andUseridIsNull() {
			addCriterion("ace.userId is null");
			return (Criteria) this;
		}

		public Criteria andUseridIsNotNull() {
			addCriterion("ace.userId is not null");
			return (Criteria) this;
		}

		public Criteria andUseridEqualTo(String value) {
			addCriterion("ace.userId =", value, "userid");
			return (Criteria) this;
		}

		public Criteria andUseridNotEqualTo(String value) {
			addCriterion("ace.userId <>", value, "userid");
			return (Criteria) this;
		}

		public Criteria andUseridGreaterThan(String value) {
			addCriterion("ace.userId >", value, "userid");
			return (Criteria) this;
		}

		public Criteria andUseridGreaterThanOrEqualTo(String value) {
			addCriterion("ace.userId >=", value, "userid");
			return (Criteria) this;
		}

		public Criteria andUseridLessThan(String value) {
			addCriterion("ace.userId <", value, "userid");
			return (Criteria) this;
		}

		public Criteria andUseridLessThanOrEqualTo(String value) {
			addCriterion("ace.userId <=", value, "userid");
			return (Criteria) this;
		}

		public Criteria andUseridLike(String value) {
			addCriterion("ace.userId like", value, "userid");
			return (Criteria) this;
		}

		public Criteria andUseridNotLike(String value) {
			addCriterion("ace.userId not like", value, "userid");
			return (Criteria) this;
		}

		public Criteria andUseridIn(List<String> values) {
			addCriterion("ace.userId in", values, "userid");
			return (Criteria) this;
		}

		public Criteria andUseridNotIn(List<String> values) {
			addCriterion("ace.userId not in", values, "userid");
			return (Criteria) this;
		}

		public Criteria andUseridBetween(String value1, String value2) {
			addCriterion("ace.userId between", value1, value2, "userid");
			return (Criteria) this;
		}

		public Criteria andUseridNotBetween(String value1, String value2) {
			addCriterion("ace.userId not between", value1, value2, "userid");
			return (Criteria) this;
		}

		public Criteria andUsernameIsNull() {
			addCriterion("ace.userName is null");
			return (Criteria) this;
		}

		public Criteria andUsernameIsNotNull() {
			addCriterion("ace.userName is not null");
			return (Criteria) this;
		}

		public Criteria andUsernameEqualTo(String value) {
			addCriterion("ace.userName =", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameNotEqualTo(String value) {
			addCriterion("ace.userName <>", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameGreaterThan(String value) {
			addCriterion("ace.userName >", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameGreaterThanOrEqualTo(String value) {
			addCriterion("ace.userName >=", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameLessThan(String value) {
			addCriterion("ace.userName <", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameLessThanOrEqualTo(String value) {
			addCriterion("ace.userName <=", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameLike(String value) {
			addCriterion("ace.userName like", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameNotLike(String value) {
			addCriterion("ace.userName not like", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameIn(List<String> values) {
			addCriterion("ace.userName in", values, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameNotIn(List<String> values) {
			addCriterion("ace.userName not in", values, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameBetween(String value1, String value2) {
			addCriterion("ace.userName between", value1, value2, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameNotBetween(String value1, String value2) {
			addCriterion("ace.userName not between", value1, value2, "username");
			return (Criteria) this;
		}

		public Criteria andTaskidIsNull() {
			addCriterion("ace.taskId is null");
			return (Criteria) this;
		}

		public Criteria andTaskidIsNotNull() {
			addCriterion("ace.taskId is not null");
			return (Criteria) this;
		}

		public Criteria andTaskidEqualTo(String value) {
			addCriterion("ace.taskId =", value, "taskid");
			return (Criteria) this;
		}

		public Criteria andTaskidNotEqualTo(String value) {
			addCriterion("ace.taskId <>", value, "taskid");
			return (Criteria) this;
		}

		public Criteria andTaskidGreaterThan(String value) {
			addCriterion("ace.taskId >", value, "taskid");
			return (Criteria) this;
		}

		public Criteria andTaskidGreaterThanOrEqualTo(String value) {
			addCriterion("ace.taskId >=", value, "taskid");
			return (Criteria) this;
		}

		public Criteria andTaskidLessThan(String value) {
			addCriterion("ace.taskId <", value, "taskid");
			return (Criteria) this;
		}

		public Criteria andTaskidLessThanOrEqualTo(String value) {
			addCriterion("ace.taskId <=", value, "taskid");
			return (Criteria) this;
		}

		public Criteria andTaskidLike(String value) {
			addCriterion("ace.taskId like", value, "taskid");
			return (Criteria) this;
		}

		public Criteria andTaskidNotLike(String value) {
			addCriterion("ace.taskId not like", value, "taskid");
			return (Criteria) this;
		}

		public Criteria andTaskidIn(List<String> values) {
			addCriterion("ace.taskId in", values, "taskid");
			return (Criteria) this;
		}

		public Criteria andTaskidNotIn(List<String> values) {
			addCriterion("ace.taskId not in", values, "taskid");
			return (Criteria) this;
		}

		public Criteria andTaskidBetween(String value1, String value2) {
			addCriterion("ace.taskId between", value1, value2, "taskid");
			return (Criteria) this;
		}

		public Criteria andTaskidNotBetween(String value1, String value2) {
			addCriterion("ace.taskId not between", value1, value2, "taskid");
			return (Criteria) this;
		}

		public Criteria andQuestion1IsNull() {
			addCriterion("ace.question1 is null");
			return (Criteria) this;
		}

		public Criteria andQuestion1IsNotNull() {
			addCriterion("ace.question1 is not null");
			return (Criteria) this;
		}

		public Criteria andQuestion1EqualTo(String value) {
			addCriterion("ace.question1 =", value, "question1");
			return (Criteria) this;
		}

		public Criteria andQuestion1NotEqualTo(String value) {
			addCriterion("ace.question1 <>", value, "question1");
			return (Criteria) this;
		}

		public Criteria andQuestion1GreaterThan(String value) {
			addCriterion("ace.question1 >", value, "question1");
			return (Criteria) this;
		}

		public Criteria andQuestion1GreaterThanOrEqualTo(String value) {
			addCriterion("ace.question1 >=", value, "question1");
			return (Criteria) this;
		}

		public Criteria andQuestion1LessThan(String value) {
			addCriterion("ace.question1 <", value, "question1");
			return (Criteria) this;
		}

		public Criteria andQuestion1LessThanOrEqualTo(String value) {
			addCriterion("ace.question1 <=", value, "question1");
			return (Criteria) this;
		}

		public Criteria andQuestion1Like(String value) {
			addCriterion("ace.question1 like", value, "question1");
			return (Criteria) this;
		}

		public Criteria andQuestion1NotLike(String value) {
			addCriterion("ace.question1 not like", value, "question1");
			return (Criteria) this;
		}

		public Criteria andQuestion1In(List<String> values) {
			addCriterion("ace.question1 in", values, "question1");
			return (Criteria) this;
		}

		public Criteria andQuestion1NotIn(List<String> values) {
			addCriterion("ace.question1 not in", values, "question1");
			return (Criteria) this;
		}

		public Criteria andQuestion1Between(String value1, String value2) {
			addCriterion("ace.question1 between", value1, value2, "question1");
			return (Criteria) this;
		}

		public Criteria andQuestion1NotBetween(String value1, String value2) {
			addCriterion("ace.question1 not between", value1, value2, "question1");
			return (Criteria) this;
		}

		public Criteria andQuestion2IsNull() {
			addCriterion("ace.question2 is null");
			return (Criteria) this;
		}

		public Criteria andQuestion2IsNotNull() {
			addCriterion("ace.question2 is not null");
			return (Criteria) this;
		}

		public Criteria andQuestion2EqualTo(String value) {
			addCriterion("ace.question2 =", value, "question2");
			return (Criteria) this;
		}

		public Criteria andQuestion2NotEqualTo(String value) {
			addCriterion("ace.question2 <>", value, "question2");
			return (Criteria) this;
		}

		public Criteria andQuestion2GreaterThan(String value) {
			addCriterion("ace.question2 >", value, "question2");
			return (Criteria) this;
		}

		public Criteria andQuestion2GreaterThanOrEqualTo(String value) {
			addCriterion("ace.question2 >=", value, "question2");
			return (Criteria) this;
		}

		public Criteria andQuestion2LessThan(String value) {
			addCriterion("ace.question2 <", value, "question2");
			return (Criteria) this;
		}

		public Criteria andQuestion2LessThanOrEqualTo(String value) {
			addCriterion("ace.question2 <=", value, "question2");
			return (Criteria) this;
		}

		public Criteria andQuestion2Like(String value) {
			addCriterion("ace.question2 like", value, "question2");
			return (Criteria) this;
		}

		public Criteria andQuestion2NotLike(String value) {
			addCriterion("ace.question2 not like", value, "question2");
			return (Criteria) this;
		}

		public Criteria andQuestion2In(List<String> values) {
			addCriterion("ace.question2 in", values, "question2");
			return (Criteria) this;
		}

		public Criteria andQuestion2NotIn(List<String> values) {
			addCriterion("ace.question2 not in", values, "question2");
			return (Criteria) this;
		}

		public Criteria andQuestion2Between(String value1, String value2) {
			addCriterion("ace.question2 between", value1, value2, "question2");
			return (Criteria) this;
		}

		public Criteria andQuestion2NotBetween(String value1, String value2) {
			addCriterion("ace.question2 not between", value1, value2, "question2");
			return (Criteria) this;
		}

		public Criteria andQuestion3IsNull() {
			addCriterion("ace.question3 is null");
			return (Criteria) this;
		}

		public Criteria andQuestion3IsNotNull() {
			addCriterion("ace.question3 is not null");
			return (Criteria) this;
		}

		public Criteria andQuestion3EqualTo(String value) {
			addCriterion("ace.question3 =", value, "question3");
			return (Criteria) this;
		}

		public Criteria andQuestion3NotEqualTo(String value) {
			addCriterion("ace.question3 <>", value, "question3");
			return (Criteria) this;
		}

		public Criteria andQuestion3GreaterThan(String value) {
			addCriterion("ace.question3 >", value, "question3");
			return (Criteria) this;
		}

		public Criteria andQuestion3GreaterThanOrEqualTo(String value) {
			addCriterion("ace.question3 >=", value, "question3");
			return (Criteria) this;
		}

		public Criteria andQuestion3LessThan(String value) {
			addCriterion("ace.question3 <", value, "question3");
			return (Criteria) this;
		}

		public Criteria andQuestion3LessThanOrEqualTo(String value) {
			addCriterion("ace.question3 <=", value, "question3");
			return (Criteria) this;
		}

		public Criteria andQuestion3Like(String value) {
			addCriterion("ace.question3 like", value, "question3");
			return (Criteria) this;
		}

		public Criteria andQuestion3NotLike(String value) {
			addCriterion("ace.question3 not like", value, "question3");
			return (Criteria) this;
		}

		public Criteria andQuestion3In(List<String> values) {
			addCriterion("ace.question3 in", values, "question3");
			return (Criteria) this;
		}

		public Criteria andQuestion3NotIn(List<String> values) {
			addCriterion("ace.question3 not in", values, "question3");
			return (Criteria) this;
		}

		public Criteria andQuestion3Between(String value1, String value2) {
			addCriterion("ace.question3 between", value1, value2, "question3");
			return (Criteria) this;
		}

		public Criteria andQuestion3NotBetween(String value1, String value2) {
			addCriterion("ace.question3 not between", value1, value2, "question3");
			return (Criteria) this;
		}

		public Criteria andNodecodeIsNull() {
			addCriterion("ace.nodeCode is null");
			return (Criteria) this;
		}

		public Criteria andNodecodeIsNotNull() {
			addCriterion("ace.nodeCode is not null");
			return (Criteria) this;
		}

		public Criteria andNodecodeEqualTo(String value) {
			addCriterion("ace.nodeCode =", value, "nodecode");
			return (Criteria) this;
		}

		public Criteria andNodecodeNotEqualTo(String value) {
			addCriterion("ace.nodeCode <>", value, "nodecode");
			return (Criteria) this;
		}

		public Criteria andNodecodeGreaterThan(String value) {
			addCriterion("ace.nodeCode >", value, "nodecode");
			return (Criteria) this;
		}

		public Criteria andNodecodeGreaterThanOrEqualTo(String value) {
			addCriterion("ace.nodeCode >=", value, "nodecode");
			return (Criteria) this;
		}

		public Criteria andNodecodeLessThan(String value) {
			addCriterion("ace.nodeCode <", value, "nodecode");
			return (Criteria) this;
		}

		public Criteria andNodecodeLessThanOrEqualTo(String value) {
			addCriterion("ace.nodeCode <=", value, "nodecode");
			return (Criteria) this;
		}

		public Criteria andNodecodeLike(String value) {
			addCriterion("ace.nodeCode like", value, "nodecode");
			return (Criteria) this;
		}

		public Criteria andNodecodeNotLike(String value) {
			addCriterion("ace.nodeCode not like", value, "nodecode");
			return (Criteria) this;
		}

		public Criteria andNodecodeIn(List<String> values) {
			addCriterion("ace.nodeCode in", values, "nodecode");
			return (Criteria) this;
		}

		public Criteria andNodecodeNotIn(List<String> values) {
			addCriterion("ace.nodeCode not in", values, "nodecode");
			return (Criteria) this;
		}

		public Criteria andNodecodeBetween(String value1, String value2) {
			addCriterion("ace.nodeCode between", value1, value2, "nodecode");
			return (Criteria) this;
		}

		public Criteria andNodecodeNotBetween(String value1, String value2) {
			addCriterion("ace.nodeCode not between", value1, value2, "nodecode");
			return (Criteria) this;
		}

		public Criteria andIsbackIsNull() {
			addCriterion("ace.isback is null");
			return (Criteria) this;
		}

		public Criteria andIsbackIsNotNull() {
			addCriterion("ace.isback is not null");
			return (Criteria) this;
		}

		public Criteria andIsbackEqualTo(Boolean value) {
			addCriterion("ace.isback =", value, "isback");
			return (Criteria) this;
		}

		public Criteria andIsbackNotEqualTo(Boolean value) {
			addCriterion("ace.isback <>", value, "isback");
			return (Criteria) this;
		}

		public Criteria andIsbackGreaterThan(Boolean value) {
			addCriterion("ace.isback >", value, "isback");
			return (Criteria) this;
		}

		public Criteria andIsbackGreaterThanOrEqualTo(Boolean value) {
			addCriterion("ace.isback >=", value, "isback");
			return (Criteria) this;
		}

		public Criteria andIsbackLessThan(Boolean value) {
			addCriterion("ace.isback <", value, "isback");
			return (Criteria) this;
		}

		public Criteria andIsbackLessThanOrEqualTo(Boolean value) {
			addCriterion("ace.isback <=", value, "isback");
			return (Criteria) this;
		}

		public Criteria andIsbackIn(List<Boolean> values) {
			addCriterion("ace.isback in", values, "isback");
			return (Criteria) this;
		}

		public Criteria andIsbackNotIn(List<Boolean> values) {
			addCriterion("ace.isback not in", values, "isback");
			return (Criteria) this;
		}

		public Criteria andIsbackBetween(Boolean value1, Boolean value2) {
			addCriterion("ace.isback between", value1, value2, "isback");
			return (Criteria) this;
		}

		public Criteria andIsbackNotBetween(Boolean value1, Boolean value2) {
			addCriterion("ace.isback not between", value1, value2, "isback");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table T_Apply_CheckDetail
	 * @mbggenerated  Wed Nov 29 09:19:21 CST 2017
	 */
	public static class Criterion {
		private String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table T_Apply_CheckDetail
     *
     * @mbggenerated do_not_delete_during_merge Tue Oct 24 15:05:30 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}