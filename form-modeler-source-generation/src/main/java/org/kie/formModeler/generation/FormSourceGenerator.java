package org.kie.formModeler.generation;

import org.kie.formModeler.common.model.FormDefinition;

/**
 * Created by pefernan on 3/24/15.
 */
public interface FormSourceGenerator {

   String generateSource( FormDefinition definition );

}
