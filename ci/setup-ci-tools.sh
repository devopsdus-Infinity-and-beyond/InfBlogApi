docker run -d -p 8081:8081 --name nexus sonatype/nexus3:latest
docker run -d --name jenkins -v /var/run/docker.sock:/var/run/docker.sock --group-add=$(stat -c %g /var/run/docker.sock) -v jenkins_home:/var/jenkins_home -v /usr/bin/docker:/usr/bin/docker -p 8080:8080 jenkins/jenkins
docker network create tools
docker network connect tools jenkins
docker network connect tools nexus