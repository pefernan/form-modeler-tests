package org.kie.formModeler.rendering.client.fields;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.errai.ioc.client.container.IOCBeanDef;
import org.jboss.errai.ioc.client.container.SyncBeanManager;

@ApplicationScoped
public class FieldManager {

    @Inject
    private SyncBeanManager iocManager;

    protected Map<String, InputHolderProvider> providersMap;

    @PostConstruct
    protected void init() {
        providersMap = new HashMap<String, InputHolderProvider>(  );
        Collection<IOCBeanDef<InputHolderProvider>> providers = iocManager.lookupBeans(InputHolderProvider.class);
        if (providers != null) {
            for (IOCBeanDef providerDef : providers) {
                InputHolderProvider provider = (InputHolderProvider ) providerDef.getInstance();
                providersMap.put(provider.getSupportedFieldDefinition(), provider);
            }
        }
    }

    public InputHolderProvider getProviderByType(String type) {
        return providersMap.get(type);
    }
}
