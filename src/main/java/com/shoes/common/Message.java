package com.shoes.common;

public final class Message {
//    public static final String MY_SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    public static final String MSG_ERROR_PROCESSING = "An error occurred during processing";
    public static final String MSG_SUCCESS_PROCESSING = "processed successfully";
    public static final String MSG_REQUIRED_EMAIL = "Please enter your email!";
    public static final String MSG_REQUIRED_PASSWORD = "Please enter your password!";
    public static final String MSG_EMAIL_NOT_TRUE = "Invalid email!";
    public static final String MSG_LOGIN_SUCCESS = "Login successful!";
    public static final String MSG_LOGIN_FAILD = "Login failed, please check your email and password!";
    public static final String MSG_NOT_FOUND_BY_ID = "No item was found with this ID!";
    public static final String MSG_CREATE_FAIL = "create failed!";
    public static final String MSG_CREATE_SUCCESS = "create successful!";
    public static final String MSG_UPDATE_FAIL = "Update failed!";
    public static final String MSG_UPDATE_SUCCESS = "Update successful!";
    public static final String MSG_DELETE_FAIL = "Delete failed!";
    public static final String MSG_DELETE_SUCCESS = "Delete successful!";
    public static final String MSG_BAD_REQUEST = "Processing failed, please check your request!";
    public static final String MSG_EMAIL_EXIST = "This email is already in use!";
    public static final String MSG_REGISTRY_FAIL = "Sign up failed!";
    public static final String MSG_REGISTRY_SUCCESS = "Sign up successful!";
    public static final String MSG_CHANGE_PASSWORD_SUCCESS = "Password changed successfully!";
    public static final String MSG_CHANGE_PASSWORD_FAIL = "Password changed failed!";
    public static final String MSG_OLD_PASSWORD_NOT_TRUE = "The old password is incorrect!";
    public static final String MSG_NEW_PASSWORD_NOT_MATCH = "Confirm new password is incorrect!";
    public static final String MSG_GET_CATEGORIES_FAIL = "Fail to get categories list!";
    public static final String MSG_GET_CATEGORIES_SUCCESS = "get categories list successfully!";
    public static final String MSG_GET_CATEGORY_FAIL = "Fail to get category!";
    public static final String MSG_GET_CATEGORY_SUCCESS = "Get category successfully!";
    public static final String MSG_GET_ORDER_STATUS_LIST_FAIL = "Fail to get statuses list!";
    public static final String MSG_GET_ORDER_STATUS_LIST_SUCCESS = "Get statuses list successfully!";
    public static final String MSG_GET_PRODUCT_FAIL = "Fail to get product!";
    public static final String MSG_GET_PRODUCT_SUCCESS = "Get product successfully!";
    public static final String MSG_ADD_PICTURE_PRODUCT_FAIL = "There was an error while adding images to the product!";
    public static final String MSG_ADD_PRODUCT_DETAIL_FAIL = "There was an error while adding product detail!";
    public static final String MSG_UPDATE_PICTURE_PRODUCT_FAIL = "There was an error while updating product images!";
    public static final String MSG_GET_ROLE_LIST_FAIL = "Fail to get roles list!";
    public static final String MSG_GET_ROLE_LIST_SUCCESS = "Get roles list successfully!";
    public static final String MSG_GET_CUSTOMER_LIST_FAIL = "Fail to get customers list!";
    public static final String MSG_GET_CUSTOMER_LIST_SUCCESS = "Get customers list successfully!";
    public static final String MSG_GET_EMPLOYEE_LIST_FAIL = "Fail to get employees list!";
    public static final String MSG_GET_EMPLOYEE_LIST_SUCCESS = "Get employees list successfully!";
    public static final String MSG_GET_MANAGER_LIST_FAIL = "Fail to get managers list!";
    public static final String MSG_GET_MANAGER_LIST_SUCCESS = "Get managers list successfully!";
    public static final String MSG_GET_PRODUCTS_lIST_FAIL = "Fail to get products list!";
    public static final String MSG_GET_PRODUCTS_lIST_SUCCESS = "Get products list successfully!";
    public static final String MSG_GET_ORDER_SUPPLIER_FAIL = "Fail to get the order!";
    public static final String MSG_GET_ORDER_SUPPLIER_SUCCESS = "Get the order successfully!";
    public static final String MSG_GET_ORDER_SUPPLIER_LIST_FAIL = "Fail to get orders list!";
    public static final String MSG_GET_ORDER_SUPPLIER_LIST_SUCCESS = "Get orders list successfully!";
    public static final String MSG_CANCEL_ORDER_SUPPLIER_FAIL = "There was an error while canceling the order!";
    public static final String MSG_CANCEL_ORDER_SUPPLIER_SUCCESS = "The order has been successfully canceled!";
    public static final String MSG_CREATE_ORDER_SUPPLIER_FAIL = "Fail to create the Order!";
    public static final String MSG_CREATE_ORDER_SUPPLIER_SUCCESS = "Create the order successfully!";
    public static final String MSG_ADD_PRODUCT_FOR_ORDER_SUPPLIER_FAIL = "Error while adding product to order!";
    public static final String MSG_CREATE_IMPORT_FORM_SUCCESS = "Imported goods successfully!";
    public static final String MSG_CREATE_IMPORT_FORM_FAIL = "Import failed!";
    public static final String MSG_ADD_PRODUCT_FOR_IMPORT_FROM_FAIL = "Error while importing goods!";
    public static final String MSG_GET_IMPORT_FORM_FAIL = "Fail to get import-form!";
    public static final String MSG_GET_IMPORT_FORM_SUCCESS = "Get import-form successfully!";
    public static final String MSG_GET_IMPORT_FORM_LIST_FAIL = "Fail to get import-forms list!";
    public static final String MSG_GET_IMPORT_FORM_LIST_SUCCESS = "Get import-forms list successfully!";
    public static final String MSG_USER_FAIL = "Fail to get user!";
    public static final String MSG_USER_SUCCESS = "Get user successfully!";
    public static final String MSG_CREATE_USER_FAIL = "Fail to create user!";
    public static final String MSG_CREATE_USER_SUCCESS = "Create user successfully!";
    public static final String MSG_UPDATE_USER_FAIL = "Fail to update user!";
    public static final String MSG_UPDATE_USER_SUCCESS = "Update user successfully!";
    public static final String MSG_NAME_PRODUCT_EXIST = "The name is duplicated with another product!";
    public static final String MSG_DELETE_PRODUCT_FAIL_IMPORT = "The product cannot be deleted because it exists in the import-form, the product has been moved to discontinued status!";
    public static final String MSG_DELETE_PRODUCT_FAIL_ORDER_SUPPLIER = "The product cannot be deleted because it exists in the order-form, the product has been moved to discontinued status !";
    public static final String MSG_DELETE_PRODUCT_FAIL_PRICE = "The product cannot be deleted because it exists in the price edit history, the product has been moved to discontinued status!";
    public static final String MSG_DELETE_PRODUCT_FAIL_DEDUCTION = "The product cannot be deleted because it exists in the inventory edit history, the product has been moved to discontinued status!";
    public static final String MSG_DELETE_PRODUCT_FAIL_CART = "The product cannot be deleted because it exists in the cart, the product has been moved to discontinued status!";
    public static final String MSG_GET_ORDER_CUSTOMER_FAIL = "Fail to get the order!";
    public static final String MSG_GET_ORDER_CUSTOMER_SUCCESS = "Get the order successfully!";
    public static final String MSG_GET_ORDER_CUSTOMER_LIST_FAIL = "Fail to get the orders list!";
    public static final String MSG_GET_ORDER_CUSTOMER_LIST_SUCCESS = "Get the orders list successfully!";
    public static final String MSG_UPDATE_STATUS_ORDER_CUSTOMER_FAIL = "There was an error while updating the order status !";
    public static final String MSG_UPDATE_STATUS_ORDER_CUSTOMER_SUCCESS = "Update status for the order successful!";
    public static final String MSG_UPDATE_ORDER_CUSTOMER_TO_WAITING_PICKUP_FAIL = "This order cannot be approved because it is not in \"WAITING_CONFIRMATON\" status!";
    public static final String MSG_NOT_UPDATE_ORDER_CUSTOMER_TO_DELIVERING_FAIL = "The status cannot be changed to \"DELIVERING\" because it is not in the \"WAITING_PICKUP\" status!";
    public static final String MSG_NOT_UPDATE_ORDER_CUSTOMER_TO_DELIVERED_FAIL = "The status cannot be changed to \"DELIVERED\" because it is not in the \"DELIVERING\" status!";
    public static final String MSG_NOT_UPDATE_ORDER_CUSTOMER_TO_CANCELLED_FAIL = "The order whose status is not \"WAITING_CONFIRMATON\" cannot be canceled!";
    public static final String MSG_GET_PRICE_DETAIL_SUCCESS = "Get price-detai successfully!";
    public static final String MSG_GET_PRICE_DETAIL_FAIL = "Fail to get price-detai!";
    public static final String MSG_GET_PRICE_DETAIL_LIST_SUCCESS = "Get price-details list successfully!";
    public static final String MSG_GET_PRICE_DETAIL_LIST_FAIL = "Fail to get price-details list!";
    public static final String MSG_BAD_REQUEST_FOR_PRICE_DETAIL = "Check your input again: Price must be greater than 0, apply date must be after current date!";
    public static final String MSG_CREATE_PRICE_DETAIL_SUCCESS = "Add price to product successfully!";
    public static final String MSG_CREATE_PRICE_DETAIL_FAIL = "Fail to add price for product!";
    public static final String MSG_CANT_NOT_DELETE_PRICE_DETAIL = "Cannot be deleted because this price is currently applied!";
    public static final String MSG_PRICE_DETAIL_EXISTED = "This date has already been updated!";
    public static final String MSG_DELETE_PRICE_DETAIL_SUCCESS = "Delete price successfully!";
    public static final String MSG_DELETE_PRICE_DETAIL_FAIL = "Deleted successfully!";
    public static final String MSG_SENT_MAIL_SUCCESS = "The password recovery link has been sent to your Email!";
    public static final String MSG_TOKEN_EXPIRED = "Fail to reset password because Token has expired!";
    public static final String MSG_RESET_PASSWORD_SUCCESS = "Reset password successfully!";
    public static final String MSG_CANT_NOT_IMPORT_ZERO_QUANTITY = "Cannot import goods with a product quantity of 0!";
    public static final String MSG_ADD_TO_CART_FAIL = "Fail to add product to card!";
    public static final String MSG_ADD_TO_CART_SUCCESS = "Adding product to card successfully!";
    public static final String MSG_INVENTORY_NOT_ENOUGH = "Inventory quantity is not enough, ";
    public static final String MSG_CAN_NOT_ADD_TO_CART_BECAUSE_QUANTITY = "Cannot add to cart because inventory is not enough!";
    public static final String MSG_DO_ORDER_FAIL = "There was an error while ordering!";
    public static final String MSG_DO_ORDER_SUCCESS = "Ordering successfully!";
    public static final String MSG_GET_PRODUCT_IMAGE_LIST_SUCCESS = "Get product images successfully!";
    public static final String MSG_GET_PRODUCT_IMAGE_LIST_FAIL = "Fail to get product images!";
    public static final String MSG_ADD_IMAGE_SUCCESS = "Added an image for this product!";
    public static final String MSG_ADD_IMAGE_FAIL = "Fail to add an image for this product!";
    public static final String MSG_NAME_INCORRECT = "Name is incorrect!";
    public static final String MSG_DOB_INCORRECT = "Date of birth is incorrect!";
    public static final String MSG_PHONE_INCORRECT = "Phone number is incorrect!";
    public static final String MSG_REQUIRED_ADDRESS = "Address is required!";
    public static final String MSG_CAN_NOT_ADD_TO_CART_BECAUSE_QUANTITY_1 = "Can not add to cart because inventory is not enough!";
    public static final String MSG_CAN_NOT_ADD_TO_CART_BECAUSE_QUANTITY_2 = "Can not add to cart because inventory is not enough, the quantity of this product in your cart is ";
    public static final String MSG_GET_PAYMENTS_FAIL = "Fail to get payments list!";
    public static final String MSG_GET_PAYMENTS_SUCCESS = "get payments list successfully!";
    public static final String MSG_GET_PAYMENT_FAIL = "Fail to get payment!";
    public static final String MSG_GET_PAYMENT_SUCCESS = "Get payment successfully!";

}
