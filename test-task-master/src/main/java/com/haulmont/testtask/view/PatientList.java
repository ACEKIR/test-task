package com.haulmont.testtask.view;

import com.haulmont.testtask.dao.PatientDAO;
import com.haulmont.testtask.entity.PatientEntity;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Grid;
import com.vaadin.ui.*;

import java.util.List;

public class PatientList extends VerticalLayout {

    private Grid grid = new Grid();
    private Button button_ins = new Button("Добавить");
    private Button button_upd = new Button("Изменить");
    private Button button_del = new Button("Удалить");
    private PatientDAO patientDAO = new PatientDAO();

    public PatientList() {

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
            PatientEditor patientEditor = new PatientEditor("Добавить пациента");
            patientEditor.setWidth(30, Unit.PERCENTAGE);
            UI.getCurrent().addWindow(patientEditor);
            patientEditor.addCloseListener(e -> update());
        });


        button_upd.addClickListener(click -> {
                    Grid.MultiSelectionModel selection = (Grid.MultiSelectionModel) grid.getSelectionModel();
                    Object itemId = selection.getSelectedRows().toArray()[0];
                    Long id = (Long) grid.getContainerDataSource().getItem(itemId).getItemProperty("patient_id").getValue();
                    PatientEntity patient = patientDAO.getById(id);
                    PatientEditor patientEditor = new PatientEditor("Изменить пациента", patient);
                    patientEditor.setWidth(30, Unit.PERCENTAGE);
                    UI.getCurrent().addWindow(patientEditor);
                    grid.clearSortOrder();
                    grid.getSelectionModel().reset();
                    click.getButton().setEnabled(false);
                    patientEditor.addCloseListener(e -> update());
                }
        );

        button_del.addClickListener(click -> {
            Grid.MultiSelectionModel selection = (Grid.MultiSelectionModel) grid.getSelectionModel();
            for (Object itemId : selection.getSelectedRows()) {
                Long id = (Long) grid.getContainerDataSource().getItem(itemId).getItemProperty("patient_id").getValue();
                patientDAO.delete(patientDAO.getById(id));
                update();
            }
            grid.getSelectionModel().reset();
            grid.clearSortOrder();
        });
    }

    private void update() {
        List<PatientEntity> patientList = patientDAO.getAll();
        BeanItemContainer<PatientEntity> container = new BeanItemContainer<PatientEntity>(PatientEntity.class, patientList);
        container.removeAllItems();
        container.addAll(patientList);
        grid.setContainerDataSource(container);
        grid.setColumnOrder("surname", "name", "patronymic", "phone_number");
        grid.getColumn("surname").setHeaderCaption("Фамилия");
        grid.getColumn("name").setHeaderCaption("Имя");
        grid.getColumn("patronymic").setHeaderCaption("Отчество");
        grid.getColumn("phone_number").setHeaderCaption("Телефон");
        if(grid.getColumn("patient_id")!=null) {
            grid.removeColumn("patient_id");
        }
    }
}
