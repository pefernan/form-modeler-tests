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
import org.kie.formModeler.examples.model.user.User;
import org.kie.formModeler.examples.service.user.UserService;

@Templated
public class UserExample extends Composite {

    @Inject
    private Caller<UserService> userService;

    private DataBinder<User> binder;

    private User model;

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
    private TextBox street;

    @Inject
    @DataField
    private IntegerBox num;

    @Inject
    @DataField
    private TextBox cp;

    @Inject
    @DataField
    private TextBox city;

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

        num.setSize( 5 );
        num.setMaxLength( 3 );

        cp.setMaxLength( 5 );
        cp.setSize( 7 );
    }

    @EventHandler("start")
    public void start(ClickEvent clickEvent) {
        userService.call( new RemoteCallback<User>() {
            @Override
            public void callback( User o ) {
                binder = DataBinder.forModel( o, InitialState.FROM_MODEL )
                        .bind( name, "name" )
                        .bind( surname, "surname" )
                        .bind( birthday, "birthday" )
                        .bind( age, "age" )
                        .bind( street, "address.street" )
                        .bind( num, "address.num" )
                        .bind( cp, "address.cp" )
                        .bind( city, "address.city" );

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
        Set<ConstraintViolation<User>> result = validator.validate( model );
        for (ConstraintViolation validation : result) {
            Window.alert(validation.getMessage());
            Window.alert( validation.getPropertyPath().toString() );
        }
    }
}
