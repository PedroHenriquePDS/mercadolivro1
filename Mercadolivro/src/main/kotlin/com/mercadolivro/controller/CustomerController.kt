package com.mercadolivro.controller

import com.mercadolivro.model.CustomerModel
import com.mercadolivro.controller.request.PostCustomerRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customer")
class CustomerController {
    val customers = mutableListOf<CustomerModel>()

    @GetMapping
    fun getAll(): List<CustomerModel> {
        return customers
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customer: PostCustomerRequest) {
        val id = if (customers.isEmpty()) {
            1
        } else {
            customers.last().id + 1
        }
        customers.add(CustomerModel(id, customer.name, customer.email))
        println(customer)
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Int): CustomerModel {
        return customers.filter { it.id == id }.first()
    }
}