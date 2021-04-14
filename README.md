# quarkus-demo
A simple sample for AWS Api Gateway and Lambda Proxy Integration

POST {api_endpoint}/{resource}?action=XXX
Body 
```
{
   "key": "val"
   ...
}
```



## Preconditions
* JDK 11
* Apache Maven
* GraalVM or docker

## How to build
```
mvn clean package -Pnative -DskipTests=true
```
then upload ./target/function.zip to your lambda

