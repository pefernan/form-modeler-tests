package org.kie.formModeler.generation.java.roaster.fields;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.kie.formModeler.common.model.FieldDefinition;

/**
 * Created by pefernan on 3/24/15.
 */
public class FieldWidgetMapperManager {

    @Inject
    private Instance<FieldWidgetMapper> intances;

    protected Map<String, FieldWidgetMapper> mappers;

    @PostConstruct
    protected void init() {
        mappers = new HashMap<String, FieldWidgetMapper>(  );

        for (FieldWidgetMapper mapper : intances) {
            mappers.put( mapper.getSupportedFieldDefinition(), mapper );
        }
    }

    public FieldWidgetMapper getMapperForFieldDefinition (FieldDefinition definition) {
        return mappers.get( definition.getClass().getName() );
    }
}
