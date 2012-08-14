package org.netbeans.gpx.editor.binding.converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author msc
 */
public class BigIntegerConverter extends Converter<BigInteger, String> {

    @Override
    public String convertForward(BigInteger value) {
        String stringVal = null;
        if (value != null) {
            stringVal = value.toString();
        }
        return stringVal;
    }

    @Override
    public BigInteger convertReverse(String value) {
        BigInteger decVal = null;
        if (value != null && !value.isEmpty()) {
            decVal = new BigInteger(value);
        }
        return decVal;
    }
}
