package org.kie.formModeler.shared.sourceGeneration;

import org.jboss.errai.bus.server.annotations.Remote;
import org.kie.formModeler.common.model.FormDefinition;

/**
 * Created by pefernan on 3/24/15.
 */
@Remote
public interface FormSourceCreator {

    void createFormSource( FormDefinition definition, String htmlTemplate );
}
