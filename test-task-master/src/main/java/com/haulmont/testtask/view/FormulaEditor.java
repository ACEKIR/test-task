package com.haulmont.testtask.view;

import com.haulmont.testtask.dao.DoctorDAO;
import com.haulmont.testtask.dao.FormulaDAO;
import com.haulmont.testtask.dao.PatientDAO;
import com.haulmont.testtask.dao.PriorityDAO;
import com.haulmont.testtask.entity.DoctorEntity;
import com.haulmont.testtask.entity.FormulaEntity;

import com.haulmont.testtask.entity.PatientEntity;
import com.haulmont.testtask.entity.PriorityEntity;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.*;

import java.util.Date;
import java.util.List;

public class FormulaEditor extends Window {

    private NativeSelect doctorSelect;
    private NativeSelect patientSelect;
    private NativeSelect prioritySelect;
    private TextField description;
    private DateField creation_date;
    private TextField validity;
    private DoctorDAO doctorDAO = new DoctorDAO();

    private boolean isEdit = false;


    public FormulaEditor(String caption) {
        super(caption);
        isEdit = false;
        init();
    }

    public FormulaEditor(String caption, FormulaEntity formula) {
        super(caption);
        isEdit = true;
        init();
        setValueField(formula);
    }

    void init() {
        center();

        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        setClosable(true);
        setResizable(false);
        setModal(true);

        FormLayout formLayout = new FormLayout();
        formLayout.setSpacing(true);

        description = new TextField("Описание");
        description.setRequired(true);
        description.setRequiredError("Поле пустое");
        description.addValidator(new StringLengthValidator("Длина поля не более 100 символов", 0, 20, false));
        description.setSizeFull();
        formLayout.addComponent(description);

        DoctorDAO doctorDAO = new DoctorDAO();
        List<DoctorEntity> doctorList = doctorDAO.getAll();
        BeanItemContainer<DoctorEntity> doctorContainer = new BeanItemContainer<>(DoctorEntity.class, doctorList);
        doctorSelect = new NativeSelect("Доктор", doctorContainer);
        doctorSelect.setRequired(true);
        doctorSelect.setNullSelectionAllowed(false);
        doctorSelect.setValue(doctorSelect.getItemIds().iterator().next());
        doctorSelect.setSizeFull();
        formLayout.addComponent(doctorSelect);

        PatientDAO patientDAO = new PatientDAO();
        List<PatientEntity> patientList = patientDAO.getAll();
        BeanItemContainer<PatientEntity> patientContainer = new BeanItemContainer<>(PatientEntity.class, patientList);
        patientSelect = new NativeSelect("Пациент", patientContainer);
        patientSelect.setRequired(true);
        patientSelect.setNullSelectionAllowed(false);
        patientSelect.setValue(patientSelect.getItemIds().iterator().next());
        patientSelect.setSizeFull();
        formLayout.addComponent(patientSelect);


        creation_date = new DateField("Дата создания");
        creation_date.setValue(new Date());
        creation_date.setDateFormat("yyyy-MM-dd");
        creation_date.setLenient(true);
        creation_date.setRequired(true);
        creation_date.setSizeFull();
        formLayout.addComponent(creation_date);


        validity = new TextField("Срок действия");
        validity.addValidator(new RegexpValidator("[0-9]+", "Поле должно состоять из цифр"));
        validity.setSizeFull();
        formLayout.addComponent(validity);

        PriorityDAO priorityDAO = new PriorityDAO();
        List<PriorityEntity> priorityList = priorityDAO.getAll();
        BeanItemContainer<PriorityEntity> priorityContainer = new BeanItemContainer<>(PriorityEntity.class, priorityList);
        prioritySelect = new NativeSelect("Приоритет", priorityContainer);
        prioritySelect.setRequired(true);
        prioritySelect.setNullSelectionAllowed(false);
        prioritySelect.setValue(prioritySelect.getItemIds().iterator().next());
        prioritySelect.setSizeFull();
        formLayout.addComponent(prioritySelect);

        layout.addComponent(formLayout);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setSpacing(true);

        Button okButton = new Button("OK");
        okButton.setWidth(30, Unit.PERCENTAGE);
        okButton.setDisableOnClick(true);
        okButton.addClickListener(click -> {
            if (!description.isValid() || !validity.isValid()) {
                okButton.setEnabled(true);
                return;
            }
            FormulaDAO formulaDAO = new FormulaDAO();
            DoctorEntity doctor1 = (DoctorEntity) doctorSelect.getValue();
            PatientEntity patient1 = (PatientEntity) patientSelect.getValue();
            PriorityEntity priority1 = (PriorityEntity) prioritySelect.getValue();
            String description1 = description.getValue();
            Date creationDate1 = creation_date.getValue();
            java.sql.Date sqlDate = new java.sql.Date(creationDate1.getTime());
            int validity1 = Integer.parseInt(validity.getValue());

            FormulaEntity formula = isEdit ? new FormulaEntity(doctor1.getDoctor_id(), patient1.getPatient_id(), priority1.getPriority_id(), description1, sqlDate, validity1) :
                    new FormulaEntity(doctor1.getDoctor_id(), patient1.getPatient_id(), priority1.getPriority_id(), description1, sqlDate, validity1);
            if (isEdit) {
                formulaDAO.update(formula);
            } else {
                formulaDAO.insert(formula);
            }
            close();
        });

        Button closeButton = new Button("Отменить");
        closeButton.setWidth(30, Unit.PERCENTAGE);
        closeButton.addClickListener(click -> close());

        buttonLayout.addComponent(okButton);
        buttonLayout.addComponent(closeButton);

        layout.addComponent(buttonLayout);

        layout.setComponentAlignment(formLayout, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(buttonLayout, Alignment.MIDDLE_CENTER);
    }

    private void setValueField(FormulaEntity formula) {
        doctorSelect.setValue(doctorDAO.getById(formula.getDoctor_id()).getSurname());
        patientSelect.setValue(formula.getPatient_id());
        prioritySelect.setValue(formula.getPriority_id());
        description.setValue(formula.getDescription());
        creation_date.setValue(formula.getCreation_date());
        validity.setValue(String.valueOf(formula.getValidity()));
    }
}


