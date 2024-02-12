# Shop Application

Welcome to the Shop application! This is a web-based e-commerce platform built using Spring Boot framework. Below are the instructions on how to start the application and a brief overview of its functionality.

## Overview

The Shop application is an e-commerce platform that allows users to browse products, add them to their cart, place orders, and view their order history. It includes features such as user authentication, product listing, cart management, and order processing.

## Getting Started

To run the Shop application locally, follow these steps:

1. **Clone Repository**: Clone this repository to your local machine using Git.

   ```bash
   git clone <repository_url>
   ```

2. **Build Project**: Navigate to the project directory and build the project using Maven.

   ```bash
   cd Shop
   mvn clean package
   ```

3. **Run Application**: Run the Spring Boot application using the following Maven command.

   ```bash
   mvn spring-boot:run
   ```

4. **Access the Application**: Once the application is running, access it in your web browser at [http://localhost:8080](http://localhost:8080).

## Application Features

The Shop application provides the following features:

- **User Authentication**: Users can sign up for an account or sign in using their credentials.
- **Product Listing**: Users can browse through a list of available products.
- **Add to Cart**: Users can add products to their shopping cart for later purchase.
- **View Cart**: Users can view the items in their shopping cart and proceed to checkout.
- **Place Order**: Users can place orders for the items in their cart and provide shipping details.
- **Order History**: Users can view their past orders and order details.

## Technologies Used

The Shop application is built using the following technologies:

- **Spring**: Java-based framework for building web applications.
- **MySQL**: Relational database management system for storing application data.
- **Thymeleaf**: Template engine for server-side HTML rendering.
- **Bootstrap**: Front-end framework for responsive web design.
- **Hibernate**: Object-relational mapping library for database interaction.
- **iText PDF**: Library for generating PDF documents.

## Contributing

Contributions to the Shop application are welcome! Feel free to fork the repository, make changes, and submit pull requests.

## License

This project is licensed under the [MIT License](LICENSE).
