package org.kie.formModeler.examples.service.user;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.enterprise.context.ApplicationScoped;

import org.jboss.errai.bus.server.annotations.Service;
import org.kie.formModeler.examples.model.user.Address;
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

        Calendar birth = Calendar.getInstance();

        birth.set( Calendar.DAY_OF_MONTH, 24 );
        birth.set( Calendar.MONTH, Calendar.FEBRUARY );
        birth.set( Calendar.YEAR, 1981 );

        user.setBirthday( birth.getTime() );

        user.setAge( Calendar.getInstance().get( Calendar.YEAR ) - birth.get( Calendar.YEAR ) );

        Address address = new Address();

        address.setStreet( "Passeig de Gracia" );
        address.setNum( 120 );
        address.setCp( "08008" );
        address.setCity( "Barcelona" );

        user.setAddress( address );

        return user;
    }
}
