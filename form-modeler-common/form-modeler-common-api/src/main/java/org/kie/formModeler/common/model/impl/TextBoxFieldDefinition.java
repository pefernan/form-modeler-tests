package org.kie.formModeler.common.model.impl;

import org.kie.formModeler.common.model.FieldDefinition;

/**
 * Created by pefernan on 3/19/15.
 */
public class TextBoxFieldDefinition extends FieldDefinition<String> {

    protected Integer size = 15;
    protected Integer maxLength = 100;

    @Override
    public String getType() {
        return String.class.getName();
    }

    public Integer getSize() {
        return size;
    }

    public void setSize( Integer size ) {
        this.size = size;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength( Integer maxLength ) {
        this.maxLength = maxLength;
    }
}