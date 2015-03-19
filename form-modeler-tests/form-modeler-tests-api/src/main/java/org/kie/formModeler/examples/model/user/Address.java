package org.kie.formModeler.examples.model.user;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;

@Bindable
@Portable
public class Address {
    @NotEmpty
    private String street;

    @Min( 1 )
    @Max( 200 )
    private int num;

    @NotEmpty
    private String cp;

    @NotEmpty
    private String city;

    public String getStreet() {
        return street;
    }

    public void setStreet( String street ) {
        this.street = street;
    }

    public int getNum() {
        return num;
    }

    public void setNum( int num ) {
        this.num = num;
    }

    public String getCp() {
        return cp;
    }

    public void setCp( String cp ) {
        this.cp = cp;
    }

    public String getCity() {
        return city;
    }

    public void setCity( String city ) {
        this.city = city;
    }
}
