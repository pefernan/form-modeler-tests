package org.kie.formModeler.examples.service.user;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.enterprise.context.ApplicationScoped;

import org.jboss.errai.bus.server.annotations.Service;
import org.kie.formModeler.examples.model.user.User;

@Service
@ApplicationScoped
public class UserServiceImpl implements UserService {
    private SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );

    @Override
    public User getUser() {
        User user = new User();
        user.setName( "Pere" );
        user.setSurname( "Fernandez" );
        Date dt;
        try {
            dt = sdf.parse( "24/02/1981" );
        } catch ( ParseException e ) {
            dt = new Date(  );
        }
        user.setBirthday( dt );

        user.setAge( new Date(  ).getYear() - dt.getYear() );

        return user;
    }
}
