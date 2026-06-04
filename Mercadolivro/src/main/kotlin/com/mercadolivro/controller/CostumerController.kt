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
            "1"
        } else {
            (customers.last().id.toInt() + 1).toString()
        }


        customers.add(CustomerModel(id = id, name = customer.name, email = customer.email))
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: String): CustomerModel? {
        println("id")
        return customers.find { it.id == id }
    }
}