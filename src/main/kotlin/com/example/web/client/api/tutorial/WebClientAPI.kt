package com.example.web.client.api.tutorial

import com.example.web.client.api.tutorial.model.Employee
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.BodyInserter
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class WebClientAPI {
    private val webClient = WebClient.create("http://localhost:8080")

    fun saveEmployee(): Mono<Employee> {
        return webClient
                .post()
                .uri("/v1/employees")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(Employee("123","Johnny", "ME")))
                .retrieve()
                .bodyToMono(Employee::class.java)
                .doOnNext { println("Inside saveEmployee $it \n") }
    }

    fun getAllEmployees(): Flux<Employee> {
        return webClient
                .get()
                .uri("/v1/employees")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Employee::class.java)
                .doOnNext { println("Inside getAllEmployees $it \n") }
    }

    fun updateEmployee(id: String, updatedEmployee: Employee): Mono<Employee> {
        return webClient
                .put()
                .uri("/v1/update")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(updatedEmployee))
                .retrieve()
                .bodyToMono(Employee::class.java)
                .doOnNext { println("Inside updateEmployee $it \n") }
    }

    fun deleteEmployee(id: String): Mono<Employee> {
        return webClient
                .delete()
                .uri("/v1/employees/{id}", id)
                .retrieve()
                .bodyToMono(Employee::class.java)
                .doOnSuccess { println("Inside deleteEmployee $it \n") }
    }
}