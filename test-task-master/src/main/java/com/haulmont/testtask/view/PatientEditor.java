package com.haulmont.testtask.view;

import com.haulmont.testtask.dao.PatientDAO;
import com.haulmont.testtask.entity.PatientEntity;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.*;

public class PatientEditor extends Window {

    private Long id;
    private TextField name;
    private TextField surname;
    private TextField patronymic;
    private TextField phone_number;

    private boolean isEdit = false;

    public PatientEditor(String caption) {
        super(caption);
        isEdit = false;
        init();
    }

    public PatientEditor(String caption, PatientEntity patient){
        super(caption);
        isEdit = true;
        init();
        setValueField(patient);
    }

    void init(){
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
        surname.addValidator(new StringLengthValidator("Длина поля не более 20 символов",0, 20, false));
        surname.setSizeFull();
        formLayout.addComponent(surname);

        name = new TextField("Имя");
        name.setRequired(true);
        name.setRequiredError("Поле пустое");
        name.addValidator(new StringLengthValidator("Длина поля не более 20 символов",0, 20, false));
        name.setSizeFull();
        formLayout.addComponent(name);

        patronymic = new TextField("Отчество");
        patronymic.setRequired(true);
        patronymic.setRequiredError("Поле пустое");
        patronymic.addValidator(new StringLengthValidator("Длина поля не более 20 символов",0, 20, false));
        patronymic.setSizeFull();
        formLayout.addComponent(patronymic);

        phone_number = new TextField("Телефон");
        phone_number.addValidator(new StringLengthValidator("Длина поля не более 20 символов",0, 20, false));
        phone_number.setSizeFull();
        formLayout.addComponent(phone_number);

        layout.addComponent(formLayout);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setSpacing(true);

        Button okButton = new Button("OK");
        okButton.setWidth(30, Unit.PERCENTAGE);
        okButton.setDisableOnClick(true);
        okButton.addClickListener(click -> {
            if (!name.isValid() || !surname.isValid() || !patronymic.isValid() || !phone_number.isValid()) {
                okButton.setEnabled(true);
                return;
            }
            PatientDAO patientDAO = new PatientDAO();
            PatientEntity patient = isEdit ?
                    new PatientEntity(id, name.getValue(), surname.getValue(), patronymic.getValue(), phone_number.getValue())
                    : new PatientEntity(name.getValue(), surname.getValue(), patronymic.getValue(), phone_number.getValue());
            if (isEdit) {
                    patientDAO.update(patient);
            } else {
                    patientDAO.insert(patient);
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

    private void setValueField(PatientEntity patient) {
        id = patient.getPatient_id();
        name.setValue(patient.getName());
        surname.setValue(patient.getSurname());
        patronymic.setValue(patient.getPatronymic());
        phone_number.setValue(patient.getPhone_number());
    }
}
