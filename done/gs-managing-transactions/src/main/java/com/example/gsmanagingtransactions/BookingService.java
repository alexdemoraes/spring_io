package com.example.gsmanagingtransactions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class BookingService {

    private final static Logger logger = LoggerFactory.getLogger(BookingService.class);

    private final JdbcTemplate jdbcTemplate;

    public BookingService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void book(String... persons) {
        for (String person : persons) {
            logger.info(String.format("Booking %s in a seat...", person));
            jdbcTemplate.update("Insert into Bookings(First_Name) values (?)", person);
        }
    }

    public List<String> findAllBookings() {
        return jdbcTemplate.query("select First_Name from Bookings",
                (rs, rowNum) -> rs.getString("First_name"));
    }
}
