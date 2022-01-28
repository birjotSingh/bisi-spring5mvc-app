package com.reactivestax.spring5mvc.repository;

import com.reactivestax.spring5mvc.model.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class WidgetRepositoryImp implements WidgetRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    KeyHolder keyHolder = new GeneratedKeyHolder();

    @Override
    public Widget saveWidget(Widget widget) {

        String query = "insert into widget_details (name, description) value (?,?)";

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, widget.getName());
                ps.setString(2, widget.getDescription());
                return ps;
            }
        }, keyHolder);


        Optional<Number> id =  Optional.ofNullable(keyHolder.getKey());
        if(!id.isEmpty()){
            widget.setId(Long.parseLong(String.valueOf(id.get())));
        }

        return widget;
    }

    @Override
    public Widget findWidgetById(Integer id) {
        String query = "select id, Name, Description from widget_details where id = ?";
       return jdbcTemplate.queryForObject(query, new Object[]{id}, new BeanPropertyRowMapper<>(Widget.class));
    }

    @Override
    public void deleteWidgetById(Integer id) {
        String query = "delete from widget_details where id =?";
        jdbcTemplate.update(query, id);
    }

    @Override
    public List<Widget> findAllWidgets() {
        String query = "select * from widget_details";
        List<Widget> widgetList = new ArrayList<>();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(query);

        for (Map<String, Object> widgetRow : maps) {
            Widget widget = new Widget();
            widget.setId((long) Integer.parseInt(String.valueOf(widgetRow.get("id"))));
            widget.setName(String.valueOf(widgetRow.get("name")));
            widget.setDescription(String.valueOf(widgetRow.get("description")));
            widgetList.add(widget);
        }
        return widgetList;
    }

    @Override
    public void updateWidget(Widget widget) {
        String query = "update widget_details set name=?, description=? where id=?";
        Object[] args = new Object[]{widget.getName(), widget.getDescription(), widget.getId()};
        jdbcTemplate.update(query, args);
    }


}
