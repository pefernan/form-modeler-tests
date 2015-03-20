package org.kie.formModeler.rendering.client.fields.impl.textBox;

import javax.enterprise.context.Dependent;

import org.kie.formModeler.common.model.FieldDefinition;
import org.kie.formModeler.common.model.impl.TextBoxFieldDefinition;
import org.kie.formModeler.rendering.client.fields.InputHolder;
import org.kie.formModeler.rendering.client.fields.InputHolderProvider;

@Dependent
public class TextBoxHolderProvider implements InputHolderProvider {

    @Override
    public String getSupportedFieldDefinition() {
        return TextBoxFieldDefinition.class.getName();
    }

    @Override
    public InputHolder getInputBuilder( FieldDefinition definition ) {
        return new TextBoxHolder( definition );
    }
}
