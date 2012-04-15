package org.netbeans.gpx.editor.binding.converter;

import java.math.BigDecimal;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author msc
 */
public class BigDecimalConverter extends Converter<BigDecimal, String> {

    @Override
    public String convertForward(BigDecimal value) {
        String stringVal = null;
        if (value != null) {
            stringVal = value.toString();
        }
        return stringVal;
    }

    @Override
    public BigDecimal convertReverse(String value) {
        BigDecimal decVal = null;
        if (value != null && !value.isEmpty()) {
            decVal = new BigDecimal(value);
        }
        return decVal;
    }
}
