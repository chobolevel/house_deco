package com.movieland.api.service.upload

import com.amazonaws.HttpMethod
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.Headers
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest
import com.movieland.api.dto.upload.PresignedResponseDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class UploadService(
    @Value("\${cloud.aws.s3.bucket}")
    private val bucket: String,
    private val amazonS3: AmazonS3
) {

    fun getPresignedUrl(prefix: String, filename: String): PresignedResponseDto {
        val key = createKey()
        val fileId = createFileId()
        var newFilename: String = filename
        if (prefix.isNotEmpty()) {
            newFilename = createPath(prefix, key, fileId, filename)
        }
        val generatePresignedUrlRequest = getGeneratePresigendUrlRequest(bucket, newFilename)
        val url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest)
        return PresignedResponseDto(
            presignedUrl = url.toString(),
            originUrl = "${url.protocol}://${url.host}/${url.path}",
            filename = filename
        )
    }

    private fun getGeneratePresigendUrlRequest(bucket: String, filename: String): GeneratePresignedUrlRequest {
        val generatePresignedUrlRequest =
            GeneratePresignedUrlRequest(bucket, filename)
                .withMethod(HttpMethod.PUT)
                .withExpiration(getPresignedUrlExpiration())
        generatePresignedUrlRequest.addRequestParameter(
            Headers.S3_CANNED_ACL,
            CannedAccessControlList.PublicRead.toString()
        )
        return generatePresignedUrlRequest
    }

    private fun getPresignedUrlExpiration(): Date {
        val expiration = Date()
        var expTimeMillis = expiration.time
        expTimeMillis += 1000 * 60 * 2
        expiration.setTime(expTimeMillis)
        return expiration
    }

    private fun createFileId(): String {
        return UUID.randomUUID().toString()
    }

    private fun createKey(): String {
        val now = LocalDate.now().toString()
        val key = now.split("-").joinToString("/")
        return key
    }

    private fun createPath(prefix: String, key: String, fileId: String, filename: String): String {
        return String.format("%s/%s/%s/%s", prefix, key, fileId, filename)
    }
}
