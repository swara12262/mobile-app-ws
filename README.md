# mobile-app-ws

Spring project built from udemy course reference.

https://www.udemy.com/course/restful-web-service-with-spring-boot-jpa-and-mysql/learn/lecture/10210344#overview


Spring Udemy course notes:

1. To create new spring application quickly use: https://start.spring.io/
1. Crate user controller class - controller
@RestController - to make class controller to reciver http requests.
@RequestMapping - USed at class level for controller to map with the methods defined in class
@GetMapping
@PostMapping
@PutMapping
@DeleteMapping

Web dependency is added intially to make our application web app with built in tomcat

2) To communicate our api with database update pom.xml with below for mysql database:

Got to https://mvnrepository.com/ where you can find all dependency required for project

To connect to mysql database from command line 
1. Got to C:\Program Files\MySQL\MySQL Server 8.0\bin path 
2. Use cmd mysql.exe –u<username> –p
user: root pass:Swara@12262
user:swara pass:swara

Add following in .properties file to  make our application to be able to communicate with your databse
spring.datasource.data-username=swara
spring.datasource.data-password=swara
spring.datasource.url=jdbc:mysql://localhost:3306/photo_app
spring.jpa.hibernate.ddl-auto=update    //it will automatically update tables


3) Created userrequest model to convert incoming json to Java object  ---com.app.ws.ui.model.request (Used by user facing layer that is in controller)
4) To return reponse has saved in databse crate com.app.ws.ui.model.reponse
5) Create UserDto which will contains both request and respose info which can be used across all the layers(Service database, user facing layers)

6) UserDto is passed to UserService and userservice will perform service logic on this object and then this useDto will be used in business logic to prepare
entity class whcih is actullay stored in database.

USerEntity class : object to persist in database
UserRepository class: takes UserEntity class and stores data into databse

CrudRepository interface defines all buit in funcitons like save delete user details
	UserEntity findByEmail(String email); no need to defince implementation as spring automatically resolves that method is used to find record with unique email.

Utils class to generate secure public user id and annaotate it with @Component as we're going  to autowire it in userservice implementation.

Annotations to look :
@Service 
@Repository 
@Controller
@Component -- Create object and store inside ioc container.

*To perform crud operations:
<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

*To provide xml support for reponse object when get user methods is called.
<dependency>
		<groupId>com.fasterxml.jackson.dataformat</groupId>
		<artifactId>jackson-dataformat-xml</artifactId>
</dependency>


Run app using command line:
Install maven
mvn install - to build app and it will also run unit tests
mvn spring-boot:run - this command will run the app in apache servlet container

77. Run app as java application
mvn install - to build app and it will also run unit tests this will generate exceutable jar file
Run using jar file in target folder of application using command.
java -jar <jar file name>
jar file has in built apache tomcat servlet on which it runs app but sometimes we need to run multiple apps under exusting or local 
tomcat then we create war file.

74: WAR(Web Application Archive) so that 
