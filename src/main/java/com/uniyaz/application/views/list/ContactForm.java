package com.uniyaz.application.views.list;

import com.uniyaz.application.data.entity.Company;
import com.uniyaz.application.data.entity.Contact;
import com.uniyaz.application.data.entity.Status;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class ContactForm extends FormLayout {

    Binder<Contact> contactBinder= new BeanValidationBinder<>(Contact.class);

    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");
    EmailField email = new EmailField("Email");
    ComboBox<Status> status = new ComboBox<>("Status");
    ComboBox<Company> company = new ComboBox<>("Company");

    Button buttonSave=new Button("Save");
    Button buttonDelete=new Button("Delete");
    Button buttonClose=new Button("Close");
    private Contact contact;

    ContactForm(List<Company> companyList, List<Status> statusList){
        addClassName("contact-form");
        contactBinder.bindInstanceFields(this);

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

        buttonSave.addClickListener(buttonClickEvent -> saveAll());
        buttonDelete.addClickListener(buttonClickEvent -> fireEvent(new DeleteEvent(this,contact)));
        buttonClose.addClickListener(buttonClickEvent -> fireEvent(new CloseEvent(this)));

        return new HorizontalLayout(buttonSave,buttonDelete,buttonClose);
    }

    private void saveAll() {
        try{
            contactBinder.writeBean(contact);
            fireEvent(new SaveEvent(this,contact));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public void setContact(Contact contact){
        this.contact=contact;
        contactBinder.readBean(contact);
    }

    // Events
    public static abstract class ContactFormEvent extends ComponentEvent<ContactForm> {
        private Contact contact;

        protected ContactFormEvent(ContactForm source, Contact contact) {
            super(source, false);
            this.contact = contact;
        }

        public Contact getContact() {
            return contact;
        }
    }

    public static class SaveEvent extends ContactFormEvent {
        SaveEvent(ContactForm source, Contact contact) {
            super(source, contact);
        }
    }

    public static class DeleteEvent extends ContactFormEvent {
        DeleteEvent(ContactForm source, Contact contact) {
            super(source, contact);
        }

    }

    public static class CloseEvent extends ContactFormEvent {
        CloseEvent(ContactForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
