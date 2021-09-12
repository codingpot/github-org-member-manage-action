package com.github.codingpot.github_org_member_manage_action;

import com.github.codingpot.github_org_member_manage_action.context.Context;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

/** ConfigManager is responsible for reading members.yaml */
public class ConfigManager {
    private static final Logger logger = Logger.getLogger(ConfigManager.class.getName());
    private final Context context;

    @Inject
    public ConfigManager(Context context) {
        this.context = context;
    }

    public Optional<ConfigDataDummy> readFromLocal() {
        return this.context
                .getMembersFilePath()
                .flatMap(
                        filePath -> {
                            try {
                                ConfigDataDummy config =
                                        (ConfigDataDummy)
                                                new Yaml(new Constructor(ConfigDataDummy.class))
                                                        .load(new FileInputStream(filePath));
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

    /**
     * ConfigDataDummy is a dummy until ConfigData is implemented. This should follow Java Bean spec
     * to get serialized to YAML.
     *
     * <p>TODO: Remove ConfigDataDummy once a real ConfigData is implemented.
     */
    public static class ConfigDataDummy {
        private String orgName;
        private List<String> owners;
        private List<String> members;

        public ConfigDataDummy() {}

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public List<String> getOwners() {
            return owners;
        }

        public void setOwners(List<String> owners) {
            this.owners = owners;
        }

        public List<String> getMembers() {
            return members;
        }

        public void setMembers(List<String> members) {
            this.members = members;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ConfigDataDummy that = (ConfigDataDummy) o;
            return getOrgName().equals(that.getOrgName())
                    && getOwners().equals(that.getOwners())
                    && getMembers().equals(that.getMembers());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getOrgName(), getOwners(), getMembers());
        }
    }
}
