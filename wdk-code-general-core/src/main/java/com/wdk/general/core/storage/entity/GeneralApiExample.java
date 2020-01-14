package com.wdk.general.core.storage.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GeneralApiExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private String sumCol;

    private Integer offset;

    private Integer limit;

    public GeneralApiExample() {
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
        this.sumCol = null;
        this.offset = null;
        this.limit = null;
    }

    public String getSumCol() {
        return this.sumCol;
    }

    public void setSumCol(String sumCol) {
        this.sumCol = sumCol;
    }

    public GeneralApiExample sumId() {
        this.sumCol="id";
        return this;
    }

    public GeneralApiExample sumPath() {
        this.sumCol="path";
        return this;
    }

    public GeneralApiExample sumArgs() {
        this.sumCol="args";
        return this;
    }

    public GeneralApiExample sumVo() {
        this.sumCol="vo";
        return this;
    }

    public GeneralApiExample sumFroms() {
        this.sumCol="froms";
        return this;
    }

    public GeneralApiExample sumWheres() {
        this.sumCol="wheres";
        return this;
    }

    public GeneralApiExample sumOrderbys() {
        this.sumCol="orderbys";
        return this;
    }

    public GeneralApiExample sumLimits() {
        this.sumCol="limits";
        return this;
    }

    public GeneralApiExample sumCreateTime() {
        this.sumCol="create_time";
        return this;
    }

    public GeneralApiExample sumUpdateTime() {
        this.sumCol="update_time";
        return this;
    }

    public Integer getOffset() {
        return this.offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public GeneralApiExample page(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
        return this;
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPathIsNull() {
            addCriterion("path is null");
            return (Criteria) this;
        }

        public Criteria andPathIsNotNull() {
            addCriterion("path is not null");
            return (Criteria) this;
        }

        public Criteria andPathEqualTo(String value) {
            addCriterion("path =", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotEqualTo(String value) {
            addCriterion("path <>", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThan(String value) {
            addCriterion("path >", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThanOrEqualTo(String value) {
            addCriterion("path >=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThan(String value) {
            addCriterion("path <", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThanOrEqualTo(String value) {
            addCriterion("path <=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLike(String value) {
            addCriterion("path like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotLike(String value) {
            addCriterion("path not like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathIn(List<String> values) {
            addCriterion("path in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotIn(List<String> values) {
            addCriterion("path not in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathBetween(String value1, String value2) {
            addCriterion("path between", value1, value2, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotBetween(String value1, String value2) {
            addCriterion("path not between", value1, value2, "path");
            return (Criteria) this;
        }

        public Criteria andArgsIsNull() {
            addCriterion("args is null");
            return (Criteria) this;
        }

        public Criteria andArgsIsNotNull() {
            addCriterion("args is not null");
            return (Criteria) this;
        }

        public Criteria andArgsEqualTo(String value) {
            addCriterion("args =", value, "args");
            return (Criteria) this;
        }

        public Criteria andArgsNotEqualTo(String value) {
            addCriterion("args <>", value, "args");
            return (Criteria) this;
        }

        public Criteria andArgsGreaterThan(String value) {
            addCriterion("args >", value, "args");
            return (Criteria) this;
        }

        public Criteria andArgsGreaterThanOrEqualTo(String value) {
            addCriterion("args >=", value, "args");
            return (Criteria) this;
        }

        public Criteria andArgsLessThan(String value) {
            addCriterion("args <", value, "args");
            return (Criteria) this;
        }

        public Criteria andArgsLessThanOrEqualTo(String value) {
            addCriterion("args <=", value, "args");
            return (Criteria) this;
        }

        public Criteria andArgsLike(String value) {
            addCriterion("args like", value, "args");
            return (Criteria) this;
        }

        public Criteria andArgsNotLike(String value) {
            addCriterion("args not like", value, "args");
            return (Criteria) this;
        }

        public Criteria andArgsIn(List<String> values) {
            addCriterion("args in", values, "args");
            return (Criteria) this;
        }

        public Criteria andArgsNotIn(List<String> values) {
            addCriterion("args not in", values, "args");
            return (Criteria) this;
        }

        public Criteria andArgsBetween(String value1, String value2) {
            addCriterion("args between", value1, value2, "args");
            return (Criteria) this;
        }

        public Criteria andArgsNotBetween(String value1, String value2) {
            addCriterion("args not between", value1, value2, "args");
            return (Criteria) this;
        }

        public Criteria andVoIsNull() {
            addCriterion("vo is null");
            return (Criteria) this;
        }

        public Criteria andVoIsNotNull() {
            addCriterion("vo is not null");
            return (Criteria) this;
        }

        public Criteria andVoEqualTo(String value) {
            addCriterion("vo =", value, "vo");
            return (Criteria) this;
        }

        public Criteria andVoNotEqualTo(String value) {
            addCriterion("vo <>", value, "vo");
            return (Criteria) this;
        }

        public Criteria andVoGreaterThan(String value) {
            addCriterion("vo >", value, "vo");
            return (Criteria) this;
        }

        public Criteria andVoGreaterThanOrEqualTo(String value) {
            addCriterion("vo >=", value, "vo");
            return (Criteria) this;
        }

        public Criteria andVoLessThan(String value) {
            addCriterion("vo <", value, "vo");
            return (Criteria) this;
        }

        public Criteria andVoLessThanOrEqualTo(String value) {
            addCriterion("vo <=", value, "vo");
            return (Criteria) this;
        }

        public Criteria andVoLike(String value) {
            addCriterion("vo like", value, "vo");
            return (Criteria) this;
        }

        public Criteria andVoNotLike(String value) {
            addCriterion("vo not like", value, "vo");
            return (Criteria) this;
        }

        public Criteria andVoIn(List<String> values) {
            addCriterion("vo in", values, "vo");
            return (Criteria) this;
        }

        public Criteria andVoNotIn(List<String> values) {
            addCriterion("vo not in", values, "vo");
            return (Criteria) this;
        }

        public Criteria andVoBetween(String value1, String value2) {
            addCriterion("vo between", value1, value2, "vo");
            return (Criteria) this;
        }

        public Criteria andVoNotBetween(String value1, String value2) {
            addCriterion("vo not between", value1, value2, "vo");
            return (Criteria) this;
        }

        public Criteria andFromsIsNull() {
            addCriterion("froms is null");
            return (Criteria) this;
        }

        public Criteria andFromsIsNotNull() {
            addCriterion("froms is not null");
            return (Criteria) this;
        }

        public Criteria andFromsEqualTo(String value) {
            addCriterion("froms =", value, "froms");
            return (Criteria) this;
        }

        public Criteria andFromsNotEqualTo(String value) {
            addCriterion("froms <>", value, "froms");
            return (Criteria) this;
        }

        public Criteria andFromsGreaterThan(String value) {
            addCriterion("froms >", value, "froms");
            return (Criteria) this;
        }

        public Criteria andFromsGreaterThanOrEqualTo(String value) {
            addCriterion("froms >=", value, "froms");
            return (Criteria) this;
        }

        public Criteria andFromsLessThan(String value) {
            addCriterion("froms <", value, "froms");
            return (Criteria) this;
        }

        public Criteria andFromsLessThanOrEqualTo(String value) {
            addCriterion("froms <=", value, "froms");
            return (Criteria) this;
        }

        public Criteria andFromsLike(String value) {
            addCriterion("froms like", value, "froms");
            return (Criteria) this;
        }

        public Criteria andFromsNotLike(String value) {
            addCriterion("froms not like", value, "froms");
            return (Criteria) this;
        }

        public Criteria andFromsIn(List<String> values) {
            addCriterion("froms in", values, "froms");
            return (Criteria) this;
        }

        public Criteria andFromsNotIn(List<String> values) {
            addCriterion("froms not in", values, "froms");
            return (Criteria) this;
        }

        public Criteria andFromsBetween(String value1, String value2) {
            addCriterion("froms between", value1, value2, "froms");
            return (Criteria) this;
        }

        public Criteria andFromsNotBetween(String value1, String value2) {
            addCriterion("froms not between", value1, value2, "froms");
            return (Criteria) this;
        }

        public Criteria andWheresIsNull() {
            addCriterion("wheres is null");
            return (Criteria) this;
        }

        public Criteria andWheresIsNotNull() {
            addCriterion("wheres is not null");
            return (Criteria) this;
        }

        public Criteria andWheresEqualTo(String value) {
            addCriterion("wheres =", value, "wheres");
            return (Criteria) this;
        }

        public Criteria andWheresNotEqualTo(String value) {
            addCriterion("wheres <>", value, "wheres");
            return (Criteria) this;
        }

        public Criteria andWheresGreaterThan(String value) {
            addCriterion("wheres >", value, "wheres");
            return (Criteria) this;
        }

        public Criteria andWheresGreaterThanOrEqualTo(String value) {
            addCriterion("wheres >=", value, "wheres");
            return (Criteria) this;
        }

        public Criteria andWheresLessThan(String value) {
            addCriterion("wheres <", value, "wheres");
            return (Criteria) this;
        }

        public Criteria andWheresLessThanOrEqualTo(String value) {
            addCriterion("wheres <=", value, "wheres");
            return (Criteria) this;
        }

        public Criteria andWheresLike(String value) {
            addCriterion("wheres like", value, "wheres");
            return (Criteria) this;
        }

        public Criteria andWheresNotLike(String value) {
            addCriterion("wheres not like", value, "wheres");
            return (Criteria) this;
        }

        public Criteria andWheresIn(List<String> values) {
            addCriterion("wheres in", values, "wheres");
            return (Criteria) this;
        }

        public Criteria andWheresNotIn(List<String> values) {
            addCriterion("wheres not in", values, "wheres");
            return (Criteria) this;
        }

        public Criteria andWheresBetween(String value1, String value2) {
            addCriterion("wheres between", value1, value2, "wheres");
            return (Criteria) this;
        }

        public Criteria andWheresNotBetween(String value1, String value2) {
            addCriterion("wheres not between", value1, value2, "wheres");
            return (Criteria) this;
        }

        public Criteria andOrderbysIsNull() {
            addCriterion("orderbys is null");
            return (Criteria) this;
        }

        public Criteria andOrderbysIsNotNull() {
            addCriterion("orderbys is not null");
            return (Criteria) this;
        }

        public Criteria andOrderbysEqualTo(String value) {
            addCriterion("orderbys =", value, "orderbys");
            return (Criteria) this;
        }

        public Criteria andOrderbysNotEqualTo(String value) {
            addCriterion("orderbys <>", value, "orderbys");
            return (Criteria) this;
        }

        public Criteria andOrderbysGreaterThan(String value) {
            addCriterion("orderbys >", value, "orderbys");
            return (Criteria) this;
        }

        public Criteria andOrderbysGreaterThanOrEqualTo(String value) {
            addCriterion("orderbys >=", value, "orderbys");
            return (Criteria) this;
        }

        public Criteria andOrderbysLessThan(String value) {
            addCriterion("orderbys <", value, "orderbys");
            return (Criteria) this;
        }

        public Criteria andOrderbysLessThanOrEqualTo(String value) {
            addCriterion("orderbys <=", value, "orderbys");
            return (Criteria) this;
        }

        public Criteria andOrderbysLike(String value) {
            addCriterion("orderbys like", value, "orderbys");
            return (Criteria) this;
        }

        public Criteria andOrderbysNotLike(String value) {
            addCriterion("orderbys not like", value, "orderbys");
            return (Criteria) this;
        }

        public Criteria andOrderbysIn(List<String> values) {
            addCriterion("orderbys in", values, "orderbys");
            return (Criteria) this;
        }

        public Criteria andOrderbysNotIn(List<String> values) {
            addCriterion("orderbys not in", values, "orderbys");
            return (Criteria) this;
        }

        public Criteria andOrderbysBetween(String value1, String value2) {
            addCriterion("orderbys between", value1, value2, "orderbys");
            return (Criteria) this;
        }

        public Criteria andOrderbysNotBetween(String value1, String value2) {
            addCriterion("orderbys not between", value1, value2, "orderbys");
            return (Criteria) this;
        }

        public Criteria andLimitsIsNull() {
            addCriterion("limits is null");
            return (Criteria) this;
        }

        public Criteria andLimitsIsNotNull() {
            addCriterion("limits is not null");
            return (Criteria) this;
        }

        public Criteria andLimitsEqualTo(String value) {
            addCriterion("limits =", value, "limits");
            return (Criteria) this;
        }

        public Criteria andLimitsNotEqualTo(String value) {
            addCriterion("limits <>", value, "limits");
            return (Criteria) this;
        }

        public Criteria andLimitsGreaterThan(String value) {
            addCriterion("limits >", value, "limits");
            return (Criteria) this;
        }

        public Criteria andLimitsGreaterThanOrEqualTo(String value) {
            addCriterion("limits >=", value, "limits");
            return (Criteria) this;
        }

        public Criteria andLimitsLessThan(String value) {
            addCriterion("limits <", value, "limits");
            return (Criteria) this;
        }

        public Criteria andLimitsLessThanOrEqualTo(String value) {
            addCriterion("limits <=", value, "limits");
            return (Criteria) this;
        }

        public Criteria andLimitsLike(String value) {
            addCriterion("limits like", value, "limits");
            return (Criteria) this;
        }

        public Criteria andLimitsNotLike(String value) {
            addCriterion("limits not like", value, "limits");
            return (Criteria) this;
        }

        public Criteria andLimitsIn(List<String> values) {
            addCriterion("limits in", values, "limits");
            return (Criteria) this;
        }

        public Criteria andLimitsNotIn(List<String> values) {
            addCriterion("limits not in", values, "limits");
            return (Criteria) this;
        }

        public Criteria andLimitsBetween(String value1, String value2) {
            addCriterion("limits between", value1, value2, "limits");
            return (Criteria) this;
        }

        public Criteria andLimitsNotBetween(String value1, String value2) {
            addCriterion("limits not between", value1, value2, "limits");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria addConditionSql(String conditionSql) {
            addCriterion(conditionSql);
            return (Criteria) this;
        }
    }

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