package com.backstopmedia.kotlin.ktwitter.utils.functional

import android.util.Log
import java.util.*

fun <K, V> List<V>.toMultimapBy(fn: (V) -> K): Map<K, List<V>> {
    val retval = hashMapOf<K, List<V>>()
    forEach {
        val k = fn(it)
        var list = retval[k] as ArrayList<V>?
        if (list == null) {
            list = arrayListOf()
            retval[k] = list
        }
        list.add(it)
    }
    return retval
}

fun <K, V, R : Comparable<R>> Map<K, V>.sortKeysByValue(fn: (V) -> R): List<K> {
    return toSortedMap(object : Comparator<K> {
        override fun compare(lhs: K, rhs: K): Int {
            return fn(get(lhs)!!).compareTo(fn(get(rhs)!!))
        }
    }).keys.toList()
}