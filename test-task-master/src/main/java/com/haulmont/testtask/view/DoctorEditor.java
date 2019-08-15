package com.haulmont.testtask.view;

import com.haulmont.testtask.dao.DoctorDAO;
import com.haulmont.testtask.entity.DoctorEntity;
import com.vaadin.ui.*;
import com.vaadin.data.validator.StringLengthValidator;

public class DoctorEditor extends com.vaadin.ui.Window {

    private Long id;
    private TextField name;
    private TextField surname;
    private TextField patronymic;
    private TextField specialization;

    private boolean isEdit;

    public DoctorEditor(String caption) {
        super(caption);
        isEdit = false;
        init();
    }

    public DoctorEditor(String caption, DoctorEntity doctor) {
        super(caption);
        isEdit = true;
        init();
        setValueField(doctor);
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

        surname = new TextField("Фамилия");
        surname.setRequired(true);
        surname.setRequiredError("Поле пустое");
        surname.addValidator(new StringLengthValidator("Длина поля не более 20 символов", 0, 20, false));
        surname.setSizeFull();
        formLayout.addComponent(surname);

        name = new TextField("Имя");
        name.setRequired(true);
        name.setRequiredError("Поле пустое");
        name.addValidator(new StringLengthValidator("Длина поля не более 20 символов", 0, 20, false));
        name.setSizeFull();
        formLayout.addComponent(name);

        patronymic = new TextField("Отчество");
        patronymic.setRequired(true);
        patronymic.setRequiredError("Поле пустое");
        patronymic.addValidator(new StringLengthValidator("Длина поля не более 20 символов", 0, 20, false));
        patronymic.setSizeFull();
        formLayout.addComponent(patronymic);

        specialization = new TextField("Специализация");
        specialization.addValidator(new StringLengthValidator("Длина поля не более 20 символов", 0, 20, false));
        specialization.setSizeFull();
        formLayout.addComponent(specialization);

        layout.addComponent(formLayout);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setSpacing(true);

        Button okButton = new Button("OK");
        okButton.setWidth(30, Unit.PERCENTAGE);
        okButton.setDisableOnClick(true);
        okButton.addClickListener(click -> {
            if (!name.isValid() || !surname.isValid() || !patronymic.isValid() || !specialization.isValid()) {
                okButton.setEnabled(true);
                return;
            }
            DoctorDAO doctorDAO = new DoctorDAO();
            DoctorEntity doctor = isEdit ? new DoctorEntity(id, name.getValue(), surname.getValue(), patronymic.getValue(), specialization.getValue()) :
                    new DoctorEntity(name.getValue(), surname.getValue(), patronymic.getValue(), specialization.getValue());
            if (isEdit) {
                doctorDAO.update(doctor);
            } else {
                doctorDAO.insert(doctor);
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

    private void setValueField(DoctorEntity doctor) {
        id = doctor.getDoctor_id();
        name.setValue(doctor.getName());
        surname.setValue(doctor.getSurname());
        patronymic.setValue(doctor.getPatronymic());
        specialization.setValue(doctor.getSpecialization());
    }
}

