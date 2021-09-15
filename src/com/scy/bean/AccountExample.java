package com.scy.bean;

import java.util.ArrayList;
import java.util.List;

public class AccountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public AccountExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
    }

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

        public Criteria andAccountIdIsNull() {
            addCriterion("account_id is null");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNotNull() {
            addCriterion("account_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccountIdEqualTo(Integer value) {
            addCriterion("account_id =", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotEqualTo(Integer value) {
            addCriterion("account_id <>", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThan(Integer value) {
            addCriterion("account_id >", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("account_id >=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThan(Integer value) {
            addCriterion("account_id <", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThanOrEqualTo(Integer value) {
            addCriterion("account_id <=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdIn(List<Integer> values) {
            addCriterion("account_id in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotIn(List<Integer> values) {
            addCriterion("account_id not in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdBetween(Integer value1, Integer value2) {
            addCriterion("account_id between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotBetween(Integer value1, Integer value2) {
            addCriterion("account_id not between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountNumIsNull() {
            addCriterion("account_num is null");
            return (Criteria) this;
        }

        public Criteria andAccountNumIsNotNull() {
            addCriterion("account_num is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNumEqualTo(String value) {
            addCriterion("account_num =", value, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumNotEqualTo(String value) {
            addCriterion("account_num <>", value, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumGreaterThan(String value) {
            addCriterion("account_num >", value, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumGreaterThanOrEqualTo(String value) {
            addCriterion("account_num >=", value, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumLessThan(String value) {
            addCriterion("account_num <", value, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumLessThanOrEqualTo(String value) {
            addCriterion("account_num <=", value, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumLike(String value) {
            addCriterion("account_num like", value, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumNotLike(String value) {
            addCriterion("account_num not like", value, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumIn(List<String> values) {
            addCriterion("account_num in", values, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumNotIn(List<String> values) {
            addCriterion("account_num not in", values, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumBetween(String value1, String value2) {
            addCriterion("account_num between", value1, value2, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumNotBetween(String value1, String value2) {
            addCriterion("account_num not between", value1, value2, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountPwdIsNull() {
            addCriterion("account_pwd is null");
            return (Criteria) this;
        }

        public Criteria andAccountPwdIsNotNull() {
            addCriterion("account_pwd is not null");
            return (Criteria) this;
        }

        public Criteria andAccountPwdEqualTo(String value) {
            addCriterion("account_pwd =", value, "accountPwd");
            return (Criteria) this;
        }

        public Criteria andAccountPwdNotEqualTo(String value) {
            addCriterion("account_pwd <>", value, "accountPwd");
            return (Criteria) this;
        }

        public Criteria andAccountPwdGreaterThan(String value) {
            addCriterion("account_pwd >", value, "accountPwd");
            return (Criteria) this;
        }

        public Criteria andAccountPwdGreaterThanOrEqualTo(String value) {
            addCriterion("account_pwd >=", value, "accountPwd");
            return (Criteria) this;
        }

        public Criteria andAccountPwdLessThan(String value) {
            addCriterion("account_pwd <", value, "accountPwd");
            return (Criteria) this;
        }

        public Criteria andAccountPwdLessThanOrEqualTo(String value) {
            addCriterion("account_pwd <=", value, "accountPwd");
            return (Criteria) this;
        }

        public Criteria andAccountPwdLike(String value) {
            addCriterion("account_pwd like", value, "accountPwd");
            return (Criteria) this;
        }

        public Criteria andAccountPwdNotLike(String value) {
            addCriterion("account_pwd not like", value, "accountPwd");
            return (Criteria) this;
        }

        public Criteria andAccountPwdIn(List<String> values) {
            addCriterion("account_pwd in", values, "accountPwd");
            return (Criteria) this;
        }

        public Criteria andAccountPwdNotIn(List<String> values) {
            addCriterion("account_pwd not in", values, "accountPwd");
            return (Criteria) this;
        }

        public Criteria andAccountPwdBetween(String value1, String value2) {
            addCriterion("account_pwd between", value1, value2, "accountPwd");
            return (Criteria) this;
        }

        public Criteria andAccountPwdNotBetween(String value1, String value2) {
            addCriterion("account_pwd not between", value1, value2, "accountPwd");
            return (Criteria) this;
        }

        public Criteria andAccountRealNameIsNull() {
            addCriterion("account_real_name is null");
            return (Criteria) this;
        }

        public Criteria andAccountRealNameIsNotNull() {
            addCriterion("account_real_name is not null");
            return (Criteria) this;
        }

        public Criteria andAccountRealNameEqualTo(String value) {
            addCriterion("account_real_name =", value, "accountRealName");
            return (Criteria) this;
        }

        public Criteria andAccountRealNameNotEqualTo(String value) {
            addCriterion("account_real_name <>", value, "accountRealName");
            return (Criteria) this;
        }

        public Criteria andAccountRealNameGreaterThan(String value) {
            addCriterion("account_real_name >", value, "accountRealName");
            return (Criteria) this;
        }

        public Criteria andAccountRealNameGreaterThanOrEqualTo(String value) {
            addCriterion("account_real_name >=", value, "accountRealName");
            return (Criteria) this;
        }

        public Criteria andAccountRealNameLessThan(String value) {
            addCriterion("account_real_name <", value, "accountRealName");
            return (Criteria) this;
        }

        public Criteria andAccountRealNameLessThanOrEqualTo(String value) {
            addCriterion("account_real_name <=", value, "accountRealName");
            return (Criteria) this;
        }

        public Criteria andAccountRealNameLike(String value) {
            addCriterion("account_real_name like", value, "accountRealName");
            return (Criteria) this;
        }

        public Criteria andAccountRealNameNotLike(String value) {
            addCriterion("account_real_name not like", value, "accountRealName");
            return (Criteria) this;
        }

        public Criteria andAccountRealNameIn(List<String> values) {
            addCriterion("account_real_name in", values, "accountRealName");
            return (Criteria) this;
        }

        public Criteria andAccountRealNameNotIn(List<String> values) {
            addCriterion("account_real_name not in", values, "accountRealName");
            return (Criteria) this;
        }

        public Criteria andAccountRealNameBetween(String value1, String value2) {
            addCriterion("account_real_name between", value1, value2, "accountRealName");
            return (Criteria) this;
        }

        public Criteria andAccountRealNameNotBetween(String value1, String value2) {
            addCriterion("account_real_name not between", value1, value2, "accountRealName");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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