package com.dynastech.flow.entity;

import java.util.ArrayList;
import java.util.List;

public class IdentitylinkExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table F_identitylink
	 * @mbggenerated  Tue Dec 05 15:11:47 CST 2017
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table F_identitylink
	 * @mbggenerated  Tue Dec 05 15:11:47 CST 2017
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table F_identitylink
	 * @mbggenerated  Tue Dec 05 15:11:47 CST 2017
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table F_identitylink
	 * @mbggenerated  Tue Dec 05 15:11:47 CST 2017
	 */
	public IdentitylinkExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table F_identitylink
	 * @mbggenerated  Tue Dec 05 15:11:47 CST 2017
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table F_identitylink
	 * @mbggenerated  Tue Dec 05 15:11:47 CST 2017
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table F_identitylink
	 * @mbggenerated  Tue Dec 05 15:11:47 CST 2017
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table F_identitylink
	 * @mbggenerated  Tue Dec 05 15:11:47 CST 2017
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table F_identitylink
	 * @mbggenerated  Tue Dec 05 15:11:47 CST 2017
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table F_identitylink
	 * @mbggenerated  Tue Dec 05 15:11:47 CST 2017
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table F_identitylink
	 * @mbggenerated  Tue Dec 05 15:11:47 CST 2017
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table F_identitylink
	 * @mbggenerated  Tue Dec 05 15:11:47 CST 2017
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table F_identitylink
	 * @mbggenerated  Tue Dec 05 15:11:47 CST 2017
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table F_identitylink
	 * @mbggenerated  Tue Dec 05 15:11:47 CST 2017
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table F_identitylink
	 * @mbggenerated  Tue Dec 05 15:11:47 CST 2017
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
			addCriterion("il.ID is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("il.ID is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(String value) {
			addCriterion("il.ID =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(String value) {
			addCriterion("il.ID <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(String value) {
			addCriterion("il.ID >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(String value) {
			addCriterion("il.ID >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(String value) {
			addCriterion("il.ID <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(String value) {
			addCriterion("il.ID <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLike(String value) {
			addCriterion("il.ID like", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotLike(String value) {
			addCriterion("il.ID not like", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<String> values) {
			addCriterion("il.ID in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<String> values) {
			addCriterion("il.ID not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(String value1, String value2) {
			addCriterion("il.ID between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(String value1, String value2) {
			addCriterion("il.ID not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andRoleIdIsNull() {
			addCriterion("il.ROLE_ID is null");
			return (Criteria) this;
		}

		public Criteria andRoleIdIsNotNull() {
			addCriterion("il.ROLE_ID is not null");
			return (Criteria) this;
		}

		public Criteria andRoleIdEqualTo(String value) {
			addCriterion("il.ROLE_ID =", value, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdNotEqualTo(String value) {
			addCriterion("il.ROLE_ID <>", value, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdGreaterThan(String value) {
			addCriterion("il.ROLE_ID >", value, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdGreaterThanOrEqualTo(String value) {
			addCriterion("il.ROLE_ID >=", value, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdLessThan(String value) {
			addCriterion("il.ROLE_ID <", value, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdLessThanOrEqualTo(String value) {
			addCriterion("il.ROLE_ID <=", value, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdLike(String value) {
			addCriterion("il.ROLE_ID like", value, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdNotLike(String value) {
			addCriterion("il.ROLE_ID not like", value, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdIn(List<String> values) {
			addCriterion("il.ROLE_ID in", values, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdNotIn(List<String> values) {
			addCriterion("il.ROLE_ID not in", values, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdBetween(String value1, String value2) {
			addCriterion("il.ROLE_ID between", value1, value2, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdNotBetween(String value1, String value2) {
			addCriterion("il.ROLE_ID not between", value1, value2, "roleId");
			return (Criteria) this;
		}

		public Criteria andUserIdIsNull() {
			addCriterion("il.USER_ID is null");
			return (Criteria) this;
		}

		public Criteria andUserIdIsNotNull() {
			addCriterion("il.USER_ID is not null");
			return (Criteria) this;
		}

		public Criteria andUserIdEqualTo(String value) {
			addCriterion("il.USER_ID =", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotEqualTo(String value) {
			addCriterion("il.USER_ID <>", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdGreaterThan(String value) {
			addCriterion("il.USER_ID >", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdGreaterThanOrEqualTo(String value) {
			addCriterion("il.USER_ID >=", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLessThan(String value) {
			addCriterion("il.USER_ID <", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLessThanOrEqualTo(String value) {
			addCriterion("il.USER_ID <=", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLike(String value) {
			addCriterion("il.USER_ID like", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotLike(String value) {
			addCriterion("il.USER_ID not like", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdIn(List<String> values) {
			addCriterion("il.USER_ID in", values, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotIn(List<String> values) {
			addCriterion("il.USER_ID not in", values, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdBetween(String value1, String value2) {
			addCriterion("il.USER_ID between", value1, value2, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotBetween(String value1, String value2) {
			addCriterion("il.USER_ID not between", value1, value2, "userId");
			return (Criteria) this;
		}

		public Criteria andTaskIdIsNull() {
			addCriterion("il.TASK_ID is null");
			return (Criteria) this;
		}

		public Criteria andTaskIdIsNotNull() {
			addCriterion("il.TASK_ID is not null");
			return (Criteria) this;
		}

		public Criteria andTaskIdEqualTo(String value) {
			addCriterion("il.TASK_ID =", value, "taskId");
			return (Criteria) this;
		}

		public Criteria andTaskIdNotEqualTo(String value) {
			addCriterion("il.TASK_ID <>", value, "taskId");
			return (Criteria) this;
		}

		public Criteria andTaskIdGreaterThan(String value) {
			addCriterion("il.TASK_ID >", value, "taskId");
			return (Criteria) this;
		}

		public Criteria andTaskIdGreaterThanOrEqualTo(String value) {
			addCriterion("il.TASK_ID >=", value, "taskId");
			return (Criteria) this;
		}

		public Criteria andTaskIdLessThan(String value) {
			addCriterion("il.TASK_ID <", value, "taskId");
			return (Criteria) this;
		}

		public Criteria andTaskIdLessThanOrEqualTo(String value) {
			addCriterion("il.TASK_ID <=", value, "taskId");
			return (Criteria) this;
		}

		public Criteria andTaskIdLike(String value) {
			addCriterion("il.TASK_ID like", value, "taskId");
			return (Criteria) this;
		}

		public Criteria andTaskIdNotLike(String value) {
			addCriterion("il.TASK_ID not like", value, "taskId");
			return (Criteria) this;
		}

		public Criteria andTaskIdIn(List<String> values) {
			addCriterion("il.TASK_ID in", values, "taskId");
			return (Criteria) this;
		}

		public Criteria andTaskIdNotIn(List<String> values) {
			addCriterion("il.TASK_ID not in", values, "taskId");
			return (Criteria) this;
		}

		public Criteria andTaskIdBetween(String value1, String value2) {
			addCriterion("il.TASK_ID between", value1, value2, "taskId");
			return (Criteria) this;
		}

		public Criteria andTaskIdNotBetween(String value1, String value2) {
			addCriterion("il.TASK_ID not between", value1, value2, "taskId");
			return (Criteria) this;
		}

		public Criteria andProcInstIdIsNull() {
			addCriterion("il.PROC_INST_ID is null");
			return (Criteria) this;
		}

		public Criteria andProcInstIdIsNotNull() {
			addCriterion("il.PROC_INST_ID is not null");
			return (Criteria) this;
		}

		public Criteria andProcInstIdEqualTo(String value) {
			addCriterion("il.PROC_INST_ID =", value, "procInstId");
			return (Criteria) this;
		}

		public Criteria andProcInstIdNotEqualTo(String value) {
			addCriterion("il.PROC_INST_ID <>", value, "procInstId");
			return (Criteria) this;
		}

		public Criteria andProcInstIdGreaterThan(String value) {
			addCriterion("il.PROC_INST_ID >", value, "procInstId");
			return (Criteria) this;
		}

		public Criteria andProcInstIdGreaterThanOrEqualTo(String value) {
			addCriterion("il.PROC_INST_ID >=", value, "procInstId");
			return (Criteria) this;
		}

		public Criteria andProcInstIdLessThan(String value) {
			addCriterion("il.PROC_INST_ID <", value, "procInstId");
			return (Criteria) this;
		}

		public Criteria andProcInstIdLessThanOrEqualTo(String value) {
			addCriterion("il.PROC_INST_ID <=", value, "procInstId");
			return (Criteria) this;
		}

		public Criteria andProcInstIdLike(String value) {
			addCriterion("il.PROC_INST_ID like", value, "procInstId");
			return (Criteria) this;
		}

		public Criteria andProcInstIdNotLike(String value) {
			addCriterion("il.PROC_INST_ID not like", value, "procInstId");
			return (Criteria) this;
		}

		public Criteria andProcInstIdIn(List<String> values) {
			addCriterion("il.PROC_INST_ID in", values, "procInstId");
			return (Criteria) this;
		}

		public Criteria andProcInstIdNotIn(List<String> values) {
			addCriterion("il.PROC_INST_ID not in", values, "procInstId");
			return (Criteria) this;
		}

		public Criteria andProcInstIdBetween(String value1, String value2) {
			addCriterion("il.PROC_INST_ID between", value1, value2, "procInstId");
			return (Criteria) this;
		}

		public Criteria andProcInstIdNotBetween(String value1, String value2) {
			addCriterion("il.PROC_INST_ID not between", value1, value2, "procInstId");
			return (Criteria) this;
		}

		public Criteria andTypeIsNull() {
			addCriterion("il.TYPE is null");
			return (Criteria) this;
		}

		public Criteria andTypeIsNotNull() {
			addCriterion("il.TYPE is not null");
			return (Criteria) this;
		}

		public Criteria andTypeEqualTo(String value) {
			addCriterion("il.TYPE =", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotEqualTo(String value) {
			addCriterion("il.TYPE <>", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThan(String value) {
			addCriterion("il.TYPE >", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThanOrEqualTo(String value) {
			addCriterion("il.TYPE >=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThan(String value) {
			addCriterion("il.TYPE <", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThanOrEqualTo(String value) {
			addCriterion("il.TYPE <=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLike(String value) {
			addCriterion("il.TYPE like", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotLike(String value) {
			addCriterion("il.TYPE not like", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeIn(List<String> values) {
			addCriterion("il.TYPE in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotIn(List<String> values) {
			addCriterion("il.TYPE not in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeBetween(String value1, String value2) {
			addCriterion("il.TYPE between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotBetween(String value1, String value2) {
			addCriterion("il.TYPE not between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andIsdeletedIsNull() {
			addCriterion("il.isdeleted is null");
			return (Criteria) this;
		}

		public Criteria andIsdeletedIsNotNull() {
			addCriterion("il.isdeleted is not null");
			return (Criteria) this;
		}

		public Criteria andIsdeletedEqualTo(Boolean value) {
			addCriterion("il.isdeleted =", value, "isdeleted");
			return (Criteria) this;
		}

		public Criteria andIsdeletedNotEqualTo(Boolean value) {
			addCriterion("il.isdeleted <>", value, "isdeleted");
			return (Criteria) this;
		}

		public Criteria andIsdeletedGreaterThan(Boolean value) {
			addCriterion("il.isdeleted >", value, "isdeleted");
			return (Criteria) this;
		}

		public Criteria andIsdeletedGreaterThanOrEqualTo(Boolean value) {
			addCriterion("il.isdeleted >=", value, "isdeleted");
			return (Criteria) this;
		}

		public Criteria andIsdeletedLessThan(Boolean value) {
			addCriterion("il.isdeleted <", value, "isdeleted");
			return (Criteria) this;
		}

		public Criteria andIsdeletedLessThanOrEqualTo(Boolean value) {
			addCriterion("il.isdeleted <=", value, "isdeleted");
			return (Criteria) this;
		}

		public Criteria andIsdeletedIn(List<Boolean> values) {
			addCriterion("il.isdeleted in", values, "isdeleted");
			return (Criteria) this;
		}

		public Criteria andIsdeletedNotIn(List<Boolean> values) {
			addCriterion("il.isdeleted not in", values, "isdeleted");
			return (Criteria) this;
		}

		public Criteria andIsdeletedBetween(Boolean value1, Boolean value2) {
			addCriterion("il.isdeleted between", value1, value2, "isdeleted");
			return (Criteria) this;
		}

		public Criteria andIsdeletedNotBetween(Boolean value1, Boolean value2) {
			addCriterion("il.isdeleted not between", value1, value2, "isdeleted");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table F_identitylink
	 * @mbggenerated  Tue Dec 05 15:11:47 CST 2017
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
     * This class corresponds to the database table F_identitylink
     *
     * @mbggenerated do_not_delete_during_merge Mon Nov 06 10:39:06 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}