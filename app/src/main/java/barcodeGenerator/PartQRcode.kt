package barcodeGenerator

data class PartQRcode(
    var article: String,
    var index: String,
    var customerArticle: String,
    var HWversion: String,
    var SWversion: String,
    var serialNumber: String,
    var isHWpresent: Boolean,
    var isSWpresent: Boolean
)