package br.com.LL.fileprocessor.converter.reader;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public interface LineToObjectMapper<T> {
    T convert(String line) throws ParseException;
    
    Map<Integer, Field> getFields();
    
    default String readString(String line, Field field) {
        return line.substring(field.getStart(), field.getEnd()).trim();
    }
    
    default long readNumber(String line, Field field) {
        return Long.parseLong(readString(line, field));
    }
    
    default BigDecimal readDecimal(String line, Field field) {
        return new BigDecimal(readString(line, field));
    }
    
    default Date readDate(String line, Field field) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
        
        return formatter.parse(readString(line, field));
    }
}
