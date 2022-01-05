FROM gradle:jdk8 as builder

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle installDist

FROM openjdk:8-jdk
EXPOSE 8080:8080
RUN mkdir /app
COPY --from=builder /home/gradle/src/build/install/dev.juanrincon.strongerapi/ /app/
WORKDIR /app/bin
CMD ["./dev.juanrincon.strongerapi"]