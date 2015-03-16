package org.kie.formModeler.examples.client.users;

import javax.inject.Inject;

import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.datepicker.client.DatePicker;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.databinding.client.api.InitialState;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.kie.formModeler.examples.client.ExampleWidget;
import org.kie.formModeler.examples.service.user.UserService;

@Templated
public class UserExample extends Composite implements ExampleWidget {

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
    private DatePicker birthday;

    @Override
    public void startTest() {
        userService.call( new RemoteCallback<Object>() {
            @Override
            public void callback( Object o ) {
                binder = DataBinder.forModel( o, InitialState.FROM_MODEL )
                        .bind( name, "name" )
                        .bind( surname, "surname" )
                        .bind( birthday, "birthday" );
                model = binder.getModel();
            }
        } ).getUser();
    }
}
