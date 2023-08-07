package barcodeGenerator

data class BoxQRcode(
    var packaging: String = "453940087",
    var article: String,
    var index: String,
    var quantityInBox: String,
    var batchNumber: String = "720716",
    var customerArticle: String
)