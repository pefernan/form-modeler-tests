package org.kie.formModeler.rendering.client.fields.impl.dateBox;

import com.github.gwtbootstrap.datepicker.client.ui.DateBox;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.kie.formModeler.common.model.FieldDefinition;
import org.kie.formModeler.common.model.impl.DateBoxFieldDefinition;
import org.kie.formModeler.rendering.client.fields.impl.AbstractInputHolder;

/**
 * Created by pefernan on 3/20/15.
 */
public class DateBoxHolder extends AbstractInputHolder {

    public DateBoxHolder( FieldDefinition definition ) {
        super( definition );
    }

    @Override
    public void setReadOnly( boolean readOnly ) {
        ((DateBox)fieldInput).setReadOnly( readOnly );
    }

    @Override
    public String getSupportedFieldDefinition() {
        return DateBoxFieldDefinition.class.getName();
    }

    @Override
    public Widget createInputWidget( FieldDefinition definition ) {
        DateBoxFieldDefinition fd = ( DateBoxFieldDefinition ) definition;
        DateBox dateBox = new DateBox();
        dateBox.setId( definition.getName() );

        dateBox.setWidth( fd.getSize() + "em" );

        return dateBox;
    }
}
