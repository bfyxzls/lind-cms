FROM fabric8/java-jboss-openjdk8-jdk:1.4
USER root
RUN yum install kde-l10n-Chinese -y
RUN yum install glibc-common -y
RUN localedef -c -f UTF-8 -i zh_CN zh_CN.utf8
ENV LC_ALL=zh_CN.UTF-8
COPY  ./build/libs/cms-boot-0.2-SNAPSHOT.jar /deployments/app.jar
