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
package org.kie.formModeler.client.screens;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.github.gwtbootstrap.client.ui.Tab;
import com.github.gwtbootstrap.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.kie.formModeler.client.screens.rendererExample.FormRendererExample;
import org.kie.formModeler.examples.client.auto.Index;
import org.kie.formModeler.examples.client.users.UserExample;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchScreen;

@Dependent
@Templated
@WorkbenchScreen(identifier="HomeScreen")
public class HomeScreen extends Composite {

    @Inject
    @DataField
    private FlowPanel content;

    private TabPanel tabPanel;

    @Inject
    private UserExample userExample;

    @Inject
    private FormRendererExample rendererExample;

    @Inject
    private Index dynamicExample;

    @WorkbenchPartTitle
    public String getScreenTitle() {
        return "Welcome to Form Modeler";
    }

    @PostConstruct
    void doLayout() {
        tabPanel = new TabPanel(  );
        content.add( tabPanel );

        final Tab renderer = new Tab();
        renderer.setHeading( "Dynamic Form Renderer Test" );
        renderer.add( rendererExample );
        tabPanel.add( renderer );

        final Tab example = new Tab();
        example.setHeading( "Dynamic Generated Example" );
        example.add( dynamicExample );
        tabPanel.add( example );

        Tab user = new Tab();
        user.setHeading( "User" );
        user.add( userExample );
        tabPanel.add( user );

        tabPanel.selectTab( 0 );
    }
}
