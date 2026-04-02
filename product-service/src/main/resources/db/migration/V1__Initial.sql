CREATE TABLE product
(
    id          UUID         NOT NULL,
    name        VARCHAR(50)  NOT NULL,
    description VARCHAR(500),
    price       DECIMAL      NOT NULL,
    stock       INTEGER      NOT NULL,
    category    VARCHAR(255),
    image_url   VARCHAR(255),
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    updated_at  TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_product PRIMARY KEY (id)
);