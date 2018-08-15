# Healthcare-Interoperability-App

Main design considerations:
a). Space complexity & Time complexity (Scalability);  DB Consistency; Maintainable, consistent application & easy error spotting.


1). Space complexity: 
	-- One place storage for PHR (in the form of PatientDetails object). No unnecessary data will occupy the cache when fetching a specific PHR. Storing a single object is much cheaper than storing multiple records in a mysql table.

2). Time Complexity (Hibernate effectiveness):
	-- No unnecessary joins required (p_id provided in all meta tables). If not for the design, multiple tables would have needed to be joined to fetch one record. Unnecessary storage and time consumption.
	-- Moreover, JPA (EntityManagers) allows in-memory persistence of objects as well as creates, updates and deletes in batches. This improves the performance compared to creating new JDBC connections & accessing data.
	-- PHR for a patient is fetched in a range of 5-20 msec.
	-- Refined search is performed in a range of 5-50 msec (post caching).

3). Maintainable, consistent application & easy error spotting (MVC & JPA effectiveness):
	-- No external DB connections. All connections were established using JPA. Used the persistence api itself (with named native queries, criteria API) to access, create, manage, delete database elements. IT ALSO MAKES THE APPLICATION EXTREMELY CONSISTENT.

4). Consistency:
	-- The database needs to be ACID compliant. Hence, MySQL.


