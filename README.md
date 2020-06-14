for any support contact me : karim.salah.kotb@gmail.com

# hibernate
What is Hibernate:
Hibernate is one of the most extensively used ORM (Object/Relational mapping) tool for Java apps
It provides a framework for mapping an object-oriented domain model to a relational database
Its purpose is to relieve the developer from a significant amount of relational data persistence-related programming tasks
 

Here are the most important features of Hibernate :
Its provides simple querying of data
It minimizes database access with smart fetching strategies
It saves and retrieves your domain objects with out writing any SQL queries
It lets you make changes to table name and columns
It centralizes pre save and post retrieve logic
It creates complex joins for retrieving related items
It creates schema from object model
It does not require any application server to operate
It manipulates complex associations of objects of your database
It also aids in coming up with the novel and innovative solutions on caching, proxy as well as lazy loading which considerably mends the performance graph of your Java Web application
Related objects can be configured to cascade operations from one to the other. For example, a parent object can be configured to cascade its save and/or delete operation to its child objects

 
It provides an SQL inspired language called Hibernate Query Language (HQL) that allows SQL-like queries to be written against Hibernate’s data objects
HQL (Hibernate Query Language) is the object-oriented version of SQL. It generates database independent queries so that there is no need to write database-specific queries. Without this capability, changing the database would require individual SQL queries to be changed as well, leading to maintenance issues
Criteria Queries are provided as an object-oriented alternative to HQL. Criteria Query is used to modify the objects and provide the restriction for the objects

Hibernate Core interfaces
The five core interfaces that are used in Hibernate application to store and retrieve persistent objects and control transactions are:

Session interface
SessionFactory interface
Configuration interface
Transaction interface
Query and Criteria interfaces

Configuration Object
The Configuration object is the first Hibernate object you create in any Hibernate application. It is usually created only once during application initialization. It represents a configuration or properties file required by the Hibernate.

The Configuration object provides two keys components −
Database Connection − This is handled through one or more configuration files supported by Hibernate. These files are hibernate.properties and hibernate.cfg.xml
Class Mapping Setup − This component creates the connection between the Java classes and database tables
ServiceRegistry : The central service API, aside from the services themselves, is the org.hibernate.service.ServiceRegistry interface. The main purpose of a service registry is to hold, manage and provide access to services.

SessionFactory Object
Configuration object is used to create a SessionFactory object which in turn configures Hibernate for the application using the supplied configuration file and allows for a Session object to be instantiated
The SessionFactory is a thread safe object and used by all the threads of an application
The SessionFactory is a heavyweight object; it is usually created during application start up and kept for later use
You would need one SessionFactory object per database using a separate configuration file. So, if you are using multiple databases, then you would have to create multiple SessionFactory objects
The application obtains Session instances from a SessionFactory
There is typically a single SessionFactory for the whole application during application initialization
The SessionFactory caches general SQL statements and other mapping metadata that Hibernate uses at runtime
It also holds cached data that has been read in one unit of work and may be reused in a future unit of work
SessionFactory sessionFactory = configuration.buildSessionFactory();

Session Object
A Session is used to get a physical connection with a database
The Session object is lightweight and designed to be instantiated each time an interaction is needed with the database
Persistent objects are saved and retrieved through a Session object
The session objects should not be kept open for a long time because they are not usually thread safe and they should be created and destroyed them as needed.
The lifecycle of a Session is bounded by the beginning and end of a logical transaction. The main function of the Session is to create, read and delete operations for instances of mapped entity classes.

Instances may exist in one of three states:

transient: never persistent, not associated with any Session
persistent: associated with a unique Session
detached: previously persistent, not associated with any Session
Session session = sessionFactory.openSession();

Here is the list of methods which will be used very often in Hibernate implementations:

beginTransaction()
Begin a unit of work and return the associated Transaction object. This method needs to be called if you want to enable transaction, and once your DB interactions are done, call commit() method on the returned transaction object. Incase of any issues, call rollback() error on the transaction object.

save()
Persist the given transient instance, first assigning a generated identifier. This method stores the given object in the database. Before storing, it checks for generated identifier declaration and process it first, then it will store into DB.

update()
Update the persistent instance with the identifier of the given detached instance. It updates the database record.

saveOrUpdate()
Either save(Object) or update(Object) the given instance, depending upon resolution of the unsaved-value checks. This operation cascades to associated instances if the association is mapped with cascade=”save-update”.

createQuery()
Create a new instance of Query for the given HQL query string.

createSQLQuery()
Create a new instance of SQLQuery for the given SQL query string.

merge()
Copy the state of the given object onto the persistent object with the same identifier. If there is no persistent instance currently associated with the session, it will be loaded. Return the persistent instance. If the given instance is unsaved, save a copy of and return it as a newly persistent instance. The given instance does not become associated with the session. This operation cascades to associated instances if the association is mapped with cascade=”merge”.

persist()
Make a transient instance persistent. This operation cascades to associated instances if the association is mapped with cascade=”persist”.

flush()
Force this session to flush. Must be called at the end of a unit of work, before committing the transaction and closing the session. Flushing is the process of synchronizing the underlying persistent store with persistable state held in memory.

delete()
Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving Session or a transient instance with an identifier associated with existing persistent state. This operation cascades to associated instances if the association is mapped with cascade=”delete”.

Transaction Object
A Transaction represents a unit of work with the database and most of the RDBMS supports transaction functionality. Transactions in Hibernate are handled by an underlying transaction manager and transaction (from JDBC or JTA).

 

This is an optional object and Hibernate applications may choose not to use this interface, instead managing transactions in their own application code.
Transaction management is the process of managing a set of statements or commands. In hibernate; transaction management is done by transaction interface as shown in below code:

 
    Session s = null;
    Transaction tr = null;
    try {
       s = sessionFactory.openSession();
       tr = s.beginTransaction();
       doTheAction(s);
       tr.commit();
    } catch (RuntimeException exc) {
      tr.rollback();
    } finally {
      s.close();
    }
Query Object
Query objects use SQL or Hibernate Query Language (HQL) string to retrieve data from the database and create objects
A Query instance is used to bind query parameters, limit the number of results returned by the query, and finally to execute the query
The Hibernate Query object is used to retrieve data from database. You can use either SQL or Hibernate Query Language (HQL)
A Query instance is obtained by calling Session.createQuery().
The Query object is used to bind query parameters, limit query results and execute the query.
Here is the list of Query methods which will be used very often in Hibernate implementations:

list()
Return the query results as a List. If the query contains multiple results pre row, the results are returned in an instance of Object[].

executeUpdate()
Execute the update or delete statement. It returns the number of entities updated or deleted.

setParameter()
Bind a value to a JDBC-style query parameter. The Hibernate type of the parameter is first detected via the usage/position in the query and if not sufficient secondly guessed from the class of the given object.

uniqueResult()
Convenience method to return a single instance that matches the query, or null if the query returns no results.

setMaxResults()
Set the maximum number of rows to retrieve. If not set, there is no limit to the number of rows retrieved.

setFirstResult()
Set the first row to retrieve. If not set, rows will be retrieved beginning from row 0.

Criteria Object
Criteria objects are used to create and execute object oriented criteria queries to retrieve objects.

The second-level cache is the SessionFactory based cache and is mainly responsible for caching objects across sessions.


How to configure hibernate with an application
Load the Hibernate configuration file and create configuration object
Create session factory from configuration object
Get one session from this session factory
Create HQL Query
Execute query to get list containing Java objects

Summary
Key points
The foremost difference between save() and persist() system is that save returns the result of a Serializable object while the return type of persisting () system is void, so it doesn’t yield anything as such
The key contrast amongst getting () and load() technique is that load() will throw an exception if id passed to them aren’t found, however, get() will return null. Another imperative distinction is that load can return intermediary without hitting the database unless required (when you get to any characteristic other than id) yet get() dependably go to the database, so once in a while utilizing load() can be quicker than the get() technique. It bodes well to utilize the load() technique on the off chance that you know the question exists yet toget() strategy if you don’t know about object’s presence
There are three types of inheritance models in Hibernate:
Table per class hierarchy
Table per subclass
Table per concrete class
Hibernate provides dirty checking feature which can be used to reduce database write times. Dirty checking feature of hibernate updates only those fields which require a change while keeps others unchanged
Callback interfaces of hibernate are useful in receiving event notifications from objects. For example, when an object is loaded or deleted, an event is generated and notification is sent using callback interfaces
Hibernate provides following four ways to fetch objects from database:
Using HQL
Using identifier
Using Criteria API
Using Standard SQL
Hibernate supports four different fetching strategies:
Join Fetching
Batch Fetching
Select Fetching
Sub-select Fetching
The first level cache is preserved at the Session level
The second level cache is maintained at a SessionFactory level and is shared by all the sessions.
 

