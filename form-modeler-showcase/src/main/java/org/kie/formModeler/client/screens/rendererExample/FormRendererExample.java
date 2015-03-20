/**
 * Copyright (C) 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.formModeler.client.screens.rendererExample;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.CheckBox;
import com.github.gwtbootstrap.client.ui.Form;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.github.gwtbootstrap.client.ui.constants.FormType;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.kie.formModeler.common.model.FieldDefinition;
import org.kie.formModeler.common.model.FormDefinition;
import org.kie.formModeler.common.model.impl.DateBoxFieldDefinition;
import org.kie.formModeler.common.model.impl.IntBoxFieldDefinition;
import org.kie.formModeler.common.model.impl.TextBoxFieldDefinition;
import org.kie.formModeler.examples.model.user.User;
import org.kie.formModeler.examples.service.user.UserService;
import org.kie.formModeler.rendering.client.renderer.FormRenderer;

@Dependent
@Templated
public class FormRendererExample extends Composite {

    @Inject
    @DataField
    private FlowPanel content;

    @Inject
    @DataField
    private CheckBox name;

    @Inject
    @DataField
    private CheckBox surname;

    @Inject
    @DataField
    private CheckBox birthday;

    @Inject
    @DataField
    private CheckBox age;

    @Inject
    @DataField
    private CheckBox street;

    @Inject
    @DataField
    private CheckBox num;

    @Inject
    @DataField
    private CheckBox cp;

    @Inject
    @DataField
    private CheckBox city;

    @Inject
    @DataField
    private Button reset;


    @Inject
    private FormRenderer renderer;

    @Inject
    private Caller<UserService> userService;

    private User user;
    private FormDefinition formDefinition;

    @PostConstruct
    void doLayout() {
        reset.setType( ButtonType.PRIMARY );
        initData();
    }

    protected void initData() {
        userService.call( new RemoteCallback<User>() {
            @Override
            public void callback( User o ) {
                content.add( renderer );
                formDefinition = new FormDefinition( "example" );
                user = o;
            }
        } ).getUser();
    }

    @EventHandler("reset")
    public void reset( ClickEvent event ) {
        name.setValue( false );
        surname.setValue( false );
        birthday.setValue( false );
        age.setValue( false );
        street.setValue( false );
        num.setValue( false );
        cp.setValue( false );
        city.setValue( false );

        content.clear();
        renderer.reset();
        initData();
    }

    protected void addFieldDefinition( FieldDefinition definition ) {
        formDefinition.addField( definition );
        renderer.loadForm( formDefinition, user );
    }

    protected void removeField( String fieldId ) {
        formDefinition.removeField( fieldId );
        renderer.loadForm( formDefinition, user );
    }

    @EventHandler("name")
    protected void selectName( ChangeEvent event ) {
        if (name.getValue().equals( Boolean.TRUE )) {
            TextBoxFieldDefinition definition = new TextBoxFieldDefinition();
            definition.setName( "name" );
            definition.setLabel( "User Name:" );
            definition.setBindingExpression( "name" );
            addFieldDefinition( definition );
        } else {
            removeField( "name" );
        }
    }

    @EventHandler("surname")
    protected void selectSurname( ChangeEvent event ) {
        if (surname.getValue().equals( Boolean.TRUE )) {
            TextBoxFieldDefinition definition = new TextBoxFieldDefinition();
            definition.setName( "surname" );
            definition.setLabel( "User Surname:" );
            definition.setBindingExpression( "surname" );
            definition.setSize( 50 );
            addFieldDefinition( definition );
        } else {
            removeField( "surname" );
        }
    }

    @EventHandler("birthday")
    protected void selectBirthDay( ChangeEvent event ) {
        if (birthday.getValue().equals( Boolean.TRUE )) {
            DateBoxFieldDefinition definition = new DateBoxFieldDefinition();
            definition.setName( "birthday" );
            definition.setLabel( "User Birthday:" );
            definition.setBindingExpression( "birthday" );
            addFieldDefinition( definition );
        } else {
            removeField( "birthday" );
        }
    }

    @EventHandler("age")
    protected void selectAge( ChangeEvent event ) {
        if (age.getValue().equals( Boolean.TRUE )) {
            IntBoxFieldDefinition definition = new IntBoxFieldDefinition();
            definition.setName( "age" );
            definition.setLabel( "User Age:" );
            definition.setBindingExpression( "age" );
            definition.setSize( 4 );
            definition.setMaxLength( 2 );
            addFieldDefinition( definition );
        } else {
            removeField( "age" );
        }
    }

    @EventHandler("street")
    protected void selectStreet( ChangeEvent event ) {
        if (street.getValue().equals( Boolean.TRUE )) {
            TextBoxFieldDefinition definition = new TextBoxFieldDefinition();
            definition.setName( "address.street" );
            definition.setLabel( "Street Name:" );
            definition.setBindingExpression( "address.street" );
            definition.setSize( 50 );
            addFieldDefinition( definition );
        } else {
            removeField( "address.street" );
        }
    }

    @EventHandler("num")
    protected void selectNumber( ChangeEvent event ) {
        if (num.getValue().equals( Boolean.TRUE )) {
            IntBoxFieldDefinition definition = new IntBoxFieldDefinition();
            definition.setName( "address.num" );
            definition.setLabel( "Street Number:" );
            definition.setBindingExpression( "address.num" );
            definition.setSize( 5 );
            definition.setMaxLength( 3 );
            addFieldDefinition( definition );
        } else {
            removeField( "address.num" );
        }
    }

    @EventHandler("cp")
    protected void selectCP( ChangeEvent event ) {
        if (cp.getValue().equals( Boolean.TRUE )) {
            TextBoxFieldDefinition definition = new TextBoxFieldDefinition();
            definition.setName( "address.cp" );
            definition.setLabel( "CP:" );
            definition.setBindingExpression( "address.cp" );
            definition.setSize( 7 );
            definition.setMaxLength( 5 );
            addFieldDefinition( definition );
        } else {
            removeField( "address.cp" );
        }
    }

    @EventHandler("city")
    protected void selectCity( ChangeEvent event ) {
        if (city.getValue().equals( Boolean.TRUE )) {
            TextBoxFieldDefinition definition = new TextBoxFieldDefinition();
            definition.setName( "address.city" );
            definition.setLabel( "City:" );
            definition.setBindingExpression( "address.city" );
            addFieldDefinition( definition );
        } else {
            removeField( "address.city" );
        }
    }
}
