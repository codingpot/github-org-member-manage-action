# GitHub Org Member Manage Action

This GitHub Action allows you to manage your GitHub organization's memberships in a declarative way using a YAML file. This file becomes the single source of truth for your organization's members, making it easy to manage and version control your member list.

## Features

- **Declarative Membership Management:** Define your organization's members and their roles (admin or member) in a simple YAML file.
- **Synchronization:** The action automatically synchronizes the members of your organization to match the state defined in your YAML file.
- **Dry Run Mode:** Preview the changes that would be made without actually applying them.
- **Bootstrap Your Configuration:** `write` mode helps you to create a `members.yaml` from the current state of your GitHub org members.

## Usage

To use this action in your workflow, you need to add a step that uses `codingpot/github-org-member-manage-action`.

### Example Workflow

Here is an example workflow that runs on a schedule and synchronizes the organization's members:

```yaml
name: Manage Org Members

on:
  schedule:
    - cron: '0 0 * * *' # Run daily at midnight
  workflow_dispatch:

jobs:
  manage-members:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout repository
        uses: actions/checkout@v3

      - name: Manage GitHub Org Members
        uses: "codingpot/github-org-member-manage-action@v1"
        with:
          gh_token: ${{ secrets.GH_TOKEN }}
          members_filepath: 'members.yaml'
          mode: 'sync'
```

### `members.yaml` format

The `members.yaml` file defines the members of your organization. Here is an example:

```yaml
# members.yaml
org_name: your-org-name

admins:
  - admin-user-1
  - admin-user-2

members:
  - member-user-1
  - member-user-2
```

## Action Inputs

| Input              | Description                                                                                                                                                                                                                                                                                       | Default        |
| ------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------- |
| `gh_token`         | A GitHub token with `admin:org` scope. This is required to manage organization memberships. It is recommended to use a secret to store the token.                                                                                                                                                    | **Required**   |
| `members_filepath` | The file path to the YAML file containing the list of members. This file will be the single source of truth for the organization's memberships.                                                                                                                                                      | `members.yaml` |
| `dry_run`          | If set to `true`, the action will only print the changes that would be made, without actually applying them. This is useful for testing and debugging.                                                                                                                                                 | `false`        |
| `mode`             | The mode of operation for the action. There are two modes available: `sync` and `write`.<ul><li>`sync`: This mode synchronizes the organization's memberships with the state defined in the `members_filepath` file.</li><li>`write`: This mode fetches the current organization memberships and writes them to the `members_filepath` file.</li></ul> | `sync`         |

## Development

This section guides you through setting up your development environment and testing the action locally.

### Prerequisites

- [Java](httpshttps://www.java.com/en/download/)
- [Gradle](https://gradle.org/install/)

### Local Testing

1. Create a `members.yaml` file in the root of the project with the `org_name` of the organization you want to manage.

   ```yaml
   # members.yaml
   org_name: your-org-name
   ```

2. Run the action in `write` mode to fetch the current members of the organization and populate the `members.yaml` file. You will need to provide a GitHub token with `admin:org` scope as an environment variable.

   ```shell
   INPUT_GH_TOKEN=<your-github-token> INPUT_MODE=write INPUT_MEMBERS_FILEPATH=$PWD/members.yaml ./gradlew run
   ```

3. After the `members.yaml` file is populated, you can run the action in `sync` mode to test the synchronization logic. It's recommended to run with `INPUT_DRY_RUN=true` first to preview the changes.

   ```shell
   INPUT_GH_TOKEN=<your-github-token> INPUT_MODE=sync INPUT_DRY_RUN=true INPUT_MEMBERS_FILEPATH=$PWD/members.yaml ./gradlew run
   ```

### Unit Tests

To run the unit tests, use the following command:

```bash
./gradlew test
```

## Contributing

Contributions are welcome! Please follow these guidelines when contributing:

- **Code Quality:** Write clean, maintainable, and well-documented code.
- **Conventional Commits:** Use [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) for all commit messages.
- **Pull Requests:** Create a pull request with a clear description of your changes.

---

*This README was updated by an AI assistant.*
