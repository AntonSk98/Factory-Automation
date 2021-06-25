Factory Automation

This project represents a component-based management system for factory automation.

The company for which the system is developed provides customizable 3D printing services.

Customers must first have registered and must be approved. Afterward, they can upload 3D printing jobs.
The company provides multiple different types of 3D printers and different types of finishing procedures.
Depending on the concrete order of an individual customer, a process is generated.
In such a process, multiple different machines are involved. The company also uses mobile robotic platforms to move parts between the individual machines.
Furthermore, the machines are equipped with robot arms to move the parts between the machine and the mobile robot. The robots act autonomously but receive their tasks from a central process management system. After the production process finished, the product is transported to a central store and shipped to the customer. The customer also receives an invoice. Invoices are managed by a central invoicing system.

-----------------------------------------------------

Project structure:

1) JAVA SE application client that communicates with the CRM management server
2) CRM management server which is responsible for customer registration, customer administration, order creation, shipping process, and invoice generation process. It is a separate deployment unit that is deployed on the WildFly server.
3) Factory Production server is responsible for product creation.
4) JAVA SE application uses JNDI lookups to interact with the CRM management server and invoke respective calls
5) The CRM management and the Factory production server interact with each other via HTTP protocol
6) Data storage is organized using PostgreSQL database (see .xml configs in META-INF files)

-----------------------------------------------------

Project launch:

1) Start JAVA SE app (provide proper configuration to connect to the CRM management system in jboss-ejb-client.properties)
2) Deploy the CRM management on Wildfly (or any other) server.
3) Deploy the Factory Production on another WildFly server instance.

-----------------------------------------------------

Technology stack:

Java SE, EJB 3.0, Postgresql

-----------------------------------------------------

Extra info:

In case you have some extra projects regarding this project, do not hesitate to contact me via email.
Email: antonskripin@gmail.com
