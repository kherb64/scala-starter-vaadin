package starter.skeleton

import com.vaadin.flow.component.ClickEvent
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.combobox.ComboBox
import com.vaadin.flow.component.formlayout.FormLayout
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.demo.helloworld.{Customer, CustomerService, CustomerStatus}
import com.vaadin.flow.data.binder.Binder

class CustomerForm(view: MainView) extends FormLayout {
  val firstName = new TextField("First name")
  val lastName = new TextField("Last name")
  val status = new ComboBox[CustomerStatus]("Status")
  val btnSave = new Button("Save")
  val btnDelete = new Button("Delete")

  val service: CustomerService = CustomerService.getInstance()
  var customer: Customer = _

  val binder = new Binder[Customer](classOf[Customer])

  //this.view = view

  binder.bindInstanceFields(this)

  btnSave.getElement.setAttribute("theme", "primary")
  val buttons = new HorizontalLayout(btnSave, btnDelete)

  add(firstName, lastName, status, buttons)

  //TODO better way to populate Vaadin combobox
  status.setItems(CustomerStatus.ImportedLead,
    CustomerStatus.NotContacted,
    CustomerStatus.Contacted,
    CustomerStatus.Customer,
    CustomerStatus.ClosedLost)

  setCustomer(null)
  btnSave.addClickListener((_: ClickEvent[Button]) => this.save())
  btnDelete.addClickListener((_: ClickEvent[Button]) => this.delete())

  def setCustomer(customer: Customer): Unit = {
    this.customer = customer
    binder.setBean(customer)
    val enabled = customer != null
    btnSave.setEnabled(enabled)
    btnDelete.setEnabled(enabled)
    if (enabled) firstName.focus()
  }

  private def delete(): Unit = {
    service.delete(customer)
    view.updateList()
    setCustomer(null)
  }

  private def save(): Unit = {
    service.save(customer)
    val c = customer
    view.updateList()
    setCustomer(c)
  }

}
