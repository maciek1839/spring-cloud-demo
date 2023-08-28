package com.showmeyourcode.spring_cloud.test.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Slf4j
public class ApiSchemaUtil {

    private ApiSchemaUtil() {
    }

    public static void updateClientSchema(String currentApiSpecificationInJson, String clientModuleFileToReplace) throws IOException {
        Path currentRelativePath = Paths.get("");
        String currentAbsolutePath= currentRelativePath.toAbsolutePath().toString();
        String springCloudDemoProjectPath = new File(currentAbsolutePath).getParentFile().getAbsolutePath();
        String clientSchemaFile = springCloudDemoProjectPath + "/client/src/main/resources/"+clientModuleFileToReplace;

        String apiSchemaToWrite = beautify(currentApiSpecificationInJson);

        log.info("Working directory: {}", currentAbsolutePath);
        log.info("Project directory: {}", springCloudDemoProjectPath);
        log.info("Client API file: {}", clientSchemaFile);
        log.info("Content to write: {}", apiSchemaToWrite);

        Files.write(Path.of(clientSchemaFile), apiSchemaToWrite.getBytes(), StandardOpenOption.WRITE);
    }

    @SneakyThrows
    private static String beautify(String json) {
        ObjectMapper mapper = new ObjectMapper();
        Object obj = mapper.readValue(json, Object.class);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    }
}
