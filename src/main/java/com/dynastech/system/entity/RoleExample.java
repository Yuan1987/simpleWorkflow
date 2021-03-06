package com.dynastech.system.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoleExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table RS_Role
     *
     * @mbggenerated Thu Aug 10 10:05:52 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table RS_Role
     *
     * @mbggenerated Thu Aug 10 10:05:52 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table RS_Role
     *
     * @mbggenerated Thu Aug 10 10:05:52 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RS_Role
     *
     * @mbggenerated Thu Aug 10 10:05:52 CST 2017
     */
    public RoleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RS_Role
     *
     * @mbggenerated Thu Aug 10 10:05:52 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RS_Role
     *
     * @mbggenerated Thu Aug 10 10:05:52 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RS_Role
     *
     * @mbggenerated Thu Aug 10 10:05:52 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RS_Role
     *
     * @mbggenerated Thu Aug 10 10:05:52 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RS_Role
     *
     * @mbggenerated Thu Aug 10 10:05:52 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RS_Role
     *
     * @mbggenerated Thu Aug 10 10:05:52 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RS_Role
     *
     * @mbggenerated Thu Aug 10 10:05:52 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RS_Role
     *
     * @mbggenerated Thu Aug 10 10:05:52 CST 2017
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RS_Role
     *
     * @mbggenerated Thu Aug 10 10:05:52 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RS_Role
     *
     * @mbggenerated Thu Aug 10 10:05:52 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table RS_Role
     *
     * @mbggenerated Thu Aug 10 10:05:52 CST 2017
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
            addCriterion("r.Id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("r.Id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("r.Id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("r.Id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("r.Id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("r.Id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("r.Id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("r.Id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("r.Id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("r.Id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("r.Id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("r.Id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("r.Id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("r.Id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDatetimecreatedIsNull() {
            addCriterion("r.DateTimeCreated is null");
            return (Criteria) this;
        }

        public Criteria andDatetimecreatedIsNotNull() {
            addCriterion("r.DateTimeCreated is not null");
            return (Criteria) this;
        }

        public Criteria andDatetimecreatedEqualTo(Date value) {
            addCriterion("r.DateTimeCreated =", value, "datetimecreated");
            return (Criteria) this;
        }

        public Criteria andDatetimecreatedNotEqualTo(Date value) {
            addCriterion("r.DateTimeCreated <>", value, "datetimecreated");
            return (Criteria) this;
        }

        public Criteria andDatetimecreatedGreaterThan(Date value) {
            addCriterion("r.DateTimeCreated >", value, "datetimecreated");
            return (Criteria) this;
        }

        public Criteria andDatetimecreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("r.DateTimeCreated >=", value, "datetimecreated");
            return (Criteria) this;
        }

        public Criteria andDatetimecreatedLessThan(Date value) {
            addCriterion("r.DateTimeCreated <", value, "datetimecreated");
            return (Criteria) this;
        }

        public Criteria andDatetimecreatedLessThanOrEqualTo(Date value) {
            addCriterion("r.DateTimeCreated <=", value, "datetimecreated");
            return (Criteria) this;
        }

        public Criteria andDatetimecreatedIn(List<Date> values) {
            addCriterion("r.DateTimeCreated in", values, "datetimecreated");
            return (Criteria) this;
        }

        public Criteria andDatetimecreatedNotIn(List<Date> values) {
            addCriterion("r.DateTimeCreated not in", values, "datetimecreated");
            return (Criteria) this;
        }

        public Criteria andDatetimecreatedBetween(Date value1, Date value2) {
            addCriterion("r.DateTimeCreated between", value1, value2, "datetimecreated");
            return (Criteria) this;
        }

        public Criteria andDatetimecreatedNotBetween(Date value1, Date value2) {
            addCriterion("r.DateTimeCreated not between", value1, value2, "datetimecreated");
            return (Criteria) this;
        }

        public Criteria andDatetimemodifiedIsNull() {
            addCriterion("r.DateTimeModified is null");
            return (Criteria) this;
        }

        public Criteria andDatetimemodifiedIsNotNull() {
            addCriterion("r.DateTimeModified is not null");
            return (Criteria) this;
        }

        public Criteria andDatetimemodifiedEqualTo(Date value) {
            addCriterion("r.DateTimeModified =", value, "datetimemodified");
            return (Criteria) this;
        }

        public Criteria andDatetimemodifiedNotEqualTo(Date value) {
            addCriterion("r.DateTimeModified <>", value, "datetimemodified");
            return (Criteria) this;
        }

        public Criteria andDatetimemodifiedGreaterThan(Date value) {
            addCriterion("r.DateTimeModified >", value, "datetimemodified");
            return (Criteria) this;
        }

        public Criteria andDatetimemodifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("r.DateTimeModified >=", value, "datetimemodified");
            return (Criteria) this;
        }

        public Criteria andDatetimemodifiedLessThan(Date value) {
            addCriterion("r.DateTimeModified <", value, "datetimemodified");
            return (Criteria) this;
        }

        public Criteria andDatetimemodifiedLessThanOrEqualTo(Date value) {
            addCriterion("r.DateTimeModified <=", value, "datetimemodified");
            return (Criteria) this;
        }

        public Criteria andDatetimemodifiedIn(List<Date> values) {
            addCriterion("r.DateTimeModified in", values, "datetimemodified");
            return (Criteria) this;
        }

        public Criteria andDatetimemodifiedNotIn(List<Date> values) {
            addCriterion("r.DateTimeModified not in", values, "datetimemodified");
            return (Criteria) this;
        }

        public Criteria andDatetimemodifiedBetween(Date value1, Date value2) {
            addCriterion("r.DateTimeModified between", value1, value2, "datetimemodified");
            return (Criteria) this;
        }

        public Criteria andDatetimemodifiedNotBetween(Date value1, Date value2) {
            addCriterion("r.DateTimeModified not between", value1, value2, "datetimemodified");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("r.Description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("r.Description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("r.Description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("r.Description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("r.Description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("r.Description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("r.Description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("r.Description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("r.Description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("r.Description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("r.Description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("r.Description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("r.Description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("r.Description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDisplaynameIsNull() {
            addCriterion("r.DisplayName is null");
            return (Criteria) this;
        }

        public Criteria andDisplaynameIsNotNull() {
            addCriterion("r.DisplayName is not null");
            return (Criteria) this;
        }

        public Criteria andDisplaynameEqualTo(String value) {
            addCriterion("r.DisplayName =", value, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameNotEqualTo(String value) {
            addCriterion("r.DisplayName <>", value, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameGreaterThan(String value) {
            addCriterion("r.DisplayName >", value, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameGreaterThanOrEqualTo(String value) {
            addCriterion("r.DisplayName >=", value, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameLessThan(String value) {
            addCriterion("r.DisplayName <", value, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameLessThanOrEqualTo(String value) {
            addCriterion("r.DisplayName <=", value, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameLike(String value) {
            addCriterion("r.DisplayName like", value, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameNotLike(String value) {
            addCriterion("r.DisplayName not like", value, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameIn(List<String> values) {
            addCriterion("r.DisplayName in", values, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameNotIn(List<String> values) {
            addCriterion("r.DisplayName not in", values, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameBetween(String value1, String value2) {
            addCriterion("r.DisplayName between", value1, value2, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameNotBetween(String value1, String value2) {
            addCriterion("r.DisplayName not between", value1, value2, "displayname");
            return (Criteria) this;
        }

        public Criteria andIsbuiltinIsNull() {
            addCriterion("r.IsBuiltin is null");
            return (Criteria) this;
        }

        public Criteria andIsbuiltinIsNotNull() {
            addCriterion("r.IsBuiltin is not null");
            return (Criteria) this;
        }

        public Criteria andIsbuiltinEqualTo(Boolean value) {
            addCriterion("r.IsBuiltin =", value, "isbuiltin");
            return (Criteria) this;
        }

        public Criteria andIsbuiltinNotEqualTo(Boolean value) {
            addCriterion("r.IsBuiltin <>", value, "isbuiltin");
            return (Criteria) this;
        }

        public Criteria andIsbuiltinGreaterThan(Boolean value) {
            addCriterion("r.IsBuiltin >", value, "isbuiltin");
            return (Criteria) this;
        }

        public Criteria andIsbuiltinGreaterThanOrEqualTo(Boolean value) {
            addCriterion("r.IsBuiltin >=", value, "isbuiltin");
            return (Criteria) this;
        }

        public Criteria andIsbuiltinLessThan(Boolean value) {
            addCriterion("r.IsBuiltin <", value, "isbuiltin");
            return (Criteria) this;
        }

        public Criteria andIsbuiltinLessThanOrEqualTo(Boolean value) {
            addCriterion("r.IsBuiltin <=", value, "isbuiltin");
            return (Criteria) this;
        }

        public Criteria andIsbuiltinIn(List<Boolean> values) {
            addCriterion("r.IsBuiltin in", values, "isbuiltin");
            return (Criteria) this;
        }

        public Criteria andIsbuiltinNotIn(List<Boolean> values) {
            addCriterion("r.IsBuiltin not in", values, "isbuiltin");
            return (Criteria) this;
        }

        public Criteria andIsbuiltinBetween(Boolean value1, Boolean value2) {
            addCriterion("r.IsBuiltin between", value1, value2, "isbuiltin");
            return (Criteria) this;
        }

        public Criteria andIsbuiltinNotBetween(Boolean value1, Boolean value2) {
            addCriterion("r.IsBuiltin not between", value1, value2, "isbuiltin");
            return (Criteria) this;
        }

        public Criteria andIsdeletedIsNull() {
            addCriterion("r.IsDeleted is null");
            return (Criteria) this;
        }

        public Criteria andIsdeletedIsNotNull() {
            addCriterion("r.IsDeleted is not null");
            return (Criteria) this;
        }

        public Criteria andIsdeletedEqualTo(Boolean value) {
            addCriterion("r.IsDeleted =", value, "isdeleted");
            return (Criteria) this;
        }

        public Criteria andIsdeletedNotEqualTo(Boolean value) {
            addCriterion("r.IsDeleted <>", value, "isdeleted");
            return (Criteria) this;
        }

        public Criteria andIsdeletedGreaterThan(Boolean value) {
            addCriterion("r.IsDeleted >", value, "isdeleted");
            return (Criteria) this;
        }

        public Criteria andIsdeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("r.IsDeleted >=", value, "isdeleted");
            return (Criteria) this;
        }

        public Criteria andIsdeletedLessThan(Boolean value) {
            addCriterion("r.IsDeleted <", value, "isdeleted");
            return (Criteria) this;
        }

        public Criteria andIsdeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("r.IsDeleted <=", value, "isdeleted");
            return (Criteria) this;
        }

        public Criteria andIsdeletedIn(List<Boolean> values) {
            addCriterion("r.IsDeleted in", values, "isdeleted");
            return (Criteria) this;
        }

        public Criteria andIsdeletedNotIn(List<Boolean> values) {
            addCriterion("r.IsDeleted not in", values, "isdeleted");
            return (Criteria) this;
        }

        public Criteria andIsdeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("r.IsDeleted between", value1, value2, "isdeleted");
            return (Criteria) this;
        }

        public Criteria andIsdeletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("r.IsDeleted not between", value1, value2, "isdeleted");
            return (Criteria) this;
        }

        public Criteria andIsenabledIsNull() {
            addCriterion("r.IsEnabled is null");
            return (Criteria) this;
        }

        public Criteria andIsenabledIsNotNull() {
            addCriterion("r.IsEnabled is not null");
            return (Criteria) this;
        }

        public Criteria andIsenabledEqualTo(Boolean value) {
            addCriterion("r.IsEnabled =", value, "isenabled");
            return (Criteria) this;
        }

        public Criteria andIsenabledNotEqualTo(Boolean value) {
            addCriterion("r.IsEnabled <>", value, "isenabled");
            return (Criteria) this;
        }

        public Criteria andIsenabledGreaterThan(Boolean value) {
            addCriterion("r.IsEnabled >", value, "isenabled");
            return (Criteria) this;
        }

        public Criteria andIsenabledGreaterThanOrEqualTo(Boolean value) {
            addCriterion("r.IsEnabled >=", value, "isenabled");
            return (Criteria) this;
        }

        public Criteria andIsenabledLessThan(Boolean value) {
            addCriterion("r.IsEnabled <", value, "isenabled");
            return (Criteria) this;
        }

        public Criteria andIsenabledLessThanOrEqualTo(Boolean value) {
            addCriterion("r.IsEnabled <=", value, "isenabled");
            return (Criteria) this;
        }

        public Criteria andIsenabledIn(List<Boolean> values) {
            addCriterion("r.IsEnabled in", values, "isenabled");
            return (Criteria) this;
        }

        public Criteria andIsenabledNotIn(List<Boolean> values) {
            addCriterion("r.IsEnabled not in", values, "isenabled");
            return (Criteria) this;
        }

        public Criteria andIsenabledBetween(Boolean value1, Boolean value2) {
            addCriterion("r.IsEnabled between", value1, value2, "isenabled");
            return (Criteria) this;
        }

        public Criteria andIsenabledNotBetween(Boolean value1, Boolean value2) {
            addCriterion("r.IsEnabled not between", value1, value2, "isenabled");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("r.Name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("r.Name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("r.Name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("r.Name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("r.Name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("r.Name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("r.Name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("r.Name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("r.Name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("r.Name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("r.Name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("r.Name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("r.Name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("r.Name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andOrdinalIsNull() {
            addCriterion("r.Ordinal is null");
            return (Criteria) this;
        }

        public Criteria andOrdinalIsNotNull() {
            addCriterion("r.Ordinal is not null");
            return (Criteria) this;
        }

        public Criteria andOrdinalEqualTo(Integer value) {
            addCriterion("r.Ordinal =", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotEqualTo(Integer value) {
            addCriterion("r.Ordinal <>", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalGreaterThan(Integer value) {
            addCriterion("r.Ordinal >", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalGreaterThanOrEqualTo(Integer value) {
            addCriterion("r.Ordinal >=", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalLessThan(Integer value) {
            addCriterion("r.Ordinal <", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalLessThanOrEqualTo(Integer value) {
            addCriterion("r.Ordinal <=", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalIn(List<Integer> values) {
            addCriterion("r.Ordinal in", values, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotIn(List<Integer> values) {
            addCriterion("r.Ordinal not in", values, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalBetween(Integer value1, Integer value2) {
            addCriterion("r.Ordinal between", value1, value2, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotBetween(Integer value1, Integer value2) {
            addCriterion("r.Ordinal not between", value1, value2, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrganizationidIsNull() {
            addCriterion("r.OrganizationId is null");
            return (Criteria) this;
        }

        public Criteria andOrganizationidIsNotNull() {
            addCriterion("r.OrganizationId is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizationidEqualTo(String value) {
            addCriterion("r.OrganizationId =", value, "organizationid");
            return (Criteria) this;
        }

        public Criteria andOrganizationidNotEqualTo(String value) {
            addCriterion("r.OrganizationId <>", value, "organizationid");
            return (Criteria) this;
        }

        public Criteria andOrganizationidGreaterThan(String value) {
            addCriterion("r.OrganizationId >", value, "organizationid");
            return (Criteria) this;
        }

        public Criteria andOrganizationidGreaterThanOrEqualTo(String value) {
            addCriterion("r.OrganizationId >=", value, "organizationid");
            return (Criteria) this;
        }

        public Criteria andOrganizationidLessThan(String value) {
            addCriterion("r.OrganizationId <", value, "organizationid");
            return (Criteria) this;
        }

        public Criteria andOrganizationidLessThanOrEqualTo(String value) {
            addCriterion("r.OrganizationId <=", value, "organizationid");
            return (Criteria) this;
        }

        public Criteria andOrganizationidLike(String value) {
            addCriterion("r.OrganizationId like", value, "organizationid");
            return (Criteria) this;
        }

        public Criteria andOrganizationidNotLike(String value) {
            addCriterion("r.OrganizationId not like", value, "organizationid");
            return (Criteria) this;
        }

        public Criteria andOrganizationidIn(List<String> values) {
            addCriterion("r.OrganizationId in", values, "organizationid");
            return (Criteria) this;
        }

        public Criteria andOrganizationidNotIn(List<String> values) {
            addCriterion("r.OrganizationId not in", values, "organizationid");
            return (Criteria) this;
        }

        public Criteria andOrganizationidBetween(String value1, String value2) {
            addCriterion("r.OrganizationId between", value1, value2, "organizationid");
            return (Criteria) this;
        }

        public Criteria andOrganizationidNotBetween(String value1, String value2) {
            addCriterion("r.OrganizationId not between", value1, value2, "organizationid");
            return (Criteria) this;
        }

        public Criteria andSnIsNull() {
            addCriterion("r.Sn is null");
            return (Criteria) this;
        }

        public Criteria andSnIsNotNull() {
            addCriterion("r.Sn is not null");
            return (Criteria) this;
        }

        public Criteria andSnEqualTo(Integer value) {
            addCriterion("r.Sn =", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotEqualTo(Integer value) {
            addCriterion("r.Sn <>", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnGreaterThan(Integer value) {
            addCriterion("r.Sn >", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnGreaterThanOrEqualTo(Integer value) {
            addCriterion("r.Sn >=", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnLessThan(Integer value) {
            addCriterion("r.Sn <", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnLessThanOrEqualTo(Integer value) {
            addCriterion("r.Sn <=", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnIn(List<Integer> values) {
            addCriterion("r.Sn in", values, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotIn(List<Integer> values) {
            addCriterion("r.Sn not in", values, "sn");
            return (Criteria) this;
        }

        public Criteria andSnBetween(Integer value1, Integer value2) {
            addCriterion("r.Sn between", value1, value2, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotBetween(Integer value1, Integer value2) {
            addCriterion("r.Sn not between", value1, value2, "sn");
            return (Criteria) this;
        }

        public Criteria andTypeidIsNull() {
            addCriterion("r.TypeId is null");
            return (Criteria) this;
        }

        public Criteria andTypeidIsNotNull() {
            addCriterion("r.TypeId is not null");
            return (Criteria) this;
        }

        public Criteria andTypeidEqualTo(String value) {
            addCriterion("r.TypeId =", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidNotEqualTo(String value) {
            addCriterion("r.TypeId <>", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidGreaterThan(String value) {
            addCriterion("r.TypeId >", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidGreaterThanOrEqualTo(String value) {
            addCriterion("r.TypeId >=", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidLessThan(String value) {
            addCriterion("r.TypeId <", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidLessThanOrEqualTo(String value) {
            addCriterion("r.TypeId <=", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidLike(String value) {
            addCriterion("r.TypeId like", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidNotLike(String value) {
            addCriterion("r.TypeId not like", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidIn(List<String> values) {
            addCriterion("r.TypeId in", values, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidNotIn(List<String> values) {
            addCriterion("r.TypeId not in", values, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidBetween(String value1, String value2) {
            addCriterion("r.TypeId between", value1, value2, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidNotBetween(String value1, String value2) {
            addCriterion("r.TypeId not between", value1, value2, "typeid");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table RS_Role
     *
     * @mbggenerated do_not_delete_during_merge Thu Aug 10 10:05:52 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table RS_Role
     *
     * @mbggenerated Thu Aug 10 10:05:52 CST 2017
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
}