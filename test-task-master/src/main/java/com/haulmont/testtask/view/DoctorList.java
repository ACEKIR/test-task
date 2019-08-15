package com.haulmont.testtask.view;

import com.haulmont.testtask.dao.DoctorDAO;
import com.haulmont.testtask.entity.DoctorEntity;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.*;

import java.util.List;

public class DoctorList extends VerticalLayout {

    private Grid grid = new Grid();
    private Button  button_ins = new Button("Добавить");
    private Button  button_upd = new Button("Изменить");
    private Button  button_del = new Button("Удалить");
    private DoctorDAO doctorDAO = new DoctorDAO();

    public DoctorList(){

        setSizeFull();
        setSpacing(true);

        grid.setSizeFull();
        grid.setSelectionMode(Grid.SelectionMode.MULTI);

        addComponent(grid);

        HorizontalLayout layout = new HorizontalLayout(button_ins, button_upd, button_del);
        layout.setSpacing(true);
        addComponent(layout);

        update();

        button_ins.addClickListener(click -> {
            DoctorEditor doctorEditor = new DoctorEditor("Добавить доктора");
            doctorEditor.setWidth(30, Unit.PERCENTAGE);
            UI.getCurrent().addWindow(doctorEditor);
            doctorEditor.addCloseListener(e -> update());
        });


        button_upd.addClickListener(click -> {
                    Grid.MultiSelectionModel selection = (Grid.MultiSelectionModel) grid.getSelectionModel();
                    Object itemId = selection.getSelectedRows().toArray()[0];
                    Long id = (Long) grid.getContainerDataSource().getItem(itemId).getItemProperty("doctor_id").getValue();
                    DoctorEntity doctor = doctorDAO.getById(id);
                    DoctorEditor doctorEditor = new DoctorEditor("Изменить доктора", doctor);
                    doctorEditor.setWidth(30, Unit.PERCENTAGE);
                    UI.getCurrent().addWindow(doctorEditor);
                    grid.clearSortOrder();
                    grid.getSelectionModel().reset();
                    click.getButton().setEnabled(false);
                    doctorEditor.addCloseListener(e -> update());
                }
        );

        button_del.addClickListener(click -> {
            Grid.MultiSelectionModel selection = (Grid.MultiSelectionModel) grid.getSelectionModel();
            for (Object itemId : selection.getSelectedRows()) {
                Long id = (Long) grid.getContainerDataSource().getItem(itemId).getItemProperty("doctor_id").getValue();
                doctorDAO.delete(doctorDAO.getById(id));
                update();
            }
            grid.getSelectionModel().reset();
            grid.clearSortOrder();
        });
    }

    private void update() {
        List<DoctorEntity> doctorList = doctorDAO.getAll();
        BeanItemContainer<DoctorEntity> container = new BeanItemContainer<DoctorEntity>(DoctorEntity.class, doctorList);
        container.removeAllItems();
        container.addAll(doctorList);
        grid.setContainerDataSource(container);
        grid.setColumnOrder("surname", "name", "patronymic", "specialization");
        grid.getColumn("surname").setHeaderCaption("Фамилия");
        grid.getColumn("name").setHeaderCaption("Имя");
        grid.getColumn("patronymic").setHeaderCaption("Отчество");
        grid.getColumn("specialization").setHeaderCaption("Специализация");
        if(grid.getColumn("doctor_id")!=null) {
            grid.removeColumn("doctor_id");
        }
    }
}
