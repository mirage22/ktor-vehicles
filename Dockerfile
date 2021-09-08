FROM openjdk:15-jdk
EXPOSE 8080:8080
RUN mkdir /app
COPY ./build/install/ktor-vehicles/ /app/
WORKDIR /app/bin
CMD ["./ktor-vehicles"]