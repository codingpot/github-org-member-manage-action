# github-org-member-manage-action

## Objective

Mange GitHub org memberships in a declarative way (.yaml).
YAML will be the single source of truth for the org memberships.

```yaml
# members.yaml
org_name: codingpot

admins:
  - kkweon
  - kkweon2

members:
  - deepdiver1
  - deepdiver2
```

## How to use

```yaml
steps:
  - uses: "codingpot/github-org-member-manage-action:v0"
    with:
      gh_token: ${{ secrets.GH_TOKEN }} # NEEDS ORG RW PERMISSION
      members_filepath: members.yaml
      # OR you could define in the action file.
      members_filecontent: |
        org_name: your_org_name
        members:
        - kkweon1
        admins:
        - deep-diver
```
