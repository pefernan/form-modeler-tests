package org.kie.formModeler.rendering.client.fields.impl.textBox;

import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import org.kie.formModeler.common.model.FieldDefinition;
import org.kie.formModeler.common.model.impl.TextBoxFieldDefinition;
import org.kie.formModeler.rendering.client.fields.impl.AbstractInputHolder;

public class TextBoxHolder extends AbstractInputHolder {

    public TextBoxHolder( FieldDefinition definition ) {
        super( definition );
    }

    @Override
    public void setReadOnly( boolean readOnly ) {
        ((TextBox)fieldInput).setReadOnly( readOnly );
    }

    @Override
    public String getSupportedFieldDefinition() {
        return TextBoxFieldDefinition.class.getName();
    }

    @Override
    public Widget createInputWidget( FieldDefinition definition ) {
        TextBoxFieldDefinition fd = ( TextBoxFieldDefinition ) definition;
        TextBox text = new TextBox();
        text.setName(definition.getName());
        text.setId(definition.getName());

        text.setMaxLength( fd.getMaxLength() );
        text.setWidth( fd.getSize() + "em" );

        return text;
    }
}
