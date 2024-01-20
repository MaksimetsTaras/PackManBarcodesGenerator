package mySharedPreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.ui.input.key.Key
import kotlin.io.path.fileVisitor
import kotlin.math.absoluteValue

class mySharedPreferences(context: Context) {

    var _spSettings: SharedPreferences
    var _spEditor: SharedPreferences.Editor

//    val _box_KeyName = "BoxValues"

    lateinit var _context: Context

    init {
        _context = context
        _spSettings = _context.getSharedPreferences("BoxAndPartRecords", Context.MODE_PRIVATE)
        _spEditor = _spSettings.edit()
    }


    private fun removeRecord(key: String) {
        _spEditor.remove(key).commit()
    }

    fun deleteRecordByValue(valueToDelete: String) {
        //get KEY by VALUE
        //remove found Key
        val allRerords = getAllRecords()
        if (allRerords != null) {
            for (record in allRerords) {
                if (record.value == valueToDelete) {
                    removeRecord(record.key)
                }
            }
        }
    }

    fun saveRecord(key: String, value: String) {
        val nextValue = getNextValue(key)
        val newKey = key + "_" + nextValue.toString()
        putString(newKey, value)
    }

    fun getAllValues_WhereKeyStartsWith(startOfKey: String): MutableList<String> {
        val allKeys = getAllRecords()

        val results: MutableList<String> = mutableListOf()

        if (allKeys != null) {
            for (item in allKeys) {
                if (item.key.startsWith(startOfKey)) {
                    results.add(item.value.toString())
                }
            }
        }
        return results
    }

    fun getAllRecords_WhereKeyStartsWith(startOfKey: String): Map<String, MutableState<String>> {
        val allKeys = getAllRecords()

        val results: Map<String, MutableState<String>> = mutableStateMapOf()

        if (allKeys != null) {
            for (item in allKeys) {
                if (item.key.startsWith(startOfKey)) {
                    results.plus(Pair(item.key, item.value))
                }
            }
        }
        return results
    }

    //    fun getPersNumber(): String {
//        return getString(_box_KeyName)
//    }
    private fun getNextValue(startOfKey: String): Int {
        val allKeys = getAllKeys()

        //get all needed keys
        val neededKeys = arrayListOf<String>()
        for (item in allKeys) {
            if (item.startsWith(startOfKey)) {
                neededKeys.add(item)
            }
        }

        //get only digits from name of KEYs
        val digits = arrayListOf<Int>()
        val prefixToRemove = startOfKey + "_"
        var currentKeyNumber = ""
        for (key in neededKeys) {
            currentKeyNumber = key.removePrefix(prefixToRemove)
            digits.add(currentKeyNumber.toInt())
        }

        digits.sortDescending()

        if (digits.isEmpty()) {
            return 0
        } else {
            return digits[0] + 1
        }
    }

//    fun saveSurname(value: String) {
//        putString(_surname_KeyName, value)
//    }
//
//    fun getSurname(): String {
//        return getString(_surname_KeyName)
//    }

    private fun getString(key: String): String {
        return _spSettings.getString(key, "no values found").toString()
    }

    private fun putString(key: String, value: String) {
        _spEditor.putString(key, value).commit()
    }

    private fun getAllKeys(): MutableSet<String> {
        val keys: MutableSet<String> = _spSettings.all.keys
        return keys
    }

    private fun getAllRecords(): MutableMap<String, *>? {
        val keys: MutableMap<String, *>? = _spSettings.all
        return keys
    }
}