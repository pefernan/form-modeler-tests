package org.kie.formModeler.examples.client.auto;

import java.util.Set;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;

import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.kie.formModeler.examples.model.user.User;
import org.kie.formModeler.examples.service.user.UserService;

/**
 * Created by pefernan on 3/24/15.
 */
@Templated
public class Index extends Composite {

    @Inject
    private Caller<UserService> userService;

    @Inject
    private Example example;

    @Inject
    @DataField
    private SimplePanel content;

    @Inject
    @DataField
    private Button load;

    @Inject
    @DataField
    private Button validate;

    @PostConstruct
    protected void init() {
        content.add( example );
        validate.setEnabled( false );
    }

    @EventHandler("load")
    public void load(ClickEvent clickEvent) {
        userService.call( new RemoteCallback<User>() {
            @Override
            public void callback( User o ) {
                example.loadModel( o );
                validate.setEnabled( true );
            }
        } ).getUser();
    }

    @EventHandler("validate")
    public void validate(ClickEvent clickEvent) {
        for (String fieldId : example.getFieldIds()) {
            Element group = Document.get().getElementById( fieldId + "_control_group" );
            Element helpBlock = Document.get().getElementById( fieldId + "_help_block" );
            if ( group != null ) group.removeClassName( "error" );
            if ( helpBlock != null ) helpBlock.setInnerHTML( "" );
        }

        Set<ConstraintViolation<Object>> result = example.validate();
        for (ConstraintViolation validation : result) {
            Element group = Document.get().getElementById( validation.getPropertyPath().toString() + "_control_group" );
            Element helpBlock = Document.get().getElementById( validation.getPropertyPath().toString() + "_help_block" );
            if ( group != null ) group.addClassName( "error" );
            if ( helpBlock != null ) helpBlock.setInnerHTML( validation.getMessage() );
        }
    }
}
