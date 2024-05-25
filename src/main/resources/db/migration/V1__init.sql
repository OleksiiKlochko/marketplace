create table product
(
    id   uuid default gen_random_uuid() not null primary key,
    name text
);

create table product_order
(
    id         uuid default gen_random_uuid() not null primary key,
    product_id uuid references product
);
