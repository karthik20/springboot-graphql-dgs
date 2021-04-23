**Spring boot with DGS**
https://netflix.github.io/dgs/

***Setup***:

`./gradlew clean build`

The above build task would invoke the default generateJava task wiht custom args that would auto generate(Query API codegen) the domain POJOs and client query API.

 - Start the spring boot application
 - Visit http://localhost:8080/graphiql
 - See the docs and query the shows with title filter

 ```graphql
{
  shows(titleFilter: "Co") {
    title
    releaseYear
  }
}
 ```
