package org.netbeans.gpx.editor.binding.converter;

import com.topografix.gpx.model.Email;
import org.jdesktop.beansbinding.Converter;

/**
 * Converter between {@link Email} and String.
 * @author msc
 */
public class EmailConverter extends Converter<Email, String> {

    @Override
    public String convertForward(Email value) {
        
        return value.toString();
    }

    @Override
    public Email convertReverse(String value) {
        
        String[] parts = value.split("@");
        Email email = new Email();
        email.setId(parts[0]);
        email.setDomain(parts[1]);
        return email;
    }
    
}
