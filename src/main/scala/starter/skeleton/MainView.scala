package starter.skeleton

import com.vaadin.flow.component.{AbstractField, ClickEvent}
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.html.Label
import com.vaadin.flow.component.icon.{Icon, VaadinIcon}
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment
import com.vaadin.flow.component.orderedlayout.{HorizontalLayout, VerticalLayout}
import com.vaadin.flow.component.page.Push
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.value.ValueChangeMode
import com.vaadin.flow.demo.helloworld.{Customer, CustomerService}
import com.vaadin.flow.router.Route
import com.vaadin.flow.shared.communication.PushMode
import com.vaadin.flow.shared.ui.Transport
import com.vaadin.flow.theme.Theme
import com.vaadin.flow.theme.lumo.Lumo

@Route("")
@Theme(
  value = classOf[Lumo],
  variant = Lumo.DARK
)
class MainView extends VerticalLayout {
  add(new Label("Hi, I'm the MainView!"))

  val service: CustomerService = CustomerService.getInstance()
  val grid = new Grid[Customer]()
  val filterText = new TextField()
  val form: CustomerForm = new CustomerForm(this)

  filterText.setPlaceholder("Filter by name...")
  filterText.setValueChangeMode(ValueChangeMode.EAGER)
  filterText.addValueChangeListener((_: AbstractField.ComponentValueChangeEvent[TextField, String]) => updateList())

  val addCustomerBtn = new Button("Add new customer")
  addCustomerBtn.addClickListener((e: ClickEvent[Button]) => {
    def foo(e: ClickEvent[Button]): Unit = {
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
  grid.addColumn(_.getFirstName).setHeader("First name")
  grid.addColumn(_.getLastName).setHeader("Last name")
  grid.addColumn(_.getStatus).setHeader("Status")

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
