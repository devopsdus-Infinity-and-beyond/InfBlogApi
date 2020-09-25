# This is a CI/CD Demo with Jenkins

Using the local docker engine for everything, but can be easy changed to use a remote docker cluster.

- runs the ci/cd toolchain
- runs docker agents for each stage as docker containers
- runs the app itself with docker-compose to do component tests

## Tools used

- docker
- docker-compose
- nexus
- jenkins
- spring boot with maven modules. The repo itself is the aggregate for all submodules
- Newman/Postman for component tests of the Restful API
- docker registry

[Jenkins Setup](docs/jenkins.md)

## TODOs

- Automate Jenkin configuration
- Add Sonarqube and coberture
- docker push to registry:5000

## FAQ

[faq](docs/faq.md)
