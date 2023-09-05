package mySharedPreferences

import android.content.Context
import android.content.SharedPreferences

class mySharedPreferences (context: Context) {

    var _spSettings: SharedPreferences
    var _spEditor: SharedPreferences.Editor

    val _box_KeyName = "BoxValues"
    val _part_KeyName = "Surname"

    lateinit var _context: Context

    init {
        _context = context
        _spSettings = _context.getSharedPreferences("IncomeValues", Context.MODE_PRIVATE)
        _spEditor = _spSettings.edit()
    }

    private fun getString(key: String): String {
        return _spSettings.getString(key, "no values found").toString()
    }

    private fun putString(key: String, value: String) {
        _spEditor.putString(key, value).commit()
    }
//
//    private fun removeRecord(key: String) {
//        _spEditor.remove(key).commit()
//    }

    fun saveBoxValues(value: String) {
        putString(_box_KeyName, value)
    }

    fun getPersNumber(): String {
        return getString(_box_KeyName)
    }

//    fun saveSurname(value: String) {
//        putString(_surname_KeyName, value)
//    }
//
//    fun getSurname(): String {
//        return getString(_surname_KeyName)
//    }
}