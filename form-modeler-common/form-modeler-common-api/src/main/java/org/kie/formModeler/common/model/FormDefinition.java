package org.kie.formModeler.common.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jboss.errai.common.client.api.annotations.Portable;

@Portable
public class FormDefinition {
    private List<FieldDefinition> fields;

    private String name;

    public FormDefinition() {
        this.fields = new ArrayList<FieldDefinition>(  );
    }

    public FormDefinition( String name ) {
        this.name = name;
        this.fields = new ArrayList<FieldDefinition>(  );
    }

    public FormDefinition( String name, List<FieldDefinition> fields ) {
        this.name = name;
        this.fields = fields;
        this.fields = new ArrayList<FieldDefinition>(  );
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public List<FieldDefinition> getFields() {
        return fields;
    }

    public void setFields( List<FieldDefinition> fields ) {
        this.fields = fields;
    }

    public void addField( FieldDefinition field ) {
        this.fields.add( field );
    }

    public void removeField( String fieldId) {
        for (Iterator<FieldDefinition> it = fields.iterator(); it.hasNext();) {
            FieldDefinition definition = it.next();
            if (definition.getName().equals( fieldId ) ) {
                it.remove();
                return;
            }
        }
    }
}
