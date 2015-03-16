package org.kie.formModeler.examples.service.user;

import java.util.Date;
import javax.enterprise.context.ApplicationScoped;

import org.jboss.errai.bus.server.annotations.Service;
import org.kie.formModeler.examples.model.user.User;

@Service
@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Override
    public Object getUser() {
        User user = new User();
        user.setName( "Pere" );
        user.setSurname( "Fernandez" );
        user.setBirthday( new Date(  ) );
        return user;
    }
}
