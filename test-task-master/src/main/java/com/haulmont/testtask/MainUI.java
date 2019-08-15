package com.haulmont.testtask;

import com.haulmont.testtask.view.*;
import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {

    @Override
    protected void init(VaadinRequest request) {

        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(true);
        setContent(layout);

        HorizontalLayout headerLayout = new HorizontalLayout();
        headerLayout.setSizeFull();

        TabSheet tabSheet = new TabSheet();
        layout.addComponent(tabSheet);

        tabSheet.addTab(new PatientList(), "Пациенты");
        tabSheet.addTab(new DoctorList(), "Доктора");
        tabSheet.addTab(new FormulaList(), "Рецепты");


    }
}