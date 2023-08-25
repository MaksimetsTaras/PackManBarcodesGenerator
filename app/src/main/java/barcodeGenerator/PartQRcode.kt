package barcodeGenerator

data class PartQRcode(
    var article: String,
    var index: String,
    var customerNumber: String,
    var HWversion: String,
    var SWversion: String,
    var serialNumber: String
)