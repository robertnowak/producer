package contracts


org.springframework.cloud.contract.spec.Contract.make {
    def name, age
    request {
        method 'PUT'
        url '/user'
        name = anyAlphaNumeric()
        age = anyPositiveInt()
        body([
                name: name,
                age : age,
                address: [
                        city: anyOf("wawa", "poznan", "krakow"),
                        type: anyOf("A", "B")
                ]
        ])
        headers {
            contentType('application/json')
        }
    }
    response {
        status OK()
        body([
                name: name,
                age : age
        ])
        headers {
            contentType('application/json')
        }
    }
}