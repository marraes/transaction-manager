INSERT INTO transaction_type(idt_transaction_type, dat_created, dat_updated, cod_transaction_type, des_transaction_type, ind_operation)
VALUES ('34ce27bb-88ee-47f5-8f91-c46b94d13204', NOW() AT TIME ZONE 'UTC', NOW() AT TIME ZONE 'UTC', 1, 'PURCHASE', 1),
    ('a614bc91-920f-4cec-8a6b-0871570ecb08', NOW() AT TIME ZONE 'UTC', NOW() AT TIME ZONE 'UTC', 2, 'WITHDRAW', 1),
    ('219f00f8-5415-4188-81b5-2e7b08949263', NOW() AT TIME ZONE 'UTC', NOW() AT TIME ZONE 'UTC', 3, 'PAYMENT', 2)
;

COMMIT;
