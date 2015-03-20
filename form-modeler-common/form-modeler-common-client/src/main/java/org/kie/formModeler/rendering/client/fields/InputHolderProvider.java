package org.kie.formModeler.rendering.client.fields;

import org.kie.formModeler.common.model.FieldDefinition;

public interface InputHolderProvider {
    public abstract String getSupportedFieldDefinition();
    public abstract InputHolder getInputBuilder( FieldDefinition definition );
}
