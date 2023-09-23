package mySharedPreferences

import android.content.Context
import android.content.SharedPreferences

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


//
//    private fun removeRecord(key: String) {
//        _spEditor.remove(key).commit()
//    }

    fun saveRecord(key: String, value: String) {
        val nextValue = getQtyOfKeysStartsWith(key) + 1
        val newKey = key + "_" + nextValue.toString()
        putString(newKey, value)
    }

    fun getAllRecordsStartsWith(startOfKey: String): MutableList<String> {
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
//    fun getPersNumber(): String {
//        return getString(_box_KeyName)
//    }
    private fun getQtyOfKeysStartsWith(startOfKey: String): Int {
        val allKeys = getAllKeys()
        var qtyOfRecords = 0

        for (item in allKeys) {
            if (item.startsWith(startOfKey)) {
                qtyOfRecords = qtyOfRecords + 1
            }
        }
        return qtyOfRecords
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