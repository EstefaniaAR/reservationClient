# reservationClient
Part of a spring cloud sample. Tutorial link : https://www.youtube.com/watch?v=ZyK5QrKCbwM

## Reservation Client 
Client which consumes the microservices 

## Requirements 
- Spring Boot
- Java 8
- Config server from :https://github.com/EstefaniaAR/testConfigServer.git
- Reservation service from: https://github.com/EstefaniaAR/testReservationService.git
- Eureka Service: https://github.com/EstefaniaAR/testEurekaService.git
- Discovery Service: https://github.com/EstefaniaAR/testDiscoveryService.git

## Dependencies
      <dependencies>
        <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
          <groupId>org.springframework.cloud</groupId>
          <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
          <groupId>org.springframework.cloud</groupId>
          <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>

        <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-test</artifactId>
          <scope>test</scope>
        </dependency>
      </dependencies>
      
   ## Run 
   Run the main Class as Spring Boot Application
   
   ## Query
   Post through Advanced REST client extension of Google Chrome
   - Method Post : http://localhost:9999/reservations
   - Body: {"reservationName":"Viktor"}
   - Body Content Type: application/json
   Get 
   http://localhost:9999/reservations/names
