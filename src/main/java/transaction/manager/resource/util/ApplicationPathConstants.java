package transaction.manager.resource.util;

public final class ApplicationPathConstants {

    private ApplicationPathConstants() {
    }

    //region ROOT
    public static final String ROOT = "/";
    public static final String API_ROOT = "/api";
    //endregion

    //region DOCUMENT-TYPES
    public static final String DOCUMENT_TYPE_RESOURCE = "/document-types";
    //endregion

    //region CUSTOMERS
    public static final String CUSTOMER_ID_PARAM = "customerId";
    public static final String CUSTOMER_RESOURCE = "/customers";
    public static final String CUSTOMER_ID_RESOURCE = CUSTOMER_RESOURCE + "/{" + CUSTOMER_ID_PARAM + "}";
    //endregion

    //region ACCOUNTS
    public static final String ACCOUNT_ID_PARAM = "accountId";
    public static final String ACCOUNT_RESOURCE = "/accounts";
    public static final String CUSTOMER_ACCOUNT_RESOURCE = CUSTOMER_RESOURCE + ACCOUNT_RESOURCE;
    public static final String CUSTOMER_ACCOUNT_ID_RESOURCE = CUSTOMER_ACCOUNT_RESOURCE + "/{" + ACCOUNT_ID_PARAM + "}";
    public static final String CUSTOMER_ID_ACCOUNT_RESOURCE = CUSTOMER_ID_RESOURCE + ACCOUNT_RESOURCE;
    public static final String CUSTOMER_ID_ACCOUNT_ID_RESOURCE = CUSTOMER_ID_ACCOUNT_RESOURCE + "/{" + ACCOUNT_ID_PARAM + "}";
    //endregion

    //region TRANSACTIONS
    public static final String TRANSACTION_ID_PARAM = "transactionId";
    public static final String TRANSACTION_RESOURCE = "/transactions";
    public static final String CUSTOMER_ACCOUNT_ID_TRANSACTION_RESOURCE = CUSTOMER_ACCOUNT_ID_RESOURCE + TRANSACTION_RESOURCE;
    public static final String CUSTOMER_ACCOUNT_ID_TRANSACTION_ID_RESOURCE = CUSTOMER_ACCOUNT_ID_TRANSACTION_RESOURCE + "/{" + TRANSACTION_ID_PARAM + "}";
    //endregion

}
