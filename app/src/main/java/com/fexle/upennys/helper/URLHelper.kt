package com.fexle.upennys.helper

class URLHelper{
    companion object {
        const val AUTH_TOKEN_KEY = "key"
        const val AUTH_TOKEN = "123"

        const val ACCESS_TOKEN_URL = ""
        private const val DOMAIN_DEV = "http://3.7.83.168:3080/"
        private const val DOMAIN_TEST = "http://3.7.83.168:3081/"
        private const val DOMAIN_LIVE = "http://3.7.83.168:3080/"
        private const val BASE_DEV_URL = "http://3.7.83.168:3080/api/customer/"
        private const val BASE_TEST_URL = "http://3.7.83.168:3081/api/customer/"
        private const val BASE_LIVE_URL = "https://dotforcetech.com"
        const val BASE_URL = DOMAIN_DEV

        const val CUSTOMER_APP_URL = "api/customer/"
        const val STORE_APP_URL = "api/store/"

        const val USER_AUTH = "${CUSTOMER_APP_URL}auth"
        const val USER_REGISTER = "${CUSTOMER_APP_URL}auth/register"
        const val USER_LOGIN = "${CUSTOMER_APP_URL}auth/login"
        const val SEND_OTP = "${CUSTOMER_APP_URL}auth/send-otp"
        const val VERIFY_OTP = "${CUSTOMER_APP_URL}auth/verify_otp"
        const val RESET_PASSWORD = "${CUSTOMER_APP_URL}auth/update-forgot-password"
        const val GET_PROFILE = "${CUSTOMER_APP_URL}profile"
        const val UPDATE_PROFILE = "${CUSTOMER_APP_URL}profile"
        const val CHANGE_PASSWORD = "${CUSTOMER_APP_URL}profile/password"
        const val UPDATE_PROFILE_IMAGE  = "${CUSTOMER_APP_URL}upload_profile_image"
        const val UPDATE_FIREBASE  = "${CUSTOMER_APP_URL}update_device_token"

        const val GET_SERVICE_TYPE  = "${STORE_APP_URL}product/business-categories"


        const val GET_HOME_BANNER  = "${CUSTOMER_APP_URL}product/get_banners/{id}"
        const val GET_CATEGORIES  = "${CUSTOMER_APP_URL}product/get_categories/{id}"
        const val GET_STORES  = "${CUSTOMER_APP_URL}product/get_store_lists"
        const val GET_OFFERS  = "${CUSTOMER_APP_URL}product/get_offers/{id}"


        const val GET_STORE_DETAILS  = "${CUSTOMER_APP_URL}product/get_store_detail/{id}"
        const val GET_PRODUCT_LIST  = "${CUSTOMER_APP_URL}product/get_product_lists/{id}"
        const val GET_PRODUCT_DETAILS  = "${CUSTOMER_APP_URL}product/get_product_detail/{id}"


        const val WALLET_ADD_MONEY  = "${CUSTOMER_APP_URL}wallet/add_to_wallet"
        const val GET_WALLET_TRANSACTION_LIST  = "${CUSTOMER_APP_URL}wallet/get_transaction_history"

        const val GET_FAQ = "${CUSTOMER_APP_URL}contents/faq"
        const val GET_ABOUT_US = "${CUSTOMER_APP_URL}contents/about_us"
        const val GET_PRIVACY_POLICY = "${CUSTOMER_APP_URL}contents/privacy_policy"
        const val GET_CONTACT_US = "${CUSTOMER_APP_URL}contents/contact_us"


        const val UPLOAD_POST_IMAGE  = "add_ad_image"
        const val ADD_POST  = "ad_post"
        const val UPDATE_POST  = "update_post"
        const val GET_SEARCH_AUTOCOMPLETE  = "search_autocomplete"
        const val SEARCH_POST  = "search_ads"
        const val MARK_AS_SOLD  = "mark_as_sold"
        const val DELETE_POST  = "ad_delete"
        const val GET_ALL_CHATS  = "get_user_threads"
        const val GET_CHATS  = "get_user_thread_msg"
        const val SEND_MESSAGE  = "send_thread_msg"
        const val SEND_IMAGE  = "send_thread_image"
        const val UPDATE_MESSAGE_DELIVERED  = "msg_delivered"
        const val UPDATE_MESSAGE_SEEN  = "msg_seen"
        const val DELETE_CHAT  = "clear_threads"
        const val BLOCK_USER  = "block_user"
        const val UNBLOCK_USER  = "unblock_user"
        const val GET_BLOCK_STATUS  = "check_user_block"
        const val GET_BLOCKED_LIST  = "get_blocklist"
        const val GET_LAST_ACTIVE  = "get_user_log"
        const val GET_SETTINGS  = "get_system_setting"

        enum class OtpFor(val value: String) {
            FORGOT_PASSWORD("forgot_password"),
            PHONE_VERIFY("phone_verify"),
            REGISTER("register")

        }

        enum class BusinessCategory(val value: String) {
            FOOD("608aa3d454d529d5a62a058e"),
            GROCERY("607fc8031a3cbb3778eaf990"),
            PHARMACY("607fc9393f64df36dc16ce99")

        }

        enum class TestBusinessCategory(val value: String) {
            FOOD("6092421f54d529d5a641b3bf"),
            GROCERY("6092421f54d529d5a641b3b9"),
            PHARMACY("6092421f54d529d5a641b3bc")

        }
    }
}