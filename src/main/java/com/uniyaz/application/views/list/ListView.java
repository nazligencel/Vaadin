package com.uniyaz.application.views.list;

import com.uniyaz.application.data.entity.Contact;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collections;

@PageTitle("list")
@Route(value = "")
public class ListView extends VerticalLayout {

    Grid<Contact> grid = new Grid<>(Contact.class);
    TextField filterText = new TextField();
    ContactForm contactForm;

    public ListView() {
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(
                getToolbar(),
                getContent()

        );
    }

    private Component getContent() {
     HorizontalLayout content =new HorizontalLayout(grid,contactForm);
     content.setFlexGrow(2,grid);
     content.setFlexGrow(1,contactForm);
     content.addClassName("content");
     content.setSizeFull();

     return content;
    }

    private void configureForm() {
        contactForm=new ContactForm(Collections.emptyList(),Collections.emptyList());
        contactForm.setWidth("30em");
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName", "email");
        grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status");
        grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button addContactButton = new Button("Add contact");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

}
