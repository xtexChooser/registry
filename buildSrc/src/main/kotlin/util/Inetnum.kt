package util

fun String.unwrapInetnumRange() = reversed()
    .replaceFirst('_', '/')
    .reversed()
    .replace('_', ':')

fun String.wrapInetnumRange() = replace(':', '_')
    .replace('/', '_')
