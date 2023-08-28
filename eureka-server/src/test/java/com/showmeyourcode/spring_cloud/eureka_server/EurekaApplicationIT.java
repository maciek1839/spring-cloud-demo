package com.showmeyourcode.spring_cloud.eureka_server;

import com.netflix.discovery.shared.Applications;
import com.netflix.eureka.EurekaServerContextHolder;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import com.showmeyourcode.spring_cloud.eureka_server.constant.EndpointConstant;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.notNullValue;


class EurekaApplicationIT extends BaseIT {

    @Test
    void shouldStartEurekaServer() {
        MatcherAssert.assertThat(context.getBean("eurekaServerContext"), Matchers.is(notNullValue()));

        // This is a way to programmatically get registered apps to the Eureka server.
        PeerAwareInstanceRegistry registry = EurekaServerContextHolder.getInstance().getServerContext().getRegistry();
        Applications applications = registry.getApplications();
        MatcherAssert.assertThat(
                applications.getRegisteredApplications().size(),
                Matchers.greaterThanOrEqualTo(0)
        );
    }

    @Test
    void shouldExposeDefaultEurekaDashboard(){
        Response response = RestAssured.given(this.requestSpecification)
                .accept(MediaType.TEXT_HTML_VALUE)
                .when()
                .get(EndpointConstant.EUREKA_DASHBOARD_ENDPOINT);

        MatcherAssert.assertThat(response.statusCode(), Matchers.is(HttpStatus.OK.value()));
        MatcherAssert.assertThat(response.body().asString().isBlank(), Matchers.is(false));
    }

}
