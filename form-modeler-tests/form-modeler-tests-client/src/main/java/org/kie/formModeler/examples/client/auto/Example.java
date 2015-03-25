package org.kie.formModeler.examples.client.auto;

import org.kie.formModeler.rendering.client.rendering.FormView;
import org.jboss.errai.databinding.client.api.DataBinder;
import javax.validation.Validator;
import javax.inject.Named;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import com.github.gwtbootstrap.client.ui.TextBox;
import javax.inject.Inject;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import com.github.gwtbootstrap.datepicker.client.ui.DateBox;
import com.github.gwtbootstrap.client.ui.IntegerBox;
import java.lang.Override;

@Named("Example")
@Templated
public class Example extends FormView
{

    @Inject
    @DataField
    private TextBox name;
    @Inject
    @DataField
    private TextBox surname;
    @Inject
    @DataField
    private DateBox birthday;
    @Inject
    @DataField
    private IntegerBox age;

    @Override
    public String[] getFieldIds()
    {
        return new String[] { "name", "surname", "birthday", "age" };
    }

    @Override
    protected void doBind()
    {
        binder.bind(name, "name");
        binder.bind(surname, "surname");
        binder.bind(birthday, "birthday");
        binder.bind(age, "age");
    }
}