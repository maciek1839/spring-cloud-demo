package com.showmeyourcode.spring_cloud.demo.test.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Slf4j
public class ApiSchemaUtil {

    public enum ApiFileToReplace {
        SHOP("api-docs-shop-microservice.json"),
        WAREHOUSE("api-docs-warehouse-microservice.json"),
        FACTORY("api-docs-factory-microservice.json");

        private final String name;

        ApiFileToReplace(String name) {
            this.name = name;
        }
    }

    public enum DestinationProject{
        REPORTING("reporting-microservice"),
        SHOP("shop-microservice"),
        WAREHOUSE("warehouse-microservice"),
        FACTORY("factory-microservice");

        private final String name;

        DestinationProject(String name) {
            this.name = name;
        }
    }

    private ApiSchemaUtil() {
    }

    public static void updateClientSchema(
            String currentApiSpecification,
                                          List<DestinationProject> destinationProjects,
            ApiFileToReplace fileToReplace
    ) throws IOException {
        Path currentRelativePath = Paths.get("");
        String currentAbsolutePath= currentRelativePath.toAbsolutePath().toString();
        String springCloudDemoProjectPath = new File(currentAbsolutePath).getParentFile().getAbsolutePath();

        log.info("Working directory: {}", currentAbsolutePath);
        log.info("Project directory: {}", springCloudDemoProjectPath);

        String apiSchemaToWrite = beautify(currentApiSpecification);
        log.info("Content to write: {}", apiSchemaToWrite);

        for(DestinationProject project: destinationProjects){
            log.info("Processing: {}", project);

            String clientSchemaFile = springCloudDemoProjectPath + "/" + project.name + "/src/main/resources/"+fileToReplace.name;
            log.info("Client API file: {}", clientSchemaFile);

            Files.write(Path.of(clientSchemaFile), apiSchemaToWrite.getBytes(), StandardOpenOption.WRITE);
        }
    }


    private static String beautify(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Object obj = mapper.readValue(json, Object.class);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException ex){
            log.warn("Cannot format the API specification.");
            return json;
        }
    }
}
