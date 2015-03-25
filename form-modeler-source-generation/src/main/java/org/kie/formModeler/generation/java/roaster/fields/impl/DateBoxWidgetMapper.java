package org.kie.formModeler.generation.java.roaster.fields.impl;

import org.kie.formModeler.common.model.FieldDefinition;
import org.kie.formModeler.common.model.impl.DateBoxFieldDefinition;
import org.kie.formModeler.generation.java.roaster.fields.FieldWidgetMapper;

/**
 * Created by pefernan on 3/24/15.
 */
public class DateBoxWidgetMapper implements FieldWidgetMapper {

    @Override
    public String getSupportedFieldDefinition() {
        return DateBoxFieldDefinition.class.getName();
    }

    @Override
    public String getWidgetClassName( FieldDefinition definition ) {
        return "com.github.gwtbootstrap.datepicker.client.ui.DateBox";
    }
}
