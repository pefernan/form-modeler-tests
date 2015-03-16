package org.kie.formModeler.examples.service.user;

import org.jboss.errai.bus.server.annotations.Remote;

@Remote
public interface UserService {
    Object getUser();
}
