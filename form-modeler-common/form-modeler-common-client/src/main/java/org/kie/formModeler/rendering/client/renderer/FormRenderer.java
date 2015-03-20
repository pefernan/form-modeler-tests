package org.kie.formModeler.rendering.client.renderer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Form;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.github.gwtbootstrap.client.ui.constants.FormType;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.databinding.client.api.InitialState;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.kie.formModeler.common.model.FieldDefinition;
import org.kie.formModeler.common.model.FormDefinition;
import org.kie.formModeler.rendering.client.fields.FieldManager;
import org.kie.formModeler.rendering.client.fields.InputHolder;
import org.kie.formModeler.rendering.client.fields.InputHolderProvider;

@Templated
public class FormRenderer extends Composite {

    private DataBinder binder;

    private Object model;

    @Inject
    private Validator validator;

    @Inject
    private FieldManager fieldManager;

    private Map<String, InputHolder> inputs;

    @DataField
    protected Form form = new Form( );

    @Inject
    @DataField
    private Button validate;

    @PostConstruct
    protected void init() {
        inputs = new HashMap<String, InputHolder>(  );
        validate.setText( "Validate values" );
        validate.setEnabled( false );
        validate.setType( ButtonType.PRIMARY );
        form.setType( FormType.INLINE );
    }

    public void loadForm(FormDefinition formDefinition, Object model) {
        form.clear();
        inputs.clear();

        binder = DataBinder.forModel( model, InitialState.FROM_MODEL );

        VerticalPanel panel = new VerticalPanel();

        for ( FieldDefinition definition : formDefinition.getFields() ) {
            InputHolderProvider provider = fieldManager.getProviderByType( definition.getCode() );
            if (provider != null) {
                InputHolder holder = provider.getInputBuilder( definition );
                inputs.put( holder.getFieldName(), holder );
                panel.add( holder.getInputContainer() );
                holder.bindInput( binder );
            }
        }
        this.model = binder.getModel();

        validate.setEnabled( true );
        form.add( panel );
    }


    @EventHandler("validate")
    public void validate(ClickEvent clickEvent) {

        for (InputHolder holder : inputs.values()) {
            if (holder.isWrong()) {
                holder.setHelpMessage( "" );
                holder.setWrong( false );
            }
        }

        Set<ConstraintViolation<Object>> result = validator.validate( model );
        for (ConstraintViolation validation : result) {
            String fieldId = validation.getPropertyPath().toString();
            InputHolder holder = inputs.get( fieldId );

            if (holder != null) {
                holder.setWrong( true );
                holder.setHelpMessage( validation.getMessage() );
            }
        }
    }

    public void reset() {
        model = null;
        binder = null;
        validate.setEnabled( false );
        inputs.clear();
        form.clear();
    }
}
