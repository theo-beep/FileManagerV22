package android.template.common

/**
 * Sealed classes and interfaces represent restricted class hierarchies that provide more control over inheritance
 *
 * **See Also:** [Sealed classes and interfaces](https://kotlinlang.org/docs/sealed-classes.html)
 */
sealed class DataResource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : DataResource<T>(data)
    class Error<T>(message: String?, data: T? = null) : DataResource<T>(data, message)
    class Loading<T>(data: T? = null) : DataResource<T>(data)
}