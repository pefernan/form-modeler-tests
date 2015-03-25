package org.kie.formModeler.generation.java.roaster.fields.impl;

import org.kie.formModeler.common.model.FieldDefinition;
import org.kie.formModeler.common.model.impl.DateBoxFieldDefinition;
import org.kie.formModeler.common.model.impl.TextBoxFieldDefinition;
import org.kie.formModeler.generation.java.roaster.fields.FieldWidgetMapper;

/**
 * Created by pefernan on 3/24/15.
 */
public class TextBoxWidgetMapper implements FieldWidgetMapper {

    @Override
    public String getSupportedFieldDefinition() {
        return TextBoxFieldDefinition.class.getName();
    }

    @Override
    public String getWidgetClassName( FieldDefinition definition ) {
        return "com.github.gwtbootstrap.client.ui.TextBox";
    }
}
