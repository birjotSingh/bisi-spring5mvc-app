package com.reactivestax.spring5mvc.repository;

import com.reactivestax.spring5mvc.model.Widget;

import java.util.List;

public interface WidgetRepo {
    public Widget saveWidget(Widget widget);
    public Widget findWidgetById(Integer id);
    public void deleteWidgetById(Integer id);
    public List<Widget> findAllWidgets();
    public void updateWidget(Widget widget);
}
