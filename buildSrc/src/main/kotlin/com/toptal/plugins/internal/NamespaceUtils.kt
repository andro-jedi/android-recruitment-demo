package com.toptal.plugins.internal

internal fun pathSuffixFor(
    rootProjectPath: String,
    currentProjectPath: String,
) = currentProjectPath
    .removePrefix(rootProjectPath)
    .removePrefix(":")
    .replace(':', '.')
    .replace("-", ".")
