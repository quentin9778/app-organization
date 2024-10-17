DROP TABLE IF EXISTS "tasks";
DROP TABLE IF EXISTS "categories";

CREATE SEQUENCE IF NOT EXISTS categories_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE "categories"(
    "id" bigint DEFAULT nextval('categories_id_seq') NOT NULL,
    "name" text,
    "user_id" text NOT NULL,
    CONSTRAINT "categories_pkey" PRIMARY KEY("id")
    );

CREATE TABLE "tasks"(
    "id" bigint DEFAULT nextval('tasks_id_seq') NOT NULL,
    "name" text,
    "tag" text,
    "status" text,
    "date_planned" date,
    "date_done" date,
    "category_id" bigint,
    "user_id" text NOT NULL, -- ID utilisateur mais sans FK

    CONSTRAINT "tasks_pkey" PRIMARY KEY("id"),
    CONSTRAINT "fk_categories" FOREIGN KEY(category_id)
    REFERENCES categories(id)
    );
