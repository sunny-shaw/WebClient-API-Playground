package com.example.web.client.api.tutorial

import com.example.web.client.api.tutorial.model.Employee
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TutorialWebClientApiApplication

fun main(args: Array<String>) {

    runApplication<TutorialWebClientApiApplication>(*args)

    //val api = WebClientAPI()
    val api = WebClientAPIUsingExchange()

    //WebClientAPI
    api.saveEmployee()
            .thenMany(api.getAllEmployees())
            .thenMany(api.updateEmployee("123", Employee("123", "Johnny", "CSE")))
            .thenMany(api.getAllEmployees())
            .thenMany(api.deleteEmployee("123"))
            .thenMany(api.getAllEmployees())
            .subscribe()
}
