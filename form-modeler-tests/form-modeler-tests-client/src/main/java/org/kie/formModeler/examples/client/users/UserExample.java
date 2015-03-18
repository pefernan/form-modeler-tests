package org.kie.formModeler.examples.client.users;

import java.text.DateFormat;
import java.util.Date;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.IntegerBox;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.github.gwtbootstrap.datepicker.client.ui.DateBox;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.databinding.client.api.InitialState;
import org.jboss.errai.databinding.client.api.PropertyChangeEvent;
import org.jboss.errai.databinding.client.api.PropertyChangeHandler;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.kie.formModeler.examples.service.user.UserService;

@Templated
public class UserExample extends Composite {

    @Inject
    private Caller<UserService> userService;

    private DataBinder binder;

    private Object model;

    @Inject
    @DataField
    private TextBox name;

    @Inject
    @DataField
    private TextBox surname;

    @Inject
    @DataField
    private DateBox birthday;

    @Inject
    @DataField
    private IntegerBox age;

    @Inject
    @DataField
    private Button validate;

    @Inject
    @DataField
    private Button start;

    @Inject
    private Validator validator;

    @PostConstruct
    protected void init() {
        start.setText( "Load Data" );
        start.setType( ButtonType.PRIMARY );

        validate.setText( "Validate values" );
        validate.setEnabled( false );
        validate.setType( ButtonType.PRIMARY );
    }

    @EventHandler("start")
    public void start(ClickEvent clickEvent) {
        userService.call( new RemoteCallback<Object>() {
            @Override
            public void callback( Object o ) {
                binder = DataBinder.forModel( o, InitialState.FROM_MODEL )
                        .bind( name, "name" )
                        .bind( surname, "surname" )
                        .bind( birthday, "birthday" )
                        .bind( age, "age" );

                binder.addPropertyChangeHandler(new PropertyChangeHandler() {

                    @Override
                    public void onPropertyChange(PropertyChangeEvent event) {
                        if (event.getPropertyName().equals( "birthday" )) {
                            if (event.getNewValue() != null) {
                                Date currentDate = new Date();
                                Date newDate = ( Date ) event.getNewValue();
                                int newAge = currentDate.getYear() - newDate.getYear();
                                UserExample.this.age.setValue( newAge );
                                ValueChangeEvent.fire( UserExample.this.age, newAge );
                            }
                        }
                    }
                });
                model = binder.getModel();
                validate.setEnabled( true );
            }
        } ).getUser();
    }

    @EventHandler("validate")
    public void validate(ClickEvent clickEvent) {
        Set<ConstraintViolation<Object>> result = validator.validate( model );
        for (ConstraintViolation validation : result) {
            Window.alert(validation.getMessage());
            Window.alert( validation.getPropertyPath().toString() );
        }
    }
}
