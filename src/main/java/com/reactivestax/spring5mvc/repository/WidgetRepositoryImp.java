package com.reactivestax.spring5mvc.repository;

import com.reactivestax.spring5mvc.model.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class WidgetRepositoryImp implements WidgetRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    KeyHolder keyHolder = new GeneratedKeyHolder();

    @Override
    public Widget saveWidget(Widget widget) {

        String query = "insert into widget_details (name, description) value (?,?)";

        int i = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, widget.getName());
                ps.setString(2, widget.getDescription());
                return ps;
            }
        });
        int widgetId = keyHolder.getKey().intValue();
        widget.setId((long) widgetId);
        return widget;
    }
}
