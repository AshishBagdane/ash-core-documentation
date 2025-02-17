package dev.ash.core.lib.doc.controllers;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Standardized API response documentation annotation for REST controllers. This annotation provides a consistent way to
 * document common HTTP response scenarios across all API endpoints. It automatically includes documentation for
 * successful responses and common error scenarios.
 *
 * <p>The error responses use the standardized ErrorResponse class from the error-handling module
 * to ensure consistent error reporting across the application.
 *
 * <p>Usage example:
 * <pre>
 * {@code
 * @RestController
 * @ApplicationApiResponses
 * public class UserController {
 *     @GetMapping("/users/{id}")
 *     public UserDTO getUser(@PathVariable Long id) {
 *         // Method implementation
 *     }
 * }
 * }</pre>
 *
 * <p>The annotation can be customized by specifying additional {@link ApiResponse} annotations
 * at the method level, which will be combined with these default responses.
 *
 * @see ApiResponse
 * @since 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@ApiResponse(
    responseCode = "200",
    description = "Request processed successfully.",
    content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = Object.class)
    )
)
@ApiResponse(
    responseCode = "400",
    description = "Invalid request. This could be due to missing or invalid parameters, " +
        "malformed request syntax, or invalid field values.",
    content = @Content(
        mediaType = "application/json",
        schema = @Schema(
            implementation = Void.class,
            name = "BadRequestException",
            description = "Standard error response structure",
            example = """
                {
                    "errorCode": "INVALID_REQUEST",
                    "message": "Invalid request parameters",
                    "details": ["Field 'email' must be a valid email address"],
                    "timestamp": "2024-01-09T10:15:30.123Z"
                }
                """
        )
    )
)
@ApiResponse(
    responseCode = "401",
    description = "Authentication required. The request lacks valid authentication credentials " +
        "or the provided credentials are invalid.",
    content = @Content(
        mediaType = "application/json",
        schema = @Schema(
            implementation = Void.class,
            name = "BadRequestException",
            description = "Standard error response structure",
            example = """
                {
                    "errorCode": "UNAUTHORIZED",
                    "message": "Authentication required",
                    "timestamp": "2024-01-09T10:15:30.123Z"
                }
                """
        )
    )
)
@ApiResponse(
    responseCode = "403",
    description = "Permission denied. The authenticated user lacks sufficient permissions " +
        "to access the requested resource.",
    content = @Content(
        mediaType = "application/json",
        schema = @Schema(
            implementation = Void.class,
            name = "BadRequestException",
            description = "Standard error response structure",
            example = """
                {
                    "errorCode": "FORBIDDEN",
                    "message": "Insufficient permissions",
                    "timestamp": "2024-01-09T10:15:30.123Z"
                }
                """
        )
    )
)
@ApiResponse(
    responseCode = "500",
    description = "Internal server error. An unexpected condition was encountered.",
    content = @Content(
        mediaType = "application/json",
        schema = @Schema(
            implementation = Void.class,
            name = "BadRequestException",
            description = "Standard error response structure",
            example = """
                {
                    "errorCode": "INTERNAL_ERROR",
                    "message": "An unexpected error occurred",
                    "timestamp": "2024-01-09T10:15:30.123Z"
                }
                """
        )
    )
)
public @interface ApplicationApiResponses {

    /**
     * Allows overriding the media type for the successful response (200). Defaults to "application/json" if not
     * specified.
     *
     * @return the media type for the successful response
     */
    @AliasFor(annotation = ApiResponse.class, attribute = "content")
    String mediaType() default "application/json";
}
