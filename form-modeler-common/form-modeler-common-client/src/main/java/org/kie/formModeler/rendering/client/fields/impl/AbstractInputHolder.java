package org.kie.formModeler.rendering.client.fields.impl;

import com.github.gwtbootstrap.client.ui.ControlGroup;
import com.github.gwtbootstrap.client.ui.ControlLabel;
import com.github.gwtbootstrap.client.ui.Controls;
import com.github.gwtbootstrap.client.ui.FormLabel;
import com.github.gwtbootstrap.client.ui.HelpBlock;
import com.github.gwtbootstrap.client.ui.constants.ControlGroupType;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.kie.formModeler.common.model.FieldDefinition;
import org.kie.formModeler.rendering.client.fields.InputHolder;

public abstract class AbstractInputHolder implements InputHolder {
    protected FieldDefinition fieldDefinition;
    protected FormLabel inputLabel;
    protected Widget fieldInput;
    protected HelpBlock helpBlock = new HelpBlock();
    protected ControlGroup controlGroup = new ControlGroup();
    protected boolean wrong = false;

    protected AbstractInputHolder( FieldDefinition definition ) {
        fieldDefinition = definition;
        controlGroup = new ControlGroup();

        ControlLabel controlLabel = new ControlLabel();
        inputLabel = new FormLabel(fieldDefinition.getLabel());
        inputLabel.setFor(fieldDefinition.getName());
        controlLabel.add(inputLabel);

        controlGroup.add(controlLabel);

        Controls controls = new Controls();
        fieldInput = createInputWidget(definition);
        controls.add(fieldInput);
        controls.add(helpBlock);
        controlGroup.add(controls);
    }

    @Override
    public String getFieldName() {
        return fieldDefinition.getName();
    }

    @Override
    public void setWrong( boolean wrong ) {
        if (wrong) controlGroup.setType( ControlGroupType.ERROR );
        else controlGroup.setType( ControlGroupType.NONE );
        this.wrong = wrong;
    }

    @Override
    public boolean isWrong() {
        return wrong;
    }

    @Override
    public void setHelpMessage( String helpMessage ) {
        helpBlock.setText( helpMessage );
    }

    @Override
    public void bindInput( DataBinder binder ) {
        binder.bind( fieldInput, fieldDefinition.getBindingExpression() );
    }

    @Override
    public Widget getInputContainer() {
        return controlGroup;
    }

    protected abstract Widget createInputWidget( FieldDefinition definition );
}
