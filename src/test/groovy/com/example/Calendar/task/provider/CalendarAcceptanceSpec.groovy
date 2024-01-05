package com.example.Calendar.task.provider

import com.example.inz.Samples
import com.example.inz.customer.operation.dto.SignUpDto

class CalendarAcceptanceSpec extends IntegrationSpec implements Samples{
    def "Should create new user"() {
        when: "creates new user"
        def result = api.customer().register(new SignUpDto("Jan","Kowalski","KowalskiJan","Password"))
        then: "user is created"
        equalsCustomers(result, createCustomer(userId: result.id, username: "Jan", login: "KowalskiJan"))
    }

    def cleanup() {
        api.customer().cleanup()
    }
}
