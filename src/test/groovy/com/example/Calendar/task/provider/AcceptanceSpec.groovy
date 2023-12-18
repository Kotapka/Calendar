//package com.example.Calendar.task.provider
//
//import com.example.inz.InzApplication
//import com.example.inz.Samples
//import com.example.inz.category.dto.CategoryDto
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.boot.test.web.client.TestRestTemplate
//import org.springframework.boot.test.web.server.LocalServerPort
//import spock.lang.Specification
//
//
//
//@SpringBootTest(classes = InzApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class AcceptanceSpec extends Specification implements Samples{
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    //@Sql({ "schema.sql", "data.sql" })
//    @Test
//    def testAllEmployees()
//    {
//        assert(
//                this.restTemplate
//                        .getForObject("http://localhost:" + port + "/api/addCategory", CategoryDto.class))
//    }
//}
