package org.kie.formModeler.examples.service.user;

import org.jboss.errai.bus.server.annotations.Remote;
import org.kie.formModeler.examples.model.user.User;

@Remote
public interface UserService {
    User getUser();
}
