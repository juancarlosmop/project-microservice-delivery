Microservice proyect 
This proyect contain 3 microservices user,product and delivery,Also contains the basic structure about microservices.

In this project I used the main concepts to create a roubust infrestructure,then We can find a complete project whit the following features:
    
    - Eureka Server
    - Gateway
    - Feign client
    - REDIS
    - TDD

END POINT
    Due to gateway we only coun with one port which is 8080
    and one end point 
     
     /user
        /create-user
        /get-user Note:We need to acces in this end point,bacause the data will storage in  Redis after that it will be use to create a deliver
        /get-users
    /product    
        /create-product
        /get-product/{id_product
        /get-products
    /delivery
        /create-bill-delivery
        /get-delivery/{id_delivery}
