package com.github.codingpot.github_org_member_manage_action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.codingpot.github_org_member_manage_action.context.Context;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.logging.Logger;
import javax.inject.Inject;

/** ConfigManager is responsible for reading members.yaml */
public class ConfigManager {
    private static final Logger logger = Logger.getLogger(ConfigManager.class.getName());
    private final Context context;

    @Inject
    public ConfigManager(Context context) {
        this.context = context;
    }

    public Optional<ConfigData> readFromLocal() {
        return this.context
                .getMembersFilePath()
                .flatMap(
                        filePath -> {
                            try {
                                ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
                                ConfigData config =
                                        mapper.readValue(new File(filePath), ConfigData.class);
                                return Optional.of(config);
                            } catch (FileNotFoundException e) {
                                logger.severe("no yaml file was found in " + filePath);
                                e.printStackTrace();
                                return Optional.empty();
                            } catch (Exception e) {
                                logger.severe("yaml file is invalid");
                                e.printStackTrace();
                                return Optional.empty();
                            }
                        });
    }
}
