package org.kie.formModeler.rendering.client.fields;

import com.google.gwt.user.client.ui.Widget;
import org.jboss.errai.databinding.client.api.DataBinder;

public interface InputHolder {
    public abstract String getFieldName();
    public abstract void setHelpMessage(String helpMessage);
    public abstract void setReadOnly( boolean readOnly );
    public abstract String getSupportedFieldDefinition();
    public void bindInput(DataBinder binder);
    public Widget getInputContainer();
    public abstract void setWrong( boolean wrong );
    public boolean isWrong();
}
