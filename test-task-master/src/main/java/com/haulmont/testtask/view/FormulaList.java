package com.haulmont.testtask.view;

import com.haulmont.testtask.dao.DoctorDAO;
import com.haulmont.testtask.dao.FormulaDAO;
import com.haulmont.testtask.entity.DoctorEntity;
import com.haulmont.testtask.entity.FormulaEntity;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.*;

import java.util.List;

public class FormulaList extends VerticalLayout {
    private Grid grid = new Grid();
    private Button button_ins = new Button("Добавить");
    private Button button_upd = new Button("Изменить");
    private Button button_del = new Button("Удалить");
    private FormulaDAO formulaDAO = new FormulaDAO();

    public FormulaList() {

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
            FormulaEditor formulaEditor = new FormulaEditor("Добавить рецепт");
            formulaEditor.setWidth(200, Unit.PIXELS);
            UI.getCurrent().addWindow(formulaEditor);
            formulaEditor.addCloseListener(e -> update());

        });


        button_upd.addClickListener(click -> {
                    Grid.MultiSelectionModel selection = (Grid.MultiSelectionModel) grid.getSelectionModel();
                    Object itemId = selection.getSelectedRows().toArray()[0];
                    Long doctor_id = (Long) grid.getContainerDataSource().getItem(itemId).getItemProperty("doctor_id").getValue();
                    Long patient_id = (Long) grid.getContainerDataSource().getItem(itemId).getItemProperty("patient_id").getValue();
                    Long priority_id = (Long) grid.getContainerDataSource().getItem(itemId).getItemProperty("priority_id").getValue();
                    FormulaEntity formula = formulaDAO.getById(doctor_id, patient_id, priority_id);
                    FormulaEditor formulaEditor = new FormulaEditor("Изменить рецепт", formula);
                    formulaEditor.setWidth(30, Unit.PERCENTAGE);
                    UI.getCurrent().addWindow(formulaEditor);
                    grid.clearSortOrder();
                    grid.getSelectionModel().reset();
                    click.getButton().setEnabled(false);
                    formulaEditor.addCloseListener(e -> update());
                }
        );

        button_del.addClickListener(click -> {
            Grid.MultiSelectionModel selection = (Grid.MultiSelectionModel) grid.getSelectionModel();
            for (Object itemId : selection.getSelectedRows()) {
                Long doctor_id = (Long) grid.getContainerDataSource().getItem(itemId).getItemProperty("doctor_id").getValue();
                Long patient_id = (Long) grid.getContainerDataSource().getItem(itemId).getItemProperty("patient_id").getValue();
                Long priority_id = (Long) grid.getContainerDataSource().getItem(itemId).getItemProperty("priority_id").getValue();
                formulaDAO.delete(formulaDAO.getById(doctor_id, patient_id, priority_id));
                update();
            }
            grid.getSelectionModel().reset();
            grid.clearSortOrder();
        });
    }

    private void update() {
        List<FormulaEntity> formulaList = formulaDAO.getAll();
        BeanItemContainer<FormulaEntity> container = new BeanItemContainer<FormulaEntity>(FormulaEntity.class, formulaList);
        container.removeAllItems();
        container.addAll(formulaList);
        grid.setContainerDataSource(container);
        grid.setColumnOrder("doctor_id", "patient_id", "priority_id", "description", "creation_date", "validity");
        grid.getColumn("doctor_id").setHeaderCaption("Доктор");
        grid.getColumn("patient_id").setHeaderCaption("Пациент");
        grid.getColumn("priority_id").setHeaderCaption("Приоритет");
        grid.getColumn("description").setHeaderCaption("Описание");
        grid.getColumn("creation_date").setHeaderCaption("Дата создания");
        grid.getColumn("validity").setHeaderCaption("Срок действия");
    }

}
