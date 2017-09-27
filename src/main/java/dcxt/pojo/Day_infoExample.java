package dcxt.pojo;

import java.util.ArrayList;
import java.util.List;

public class Day_infoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public Day_infoExample() {
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

        public Criteria andDateIsNull() {
            addCriterion("date_ is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("date_ is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(String value) {
            addCriterion("date_ =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(String value) {
            addCriterion("date_ <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(String value) {
            addCriterion("date_ >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(String value) {
            addCriterion("date_ >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(String value) {
            addCriterion("date_ <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(String value) {
            addCriterion("date_ <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLike(String value) {
            addCriterion("date_ like", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotLike(String value) {
            addCriterion("date_ not like", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<String> values) {
            addCriterion("date_ in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<String> values) {
            addCriterion("date_ not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(String value1, String value2) {
            addCriterion("date_ between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(String value1, String value2) {
            addCriterion("date_ not between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andPTotalIsNull() {
            addCriterion("p_total is null");
            return (Criteria) this;
        }

        public Criteria andPTotalIsNotNull() {
            addCriterion("p_total is not null");
            return (Criteria) this;
        }

        public Criteria andPTotalEqualTo(Integer value) {
            addCriterion("p_total =", value, "pTotal");
            return (Criteria) this;
        }

        public Criteria andPTotalNotEqualTo(Integer value) {
            addCriterion("p_total <>", value, "pTotal");
            return (Criteria) this;
        }

        public Criteria andPTotalGreaterThan(Integer value) {
            addCriterion("p_total >", value, "pTotal");
            return (Criteria) this;
        }

        public Criteria andPTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("p_total >=", value, "pTotal");
            return (Criteria) this;
        }

        public Criteria andPTotalLessThan(Integer value) {
            addCriterion("p_total <", value, "pTotal");
            return (Criteria) this;
        }

        public Criteria andPTotalLessThanOrEqualTo(Integer value) {
            addCriterion("p_total <=", value, "pTotal");
            return (Criteria) this;
        }

        public Criteria andPTotalIn(List<Integer> values) {
            addCriterion("p_total in", values, "pTotal");
            return (Criteria) this;
        }

        public Criteria andPTotalNotIn(List<Integer> values) {
            addCriterion("p_total not in", values, "pTotal");
            return (Criteria) this;
        }

        public Criteria andPTotalBetween(Integer value1, Integer value2) {
            addCriterion("p_total between", value1, value2, "pTotal");
            return (Criteria) this;
        }

        public Criteria andPTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("p_total not between", value1, value2, "pTotal");
            return (Criteria) this;
        }

        public Criteria andOTotalIsNull() {
            addCriterion("o_total is null");
            return (Criteria) this;
        }

        public Criteria andOTotalIsNotNull() {
            addCriterion("o_total is not null");
            return (Criteria) this;
        }

        public Criteria andOTotalEqualTo(Integer value) {
            addCriterion("o_total =", value, "oTotal");
            return (Criteria) this;
        }

        public Criteria andOTotalNotEqualTo(Integer value) {
            addCriterion("o_total <>", value, "oTotal");
            return (Criteria) this;
        }

        public Criteria andOTotalGreaterThan(Integer value) {
            addCriterion("o_total >", value, "oTotal");
            return (Criteria) this;
        }

        public Criteria andOTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("o_total >=", value, "oTotal");
            return (Criteria) this;
        }

        public Criteria andOTotalLessThan(Integer value) {
            addCriterion("o_total <", value, "oTotal");
            return (Criteria) this;
        }

        public Criteria andOTotalLessThanOrEqualTo(Integer value) {
            addCriterion("o_total <=", value, "oTotal");
            return (Criteria) this;
        }

        public Criteria andOTotalIn(List<Integer> values) {
            addCriterion("o_total in", values, "oTotal");
            return (Criteria) this;
        }

        public Criteria andOTotalNotIn(List<Integer> values) {
            addCriterion("o_total not in", values, "oTotal");
            return (Criteria) this;
        }

        public Criteria andOTotalBetween(Integer value1, Integer value2) {
            addCriterion("o_total between", value1, value2, "oTotal");
            return (Criteria) this;
        }

        public Criteria andOTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("o_total not between", value1, value2, "oTotal");
            return (Criteria) this;
        }

        public Criteria andPriceTotalIsNull() {
            addCriterion("price_total is null");
            return (Criteria) this;
        }

        public Criteria andPriceTotalIsNotNull() {
            addCriterion("price_total is not null");
            return (Criteria) this;
        }

        public Criteria andPriceTotalEqualTo(Integer value) {
            addCriterion("price_total =", value, "priceTotal");
            return (Criteria) this;
        }

        public Criteria andPriceTotalNotEqualTo(Integer value) {
            addCriterion("price_total <>", value, "priceTotal");
            return (Criteria) this;
        }

        public Criteria andPriceTotalGreaterThan(Integer value) {
            addCriterion("price_total >", value, "priceTotal");
            return (Criteria) this;
        }

        public Criteria andPriceTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("price_total >=", value, "priceTotal");
            return (Criteria) this;
        }

        public Criteria andPriceTotalLessThan(Integer value) {
            addCriterion("price_total <", value, "priceTotal");
            return (Criteria) this;
        }

        public Criteria andPriceTotalLessThanOrEqualTo(Integer value) {
            addCriterion("price_total <=", value, "priceTotal");
            return (Criteria) this;
        }

        public Criteria andPriceTotalIn(List<Integer> values) {
            addCriterion("price_total in", values, "priceTotal");
            return (Criteria) this;
        }

        public Criteria andPriceTotalNotIn(List<Integer> values) {
            addCriterion("price_total not in", values, "priceTotal");
            return (Criteria) this;
        }

        public Criteria andPriceTotalBetween(Integer value1, Integer value2) {
            addCriterion("price_total between", value1, value2, "priceTotal");
            return (Criteria) this;
        }

        public Criteria andPriceTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("price_total not between", value1, value2, "priceTotal");
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