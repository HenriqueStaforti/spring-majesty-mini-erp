-- Criação da tabela de usuários
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de papeis de usuário
CREATE TABLE IF NOT EXISTS user_roles (
    user_id BIGINT NOT NULL,
    role VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_id, role),
    CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

-- Criação da tabela de clientes
CREATE TABLE IF NOT EXISTS clients (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    address TEXT,
    city VARCHAR(100),
    state VARCHAR(50),
    district VARCHAR(100),
    zip_code VARCHAR(20),
    document VARCHAR(50),
    enabled BOOLEAN DEFAULT true,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE
);

-- Criação da tabela de produtos
CREATE TABLE IF NOT EXISTS products (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(50) UNIQUE,
    name VARCHAR(255) NOT NULL,
    image_url TEXT,
    expiration_days INTEGER NOT NULL DEFAULT 0,
    unit_of_measure VARCHAR(20) NOT NULL,
    list_price DECIMAL(10, 2) NOT NULL,
    sale_price DECIMAL(10, 2) NOT NULL,
    enabled BOOLEAN DEFAULT true,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE
);

-- Criação da tabela de pedidos
CREATE TABLE IF NOT EXISTS orders (
    id BIGSERIAL PRIMARY KEY,
    client_id BIGINT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    submitted_date TIMESTAMP WITH TIME ZONE,
    payment_type VARCHAR(20) NOT NULL,
    payment_term VARCHAR(100),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE,
    CONSTRAINT fk_order_client FOREIGN KEY (client_id) REFERENCES clients (id)
);

-- Criação da tabela de itens do pedido
CREATE TABLE IF NOT EXISTS order_item (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    unit_of_measure VARCHAR(20) NOT NULL,
    quantity INTEGER NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_order_item_order FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    CONSTRAINT fk_order_item_product FOREIGN KEY (product_id) REFERENCES products (id)
);

-- Índices para melhorar desempenho
CREATE INDEX idx_clients_document ON clients(document);
CREATE INDEX idx_products_code ON products(code);
CREATE INDEX idx_orders_client_id ON orders(client_id);
CREATE INDEX idx_order_item_order_id ON order_item(order_id);
CREATE INDEX idx_order_item_product_id ON order_item(product_id);

-- Comentários para documentação
COMMENT ON TABLE clients IS 'Armazena informações dos clientes';
COMMENT ON TABLE products IS 'Armazena os produtos disponíveis para venda';
COMMENT ON TABLE orders IS 'Registra os pedidos de venda';
COMMENT ON TABLE order_item IS 'Itens que compõem cada pedido de venda';

-- Inserção de dados iniciais (opcional)
INSERT INTO users (username, password, created_at)
VALUES ('admin', '$2y$10$3PPbGI4nxng5CZEvl/JNouPSECfQrUeIW9YHrMzdgawoP8DFqI67S', CURRENT_TIMESTAMP);

INSERT INTO user_roles (user_id, role) 
VALUES ((SELECT id FROM users WHERE username = 'admin'), 'ROLE_ADMIN');
