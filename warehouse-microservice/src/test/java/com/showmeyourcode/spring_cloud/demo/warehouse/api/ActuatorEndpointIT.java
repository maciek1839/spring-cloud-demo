package com.showmeyourcode.spring_cloud.demo.warehouse.api;

import com.showmeyourcode.spring_cloud.demo.warehouse.BaseIT;
import com.showmeyourcode.spring_cloud.demo.warehouse.constant.EndpointConstant;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@Slf4j
class ActuatorEndpointIT extends BaseIT {

    @Test
    void shouldExposeActuatorEndpoint() {
        RestAssured.given(this.requestSpecification)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(addContextPath(EndpointConstant.ACTUATOR_ENDPOINT))
                .then()
                .assertThat()
                .statusCode(Matchers.is(HttpStatus.OK.value()));
    }

    @Test
    void shouldExposeHealthEndpoint() {
        RestAssured.given(this.requestSpecification)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(addContextPath(EndpointConstant.ACTUATOR_HEALTH_ENDPOINT))
                .then()
                .assertThat()
                .statusCode(Matchers.is(HttpStatus.OK.value()))
                .body("isEmpty()", Matchers.is(false));
    }

    @Test
    void shouldExposeInfoEndpoint() {
        RestAssured.given(this.requestSpecification)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(addContextPath(EndpointConstant.ACTUATOR_INFO_ENDPOINT))
                .then()
                .assertThat()
                .log().ifValidationFails(LogDetail.BODY)
                .statusCode(Matchers.is(HttpStatus.OK.value()))
                .body("isEmpty()", Matchers.is(false))
                .body("git", Matchers.notNullValue())
                .body("build", Matchers.notNullValue());
    }

    @Test
    void shouldExposeMetricsEndpoint() {
        RestAssured.given(this.requestSpecification)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(addContextPath(EndpointConstant.ACTUATOR_METRICS_ENDPOINT))
                .then()
                .assertThat()
                .log().ifValidationFails(LogDetail.BODY)
                .statusCode(Matchers.is(HttpStatus.OK.value()))
                .body("isEmpty()", Matchers.is(false))
                .body("names", Matchers.notNullValue())
                .body("names", Matchers.hasItems(
                        "jvm.buffer.count",
                        "jvm.buffer.memory.used",
                        "jvm.buffer.total.capacity",
                        "jvm.classes.loaded",
                        "jvm.classes.unloaded",
                        "jvm.gc.live.data.size",
                        "jvm.gc.max.data.size",
                        "jvm.gc.memory.allocated",
                        "jvm.gc.memory.promoted",
                        "jvm.gc.pause",
                        "jvm.memory.committed",
                        "jvm.memory.max",
                        "jvm.memory.used",
                        "jvm.threads.daemon",
                        "jvm.threads.live",
                        "jvm.threads.peak",
                        "jvm.threads.states",
                        "logback.events",
                        "process.cpu.usage",
                        "process.start.time",
                        "process.uptime",
                        "system.cpu.count",
                        "system.cpu.usage",
                        "tomcat.sessions.active.current",
                        "tomcat.sessions.active.max",
                        "tomcat.sessions.alive.max",
                        "tomcat.sessions.created",
                        "tomcat.sessions.expired",
                        "tomcat.sessions.rejected"
                ));
    }

}
