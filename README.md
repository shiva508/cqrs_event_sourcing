# event_driven_microservices

Learn to build distributed Event-driven Microservices, CQRS, Event Sourcing, SAGA, Transactions

SERVICE-1
name=epooldiscovery
port=8000
URL=http://localhost:8000/

SERVICE-2
name=epoolgateway
port=8001

SERVICE-3
name=productservice
port=8002

GATEWAY=http://localhost:8001/productservice/v1/product/getproduct

DOCKER 
MONGO_DB:sudo docker run -it -d --name mongo-container -p 27017:27017 --network shivanetwork --restart always -v mongodb_data_container:/data/db mongo:latest
