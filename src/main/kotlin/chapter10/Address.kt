package chapter10

class Address(val houseNumber: String, val road: String, val city: String, val state: String, val zip: String) {
    override fun toString(): String {
        return "$houseNumber $road, $city $state $zip"
    }
}