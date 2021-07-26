package com.mergetechng.jobs.commons.dto;

import com.mergetechng.jobs.commons.enums.FilterOperationEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class FilterCondition {

    private String field;
    private FilterOperationEnum operator;
    private Object value;

    public FilterCondition(String field, FilterOperationEnum operator, Object value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }


    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public FilterOperationEnum getOperator() {
        return operator;
    }

    public void setOperator(FilterOperationEnum operator) {
        this.operator = operator;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}