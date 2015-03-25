package org.kie.formModeler.rendering.client.rendering;

import java.util.Set;
import java.util.TreeSet;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.google.gwt.user.client.ui.Composite;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.databinding.client.api.InitialState;

/**
 * Created by pefernan on 3/24/15.
 */
public abstract class FormView extends Composite {

    protected DataBinder binder;

    @Inject
    protected Validator validator;

    protected Object model;

    public void loadModel(Object model) {
        this.model = model;
        binder = DataBinder.forModel( model, InitialState.FROM_MODEL );
        doBind();
    }

    public Set<ConstraintViolation<Object>> validate() {
        if (model == null) return new TreeSet<ConstraintViolation<Object>>(  );
        return validator.validate( model );
    }

    public abstract String[] getFieldIds();

    protected abstract void doBind();
}
