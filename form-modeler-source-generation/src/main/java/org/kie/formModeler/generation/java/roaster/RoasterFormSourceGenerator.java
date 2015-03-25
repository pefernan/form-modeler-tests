package org.kie.formModeler.generation.java.roaster;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Validator;

import org.apache.commons.lang.WordUtils;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.kie.formModeler.common.model.FieldDefinition;
import org.kie.formModeler.common.model.FormDefinition;
import org.kie.formModeler.generation.java.JavaFormSourceGenerator;
import org.kie.formModeler.generation.java.roaster.fields.FieldWidgetMapper;
import org.kie.formModeler.generation.java.roaster.fields.FieldWidgetMapperManager;

/**
 * Created by pefernan on 3/24/15.
 */

@Dependent
public class RoasterFormSourceGenerator extends JavaFormSourceGenerator {

    @Inject
    private FieldWidgetMapperManager widgetMapperManager;

    @Override
    public String generateSource( FormDefinition definition ) {
        JavaClassSource javaClass = Roaster.create( JavaClassSource.class );
        javaClass.setPackage("org.kie.formModeler.examples.client.auto").setName( WordUtils.capitalize( definition.getName() ) );
        javaClass.setSuperType( "org.kie.formModeler.rendering.client.rendering.FormView" );
        javaClass.addImport( DataBinder.class );
        javaClass.addImport( Validator.class );

        javaClass.addAnnotation( Named.class ).setStringValue( WordUtils.capitalize( definition.getName() ) );
        javaClass.addAnnotation( Templated.class );

        StringBuffer bindMethod = new StringBuffer(  );

        StringBuffer fieldIds = new StringBuffer(  );

        for ( FieldDefinition field : definition.getFields() ) {
            FieldWidgetMapper mapper = widgetMapperManager.getMapperForFieldDefinition( field );

            if ( mapper != null ) {
                if ( fieldIds.length() != 0 ) fieldIds.append( "," );
                fieldIds.append( "\"" ).append( field.getName() ).append( "\"" );
                FieldSource<JavaClassSource> fieldSource = javaClass.addField();
                fieldSource.setName( field.getName() );
                fieldSource.setType( mapper.getWidgetClassName( field ) );
                fieldSource.setPrivate();
                fieldSource.addAnnotation( Inject.class );
                fieldSource.addAnnotation( DataField.class );
                bindMethod.append( "binder.bind( " + field.getName() + ", \"" + field.getBindingExpression() + "\" );\n" );
            }
        }

        fieldIds.insert( 0, "return new String[] {" );
        fieldIds.append( "};" );
        javaClass.addMethod()
                .setPublic()
                .setReturnType( String[].class )
                .setName( "getFieldIds" )
                .setBody( fieldIds.toString() )
                .addAnnotation( Override.class );


        javaClass.addMethod()
                .setProtected()
                .setReturnTypeVoid()
                .setName( "doBind" )
                .setBody( bindMethod.toString() )
                .addAnnotation( Override.class );;

        return javaClass.toString();
    }
}
