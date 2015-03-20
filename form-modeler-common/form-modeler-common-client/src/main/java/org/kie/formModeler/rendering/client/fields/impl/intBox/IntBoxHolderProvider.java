package org.kie.formModeler.rendering.client.fields.impl.intBox;

import javax.enterprise.context.Dependent;

import org.kie.formModeler.common.model.FieldDefinition;
import org.kie.formModeler.common.model.impl.IntBoxFieldDefinition;
import org.kie.formModeler.rendering.client.fields.InputHolder;
import org.kie.formModeler.rendering.client.fields.InputHolderProvider;

@Dependent
public class IntBoxHolderProvider implements InputHolderProvider {

    @Override
    public String getSupportedFieldDefinition() {
        return IntBoxFieldDefinition.class.getName();
    }

    @Override
    public InputHolder getInputBuilder( FieldDefinition definition ) {
        return new IntBoxHolder( definition );
    }
}
