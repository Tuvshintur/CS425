package com.smt.example.service.utilities;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Helper Service @author Turuu
 */

@Component
public class HelperService {

    public String getLocalDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

    public Date getDate(String value) {
        Date date = null;
        if (StringUtils.isNotBlank(value)) {
            DateFormat readFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = readFormat.parse(value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

}
