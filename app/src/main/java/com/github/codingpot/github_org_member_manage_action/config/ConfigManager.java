package com.github.codingpot.github_org_member_manage_action.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.codingpot.github_org_member_manage_action.context.Context;
import java.io.File;
import java.io.IOException;
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

    public ConfigData readFromLocal() throws IOException {
        var filepath = this.context.getMembersFilePath();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(new File(filepath), ConfigData.class);
    }
}