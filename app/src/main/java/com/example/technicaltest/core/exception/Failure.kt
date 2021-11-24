package com.example.technicaltest.core.exception

sealed class Failure {
    object NetworkConnection : Failure()
    data class ServerError(val message: String) : Failure()
    object ExpiredSession: Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure : Failure()
}