package starter.skeleton

import com.vaadin.flow.component.{AbstractField, ClickEvent}
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.icon.{Icon, VaadinIcon}
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment
import com.vaadin.flow.component.orderedlayout.{HorizontalLayout, VerticalLayout}
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.value.ValueChangeMode
import com.vaadin.flow.demo.helloworld.{Customer, CustomerService}

class MainView extends VerticalLayout {
  val service: CustomerService = CustomerService.getInstance()
  val grid = new Grid[Customer]()
  val filterText = new TextField()
  val form: CustomerForm = new CustomerForm(this)

  filterText.setPlaceholder("Filter by name...")
  filterText.setValueChangeMode(ValueChangeMode.EAGER)
  filterText.addValueChangeListener((_: AbstractField.ComponentValueChangeEvent[TextField, String]) => updateList())

  val addCustomerBtn = new Button("Add new customer")
  addCustomerBtn.addClickListener((e: ClickEvent[Button]) => {
    def foo(e: ClickEvent[Button]):Unit = {
      grid.asSingleSelect.clear()
      form.setCustomer(new Customer)
    }

    foo(e)
  })

  val clearFilterTextBtn = new Button(new Icon(VaadinIcon.CLOSE_CIRCLE))
  clearFilterTextBtn.addClickListener((_: ClickEvent[Button]) => filterText.clear())

  val filtering = new HorizontalLayout(filterText, clearFilterTextBtn)
  val toolbar = new HorizontalLayout(filtering, addCustomerBtn)

  grid.setSizeFull()
  grid.addColumn("FirstName").setHeader("First name")
  grid.addColumn("LastName").setHeader("Last name")
  grid.addColumn("Status").setHeader("Status")

  val main = new HorizontalLayout(grid, form)
  main.setAlignItems(Alignment.START)
  main.setSizeFull()

  add(toolbar, main)
  setHeight("100vh")
  updateList()

  grid.asSingleSelect.addValueChangeListener((event: AbstractField.ComponentValueChangeEvent[Grid[Customer], Customer]) => form.setCustomer(event.getValue))

  private[skeleton] def updateList(): Unit = {
    grid.setItems(service.findAll(filterText.getValue))
  }

}
