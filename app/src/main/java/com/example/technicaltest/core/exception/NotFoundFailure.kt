package com.example.technicaltest.core.exception

class NotFoundFailure {

    class DataNotExist() : Failure.FeatureFailure()

    class EmptyListLoadMore() : Failure.FeatureFailure()
}
