package top.cpjinan.akaribot.utils

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

/**
 * AkariBot
 * top.cpjinan.akaribot.utils
 *
 * HTTP 工具类。
 *
 * @author 季楠
 * @since 2026/1/18 22:18
 */
object HttpUtils {
    private val client = OkHttpClient()

    /**
     * 发送 POST 请求。
     *
     * @param url 请求 URL。
     * @param body 请求体。
     * @return 响应体字符串。
     */
    fun sendPostRequest(url: String, body: String): String {
        return client.newCall(
            Request.Builder().url(url).post(body.toRequestBody("application/json".toMediaType())).build()
        ).execute().use { response ->
            response.body.string()
        }
    }
}