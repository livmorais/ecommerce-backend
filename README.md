# Ecommerce

The project is a web application developed in Java using the Spring Boot framework. It serves as an e-commerce platform, allowing users to buy products online. Below are the main features and endpoints of the application:

## Technologies Used
- Language: Java
- Framework: Spring Boot
- Database: MySQL
- Security: JWT for authentication and authorization.

## Endpoints
- `/products` (GET): Returns a list of available products for purchase.
- `/auth/login` (POST): Allows users to log in and generates an authentication token and permissions.
- `/auth/register` (POST): Allows users to register and choose their role as a seller or buyer.
- `/api/products` (GET): Sellers can view their registered products.
- `/api/products/{productId}` (GET): Allows sellers to view a specific product by its ID.
- `/api/products` (POST): Sellers can add new products.
- `/api/products/{productId}` (PUT): Sellers can update the information of a specific product.
- `/api/products/{productId}` (DELETE): Sellers can delete a specific product.
- `/cart` (GET): Retrieves the user's shopping cart.
- `/cart/{productId}` (POST): Adds a product to the user's shopping cart.
- `/cart/{cartItemId}` (PUT): Updates the quantity of a product in the user's shopping cart.
- `/cart/{cartItemId}` (DELETE): Removes a product from the user's shopping cart.
- `/checkout/success` (GET): Returns the details of completed purchases.
- `/checkout` (POST): Initiates the checkout process.
- `/api/sales` (GET): Sellers can check their sales and view detailed information to process deliveries.


## Functionalities
- User Registration: Users can register on the platform and select their role as a seller or buyer.
- User Authentication: The application uses JWT for user authentication and authorization.
- Shopping Cart Management: Users can add, update, and remove products from their shopping cart.
- Checkout Process: Users can initiate the checkout process to complete their purchases.
- Sales Tracking: Sellers can view their sales and detailed information for order processing.
- Product Management: Sellers can manage their registered products, including adding, updating, and deleting products.

## Additional Notes
- The project is organized into logical packages, including controllers, services, domain entities, and security configurations.
- The `TokenService` class is responsible for generating and validating JWT tokens.
- The `SecurityFilter` is a custom filter used to intercept requests and validate JWT tokens for user authentication.
- Critical functionalities are protected by authentication, ensuring that only authenticated users can perform certain actions.


# Ecommerce - PT-BR

O projeto é uma aplicação web desenvolvida em Java utilizando o framework Spring Boot. Ele funciona como uma plataforma e-commerce, permitindo que os usuários comprem produtos online. Abaixo estão as principais funcionalidades e endpoints da aplicação:

## Tecnologias Utilizadas
- Linguagem: Java
- Framework: Spring Boot
- Banco de Dados: MySQL
- Segurança: JWT para autenticação e autorização.

## Endpoints
- `/products` (GET): Retorna uma lista de produtos disponíveis para compra.
- `/auth/login` (POST): Permite que os usuários façam login e gera um token de autenticação e permissões.
- `/auth/register` (POST): Permite que os usuários se cadastrem e escolham sua função como vendedor ou comprador.
- `/cart/{productId}` (POST): Adiciona um produto ao carrinho de compras do usuário.
- `/cart/{cartItemId}` (PUT): Atualiza a quantidade de um produto no carrinho de compras do usuário.
- `/cart/{cartItemId}` (DELETE): Remove um produto do carrinho de compras do usuário.
- `/cart` (GET): Retorna o carrinho de compras do usuário.
- `/checkout/success` (GET): Retorna os detalhes das compras concluídas.
- `/checkout` (POST): Inicia o processo de checkout.
- `/api/sales` (GET): Vendedores podem verificar suas vendas e visualizar informações detalhadas para processar entregas.
- `/api/products` (GET): Vendedores podem visualizar os produtos cadastrados por eles.
- `/api/products/{productId}` (GET): Permite que os vendedores visualizem um produto específico por meio de seu ID.
- `/api/products` (POST): Vendedores podem adicionar novos produtos.
- `/api/products/{productId}` (PUT): Vendedores podem atualizar as informações de um produto específico.
- `/api/products/{productId}` (DELETE): Vendedores podem deletar um produto específico.

## Funcionalidades
- Cadastro de Usuários: Os usuários podem se cadastrar na plataforma e selecionar sua função como vendedor ou comprador.
- Autenticação de Usuários: A aplicação utiliza JWT para autenticação e autorização de usuários.
- Gerenciamento do Carrinho de Compras: Os usuários podem adicionar, atualizar e remover produtos de seu carrinho de compras.
- Processo de Checkout: Os usuários podem iniciar o processo de checkout para concluir suas compras.
- Rastreamento de Vendas: Vendedores podem ver suas vendas e informações detalhadas para o processamento de pedidos.
- Gerenciamento de Produtos: Vendedores podem gerenciar seus produtos cadastrados, incluindo adicionar, atualizar e deletar produtos.

## Notas Adicionais
- O projeto está organizado em pacotes lógicos, incluindo controllers, serviços, entidades de domínio e configurações de segurança.
- A classe `TokenService` é responsável por gerar e validar tokens JWT.
- O `SecurityFilter` é um filtro personalizado usado para interceptar requisições e validar tokens JWT para autenticação do usuário.
- Funcionalidades críticas são protegidas por autenticação, garantindo que apenas usuários autenticados possam executar determinadas ações.


