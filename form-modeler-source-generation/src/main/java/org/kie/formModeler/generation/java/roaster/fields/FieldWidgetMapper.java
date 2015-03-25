package org.kie.formModeler.generation.java.roaster.fields;

import org.kie.formModeler.common.model.FieldDefinition;

/**
 * Created by pefernan on 3/24/15.
 */
public interface FieldWidgetMapper {
    public String getSupportedFieldDefinition();
    public String getWidgetClassName( FieldDefinition definition );
}
