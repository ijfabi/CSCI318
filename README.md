# CSCI318
The Auction MicroService has three services: AuctionSystem, BidSystem and RealEstateSystem. First the Project Folder has to be downloaded and put through an IDE. This project has the dependencies: H2, WEB and JPA. Once, the files are loaded in, run AuctionSystemApplication, BidSystemApplication, RealEstateApplication together. Make sure they are running on three different ports. This can be accessed on application.properties under resources.



REST Request : 
curl -X POST "http://localhost:8082/realEstateCos" \
-H "Content-Type:application/json" \
-d '{
  "name": "Excalibur Properties",
  "address": "123 King Arthur Road",
  "phone": "123-456-7890",
  "email": "info@excaliburprops.com"
}'
Output on Terminal :
{"companyId":2,"name":"Excalibur Properties","address":"123 King Arthur Road","phone":"123-456-7890","email":"info@excaliburprops.com"}%    


REST Request for Use Case 2:
curl -X POST "http://localhost:8081/buyers" \                                                                                  
-H "Content-Type: application/json" \
-d '{                 
  "firstName": "John",
  "lastName": "Doe",      
  "phone": "123-456-7890",     
  "address": "123 Main Street", 
  "email": "johndoe@example.com"
}'                                                                                                                                                          
Output on Terminal for Use Case 2:
{"buyerId":1,"firstName":"John","lastName":"Doe","phone":"123-456-7890","address":"123 Main Street","email":"johndoe@example.com"}% 

REST Request For Use Case 3:
curl -X POST "http://localhost:8082/realEstateCos/1/agents" \                                                                   
-H "Content-Type:application/json" \
-d '{                       
  "firstName": "Arthur",      
  "lastName": "Pendragon",     
  "email": "arthur@camelot.bt",  
  "phone": "234-567-8901",  
  "dob": "1985-05-15",  
  "gender": "M"
}’                                                                                                                                             

Output on Terminal for Use Case 3:
{"employeeNo":3,"firstName":"Arthur","lastName":"Pendragon","email":"arthur@camelot.bt","phone":"234-567-8901","dob":"1985-05-15T00:00:00.000+00:00","gender":"M"}

REST Request Use Case 4:
		 	 	 		
curl -X POST -H "Content-Type:application/json" -d '{"name":"42 Lombok Drive Wollongong", "startDate":"2024-09-13", "finishDate":"2024-09-20", "currentPrice":345543, "status":"active"}' http://localhost:8080/auctions 

Output on Terminal for Use Case 4:


{"auctionId":3,"name":"42 Lombok Drive Wollongong","startDate":"2024-09-13T00:00:00.000+00:00","finishDate":"2024-09-20T00:00:00 .000+00:00","currentPrice":345543.0,"status":"active"}% 

