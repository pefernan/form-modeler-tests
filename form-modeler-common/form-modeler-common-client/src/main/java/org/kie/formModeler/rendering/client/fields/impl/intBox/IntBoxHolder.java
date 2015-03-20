package org.kie.formModeler.rendering.client.fields.impl.intBox;

import com.github.gwtbootstrap.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Widget;
import org.kie.formModeler.common.model.FieldDefinition;
import org.kie.formModeler.common.model.impl.IntBoxFieldDefinition;
import org.kie.formModeler.rendering.client.fields.impl.AbstractInputHolder;

/**
 * Created by pefernan on 3/20/15.
 */
public class IntBoxHolder extends AbstractInputHolder {

    public IntBoxHolder( FieldDefinition definition ) {
        super( definition );
    }

    @Override
    public void setReadOnly( boolean readOnly ) {
        ((IntegerBox)fieldInput).setReadOnly( readOnly );
    }

    @Override
    public String getSupportedFieldDefinition() {
        return IntBoxFieldDefinition.class.getName();
    }

    @Override
    public Widget createInputWidget( FieldDefinition definition ) {
        IntBoxFieldDefinition fd = ( IntBoxFieldDefinition ) definition;
        IntegerBox intBox = new IntegerBox();
        intBox.setName( definition.getName() );
        intBox.setId( definition.getName() );

        intBox.setMaxLength( fd.getMaxLength() );
        intBox.setWidth( fd.getSize() + "em" );

        return intBox;
    }
}
