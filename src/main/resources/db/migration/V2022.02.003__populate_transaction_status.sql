INSERT INTO transaction_status(idt_transaction_status, dat_created, dat_updated, cod_transaction_status, des_transaction_status)
VALUES ('50ea4ebb-b689-489d-b94b-4c518ad8ffa5', NOW() AT TIME ZONE 'UTC', NOW() AT TIME ZONE 'UTC', 1, 'REGISTERED'),
    ('9e971bd7-07d5-497c-be32-353a797fa0f2', NOW() AT TIME ZONE 'UTC', NOW() AT TIME ZONE 'UTC', 2, 'PERFORMED'),
    ('5080d59f-ec4a-42ba-aa96-9307f8dfa32a', NOW() AT TIME ZONE 'UTC', NOW() AT TIME ZONE 'UTC', 3, 'PENDING_CONFIRMATION'),
    ('b813c125-6d8e-4e7f-a6cc-926d0e965438', NOW() AT TIME ZONE 'UTC', NOW() AT TIME ZONE 'UTC', 4, 'CONFIRMED'),
    ('cbc96c48-76b8-453b-a8ec-7f14cb180afb', NOW() AT TIME ZONE 'UTC', NOW() AT TIME ZONE 'UTC', 5, 'DENIED'),
    ('b975329c-ca31-416f-b165-dba64564b1c8', NOW() AT TIME ZONE 'UTC', NOW() AT TIME ZONE 'UTC', 6, 'CANCELED')
;

COMMIT;
