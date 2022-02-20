INSERT INTO document_type (idt_document_type, dat_created, dat_updated, cod_document_type, des_document_type)
VALUES ('1ccc4d50-7f62-410d-a2ff-3318cfc9c574', NOW() AT TIME ZONE 'UTC', NOW() AT TIME ZONE 'UTC', 1, 'ID'),
    ('99e0ee49-6187-4ee5-92f8-814e6bc72627', NOW() AT TIME ZONE 'UTC', NOW() AT TIME ZONE 'UTC', 2, 'CPF'),
    ('982a5ae7-4b1d-4378-a115-34263d7111d8', NOW() AT TIME ZONE 'UTC', NOW() AT TIME ZONE 'UTC', 3, 'CNPJ'),
    ('5a7059a9-12c6-4df0-b7e9-ebb164e42635', NOW() AT TIME ZONE 'UTC', NOW() AT TIME ZONE 'UTC', 4, 'PASSPORT');

COMMIT;
