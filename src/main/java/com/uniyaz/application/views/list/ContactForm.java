package com.uniyaz.application.views.list;

import com.uniyaz.application.data.entity.Company;
import com.uniyaz.application.data.entity.Status;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

public class ContactForm extends FormLayout {

    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");
    EmailField email = new EmailField("Email");
    ComboBox<Status> status = new ComboBox<>("Status");
    ComboBox<Company> company = new ComboBox<>("Company");

    Button buttonSave=new Button("Save");
    Button buttonDelete=new Button("Delete");
    Button buttonClose=new Button("Close");

    ContactForm(List<Company> companyList, List<Status> statusList){
        addClassName("contact-form");

        company.setItems(companyList);
        company.setItemLabelGenerator(Company::getName);
        status.setItems(statusList);
        status.setItemLabelGenerator(Status::getName);

        add(firstName,lastName,email,company,status,createButtonLayout());
    }

    private HorizontalLayout createButtonLayout() {
        buttonSave.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonDelete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        buttonClose.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        buttonSave.addClickShortcut(Key.ENTER);
        buttonClose.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(buttonSave,buttonDelete,buttonClose);
    }
}
