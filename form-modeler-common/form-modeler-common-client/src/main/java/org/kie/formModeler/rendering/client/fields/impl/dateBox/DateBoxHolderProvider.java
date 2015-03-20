package org.kie.formModeler.rendering.client.fields.impl.dateBox;

import javax.enterprise.context.Dependent;

import org.kie.formModeler.common.model.FieldDefinition;
import org.kie.formModeler.common.model.impl.DateBoxFieldDefinition;
import org.kie.formModeler.rendering.client.fields.InputHolder;
import org.kie.formModeler.rendering.client.fields.InputHolderProvider;

@Dependent
public class DateBoxHolderProvider implements InputHolderProvider {

    @Override
    public String getSupportedFieldDefinition() {
        return DateBoxFieldDefinition.class.getName();
    }

    @Override
    public InputHolder getInputBuilder( FieldDefinition definition ) {
        return new DateBoxHolder( definition );
    }
}
