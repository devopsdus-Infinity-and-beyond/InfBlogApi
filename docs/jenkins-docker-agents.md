# Jenins with docker agents

In the declarative Pipeline:

```Jenkinsfile
agent {
    docker {
        image 'maven:3.6.3-adoptopenjdk-14'
    }
}
```

To start the ci toolchain run:

```zsh
cd ci && docker-compose up -d && cd ..
```

## Plugins

- pipeline plugins
- docker-pipeline plugin
- github plugin
- Optional: Install BlueOcean for better UX.

## Setup

- Add your github token to the jenkins credentials using the userame/password credential type
- Setup Jenkins access to Nexus
    - Run docker exec -it ci_nexus_1 cat /nexus-data/admin.password to get the adin password
    - Login to Nexus and change the password
    - Add the user to Jenins credentials as username/password credential type with id: nexus
- Add a multibranch pipeline job; use github as source.
